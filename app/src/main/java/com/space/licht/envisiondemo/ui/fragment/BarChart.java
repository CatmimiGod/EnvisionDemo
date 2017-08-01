package com.space.licht.envisiondemo.ui.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * 作者：chs on 2016/9/8 09:46
 * 邮箱：657083984@qq.com
 * 柱形图表
 */
public class BarChart extends View {
    private Context mContext;
    /**
     * 背景的颜色
     */
    private static final int BG_COLOR = Color.parseColor("#EEEEEE");
    /**
     * 视图的宽和高
     */
    private int mTotalWidth, mTotalHeight;
    /**
     * x轴 y轴 起始坐标
     */
    private float xStartIndex, yStartIndex;
    /**
     * 图表绘制区域的顶部和底部  图表绘制区域的最大高度
     */
    private float paintTop, paintBottom, maxHeight;
    /**
     * 左边和上边的边距
     */
    private int leftMargin, topMargin;
    /**
     * 画笔 背景，轴 ，线 ，text ,点
     */
    private Paint bgPaint, axisPaint, textPaint, barPaint, borderPaint;
    /**
     * 上下左右的白色部分
     */
    private Rect leftWhiteRect, rightWhiteRect, topWhiteRect, bottomWhiteRect;
    /**
     * 矩形柱子  点击后的矩形
     */
    private Rect mBarRect, mBarRectClick;
    private List<ChartEntity> mData;//数据集合
    /**
     * 右边的最大和最小值
     */
    private int maxRight, minRight;
    /**
     * item中的最大值
     */
    private float maxValueInItems;
    /**
     * 最大分度值
     */
    private float maxDivisionValue;
    /**
     * 左后一次的x坐标
     */
    private float lastPointX;
    /**
     * 向右边滑动的距离
     */
    private float leftMoving;
    //左边Y轴的单位
    private String leftAxisUnit = "1400";
    /**
     * 当前移动的距离
     */
    private float movingThisTime = 0.0f;
    /**
     * 每一个bar的宽度
     */
    private int barWidth;
    /**
     * 每个bar之间的距离
     */
    private int barSpace;
    /**
     * 柱形图左边的x轴坐标 和右边的x轴坐标
     */
    private List<Integer> mBarLeftXPoints = new ArrayList<>();
    private List<Integer> mBarRightXPoints = new ArrayList<>();


    /* 用户点击到了无效位置 */
    public static final int INVALID_POSITION = -1;
    private OnItemBarClickListener mOnItemBarClickListener;
    private GestureDetector mGestureListener;
    /**
     * 是否绘制点击效果
     */
    private boolean isDrawBorder;
    /**
     * 点击的地方
     */
    private int mClickPosition;

    public void setOnItemBarClickListener(OnItemBarClickListener onRangeBarClickListener) {
        this.mOnItemBarClickListener = onRangeBarClickListener;
    }

    public interface OnItemBarClickListener {
        void onClick(int position);
    }

    public BarChart(Context context) {
        super(context);
        init(context);
    }

    public BarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setWillNotDraw(false);
        mContext = context;
        leftMargin = DensityUtil.dip2px(context, 16);
        topMargin = DensityUtil.dip2px(context, 30);

        bgPaint = new Paint();
        bgPaint.setColor(Color.WHITE);

        axisPaint = new Paint();
        axisPaint.setStrokeWidth(DensityUtil.dip2px(context, 1));

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(DensityUtil.dip2px(getContext(), 10));

        barPaint = new Paint();
        barPaint.setColor(Color.parseColor("#6FC5F4"));

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setAntiAlias(true);
        borderPaint.setStyle(Paint.Style.FILL);
        borderPaint.setColor(Color.rgb(0, 0, 0));
        borderPaint.setAlpha(120);

        mBarRect = new Rect(0, 0, 0, 0);
        mBarRectClick = new Rect(0, 0, 0, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mTotalWidth = w - getPaddingLeft() - getPaddingRight();
        mTotalHeight = h - getPaddingTop() - getPaddingBottom();
        setNeedHeight();
        leftWhiteRect = new Rect(0, 0, 0, mTotalHeight);
        rightWhiteRect = new Rect(mTotalWidth - leftMargin * 2, 0, mTotalWidth, mTotalHeight);
        topWhiteRect = new Rect(0, 0, mTotalWidth, topMargin / 2);
        bottomWhiteRect = new Rect(0, (int) yStartIndex, mTotalWidth, mTotalHeight);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 设置矩形的顶部 底部 右边Y轴的3部分每部分的高度
     */
    private void setNeedHeight() {
        paintTop = topMargin * 2;
        paintBottom = mTotalHeight - topMargin / 2;
        maxHeight = paintBottom - paintTop;
        yStartIndex = mTotalHeight - topMargin / 2;
        ;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

    public void setData(List<ChartEntity> list) {
        this.mData = list;
        //计算最大值
        maxValueInItems = list.get(0).getyValue();
        for (ChartEntity entity : list) {
            if (entity.getyValue() > maxValueInItems) {
                maxValueInItems = entity.getyValue();
            }
        }
        getRange(1000);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(BG_COLOR);
        if (mData == null) return;
        //得到每个bar的宽度
        getItemsWidth();
//        canvas.drawRect(bottomWhiteRect, bgPaint);
//        canvas.drawRect(topWhiteRect, bgPaint);
        //绘制矩形柱子
        drawBars(canvas);
        //画左边的Y轴
        canvas.drawLine(xStartIndex, yStartIndex, xStartIndex, topMargin / 2 + 80, axisPaint);

        drawLeftYAxis(canvas);
        //画X轴 下面的
        canvas.drawLine(xStartIndex, yStartIndex, mTotalWidth - leftMargin * 2, yStartIndex, axisPaint);
        //画X轴的text
        drawXAxisText(canvas);
        //画指示器
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        canvas.drawText("Remain",mBarLeftXPoints.get(4)+20,120,textPaint);
        canvas.drawText("Total",mBarLeftXPoints.get(4)+20,160,textPaint);
        // 绘制这个三角形,你可以绘制任意多边形
        Path path = new Path();
        path.moveTo(mBarLeftXPoints.get(4), 100);// 此点为多边形的起点
        path.lineTo(mBarLeftXPoints.get(4)+20, 100);
        path.lineTo(mBarLeftXPoints.get(4)+20, 120);
        path.lineTo(mBarLeftXPoints.get(4), 120);
        path.close(); // 使这些点构成封闭的多边形
        textPaint.setColor(Color.argb(120,120,1,20));
        canvas.drawPath(path,textPaint);

        Path path2 = new Path();
        path2.moveTo(mBarLeftXPoints.get(4), 140);// 此点为多边形的起点
        path2.lineTo(mBarLeftXPoints.get(4)+20, 140);
        path2.lineTo(mBarLeftXPoints.get(4)+20, 160);
        path2.lineTo(mBarLeftXPoints.get(4), 160);
        path2.close(); // 使这些点构成封闭的多边形
        textPaint.setColor(Color.argb(120,020,32,20));
        canvas.drawPath(path2,textPaint);


    }


    /**
     * 绘制柱形图
     *
     * @param canvas
     */
    private void drawBars(Canvas canvas) {
        mBarLeftXPoints.clear();
        mBarRightXPoints.clear();
        mBarRect.bottom = mTotalHeight - topMargin / 2;
        Log.i("StartIndex", "xStartIndex" + xStartIndex + "barWidth:" + barWidth + "barSpace" + barSpace + "leftMoving" + leftMoving);
        for (int i = 0; i < mData.size(); i++) {
            mBarRect.left = (int) (xStartIndex + barWidth * i + barSpace * (i + 1) - leftMoving)-20;
            mBarRect.top = (int) maxHeight + topMargin * 2 - (int) (maxHeight * (mData.get(i).getyValue() / maxDivisionValue));
            mBarRect.right = mBarRect.left + barWidth - 30;
            mBarLeftXPoints.add(mBarRect.left);
            mBarRightXPoints.add(mBarRect.right);
            barPaint.setColor(Color.BLUE);
            Log.e(TAG, "drawBars: " + mBarRect.bottom);
            canvas.drawRect(mBarRect, barPaint);


            Rect mBarRects = new Rect();
            mBarRects.bottom = mBarRect.top;
            mBarRects.top = mBarRect.top - (int) (maxHeight * (mData.get(i).getyValue() / 1400));
            mBarRects.left = mBarRect.left;
            mBarRects.right = mBarRect.right;

            barPaint.setColor(Color.RED);
            canvas.drawRect(mBarRects, barPaint);

            canvas.drawText("" + mBarRect.top, mBarRect.left, mBarRect.top + 25, textPaint);
            canvas.drawText("" + mBarRects.top, mBarRect.left, mBarRects.top, textPaint);
        }

    }


    /**
     * 设定两个点之间的间距 和向右边滑动的时候右边的最大距离
     */
    private void getItemsWidth() {
        int barMinWidth = DensityUtil.dip2px(getContext(), 10);
        int barMinSpace = DensityUtil.dip2px(getContext(), 10);

        barWidth = (mTotalWidth - leftMargin * 2) / (mData.size() + 3);
        barSpace = (mTotalWidth - leftMargin * 2 - barWidth * mData.size()) / (mData.size() + 1);
        if (barWidth < barMinWidth || barSpace < barMinSpace) {
            barWidth = barMinWidth;
            barSpace = barMinSpace;
        }
//        barWidth = DensityUtil.dip2px(getContext(), 10);
//        barSpace = DensityUtil.dip2px(getContext(), 20);
        maxRight = (int) (xStartIndex + (barSpace + barWidth) * mData.size()) + barSpace * 2 - 20;
        minRight = mTotalWidth - barSpace - leftMargin - 20;
    }


    /**
     * 得到柱状图的最大和最小的分度值
     *
     * @param maxValueInItems
     */
    private void getRange(float maxValueInItems) {
        int scale = CalculateUtil.getScale(maxValueInItems);//获取这个最大数 数总共有几位
        float unScaleValue = (float) (maxValueInItems / Math.pow(10, scale));//最大值除以位数之后剩下的值  比如1200/1000 后剩下1.2

        maxDivisionValue = (float) (CalculateUtil.getRangeTop(unScaleValue) * Math.pow(10, scale));//获取Y轴的最大的分度值
        xStartIndex = CalculateUtil.getDivisionTextMaxWidth(maxDivisionValue, mContext) + 20;
    }

    /**
     * 画Y轴上的text (1)当最大值大于1 的时候 将其分成5份 计算每个部分的高度  分成几份可以自己定
     * （2）当最大值大于0小于1的时候  也是将最大值分成5份
     * （3）当为0的时候使用默认的值
     *
     * @param canvas
     */
    private void drawLeftYAxis(Canvas canvas) {
        float eachHeight = (maxHeight / 4f);


        for (int i = 0; i <= 4; i++) {
            float startY = paintBottom - eachHeight * i;
            if (startY < topMargin / 2) {
                break;
            }
            int[] arr = {0, 200, 600, 1000, 1400};
            String text = "" + arr[i];
            canvas.drawText(text, xStartIndex - textPaint.measureText(text) - 5, startY + textPaint.measureText("0"), textPaint);
        }

    }

    /**
     * 绘制X轴上的text
     *
     * @param canvas
     */


    private void drawXAxisText(Canvas canvas) {
        String[] arr = {"Father", "Wife", "Son", "Daughter", "Ipad"};

        float distance = 0;
        for (int i = 0; i < 5; i++) {
            distance = xStartIndex + barWidth * i + barSpace * (i + 1) - leftMoving;
            String text = arr[i];
            canvas.drawText(text, mBarLeftXPoints.get(i) - (textPaint.measureText(text) - barWidth) / 2 - 20, paintBottom + DensityUtil.dip2px(getContext(), 10), textPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }


}
