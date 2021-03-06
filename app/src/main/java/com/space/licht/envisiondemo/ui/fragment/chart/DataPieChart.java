package com.space.licht.envisiondemo.ui.fragment.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.space.licht.envisiondemo.app.App;
import com.space.licht.envisiondemo.model.bean.Collection;
import com.space.licht.envisiondemo.ui.fragment.CalculateUtil;

import java.util.List;

/**
 * 作者：chs on 2016/9/8 16:25
 * 邮箱：657083984@qq.com
 * 饼状图表
 */
public class DataPieChart extends View {
    private static final String TAG = "PieChart";
    /**
     * 视图的宽和高
     */
    private int mTotalWidth, mTotalHeight;
    /**
     * 绘制区域的半径
     */
    private float mRadius;

    private Paint mPaint, mLinePaint, mTextPaint;

    private Path mPath;
    /**
     * 扇形的绘制区域
     */
    private RectF mRectF;
    /**
     * 点击之后的扇形的绘制区域
     */
    private RectF mRectFTouch;

    private List<Collection> mDataList;
    /**
     * 所有的数据加起来的总值
     */
    private float mTotalValue;
    /**
     * 起始角度的集合
     */
    private float[] angles;
    /**
     * 手点击的部分的position
     */
    private int position = 1;
    /**
     * 点击监听
     */
    private OnItemPieClickListener mOnItemPieClickListener;
    private Paint mBluePaint;

    public void setOnItemPieClickListener(OnItemPieClickListener onItemPieClickListener) {
        mOnItemPieClickListener = onItemPieClickListener;
    }

    public interface OnItemPieClickListener {
        void onClick(int position);
    }

    public DataPieChart(Context context) {
        super(context);
        init(context);
    }

    public DataPieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DataPieChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mRectF = new RectF();
        mRectFTouch = new RectF();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setStrokeWidth(2);
        mLinePaint.setColor(Color.BLACK);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);

        mTextPaint.setTypeface(App.mRegularTf);
        mTextPaint.setColor(0xff999999);
        mTextPaint.setTextSize(40);


        mBluePaint = new Paint();
        mBluePaint.setAntiAlias(true);
        mBluePaint.setStyle(Paint.Style.FILL);
        mBluePaint.setTextSize(52);

        mBluePaint.setTypeface(App.mMediumTf);
        mBluePaint.setColor(0xff00a2ff);

        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w - getPaddingLeft() - getPaddingRight();
        mTotalHeight = h - getPaddingTop() - getPaddingBottom();

        mRadius = (float) (Math.min(mTotalWidth, mTotalHeight) / 2 * 0.7);

        mRectF.left = -mRadius;
        mRectF.top = -mRadius;
        mRectF.right = mRadius;
        mRectF.bottom = mRadius;

        mRectFTouch.left = -mRadius - 16;
        mRectFTouch.top = -mRadius - 16;
        mRectFTouch.right = mRadius + 16;
        mRectFTouch.bottom = mRadius + 16;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDataList == null)
            return;
        canvas.translate(mTotalWidth / 2, mTotalHeight / 2);
        //绘制饼图的每块区域
        drawPiePath(canvas);
    }

    /**
     * 绘制饼图的每块区域 和文本
     *
     * @param canvas
     */
    private void drawPiePath(Canvas canvas) {
        //起始地角度
        float startAngle = -80;
        for (int i = 0; i < mDataList.size(); i++) {
            float sweepAngle = mDataList.get(i).getDataTime() / mTotalValue * 354;//每个扇形的角度
            mPaint.setColor(mDataList.get(i).getUsedColor());
            //*******下面的两种方法选其一就可以 一个是通过画路径来实现 一个是直接绘制扇形***********
            mPath.moveTo(0, 0);
            if (position - 1 == i) {
                mPath.arcTo(mRectFTouch, startAngle, sweepAngle);
            } else {
                mPath.arcTo(mRectF, startAngle, sweepAngle);
            }
            canvas.drawPath(mPath, mPaint);
            mPath.reset();
            canvas.drawArc(mRectF, startAngle, sweepAngle, true, mPaint);
//            if(position-1==i){
//                canvas.drawArc(mRectFTouch,startAngle,sweepAngle,true,mPaint);
//            }else {
//                canvas.drawArc(mRectF,startAngle,sweepAngle,true,mPaint);
//            }
//            Log.i("toRadians",(startAngle+sweepAngle/2)+"****"+Math.toRadians(startAngle+sweepAngle/2));
            //确定直线的起始和结束的点的位置
            float pxs = (float) (mRadius * Math.cos(Math.toRadians(startAngle + sweepAngle / 4)));
            float pys = (float) (mRadius * Math.sin(Math.toRadians(startAngle + sweepAngle / 4)));
            float pxt = (float) ((mRadius + 30) * Math.cos(Math.toRadians(startAngle + sweepAngle / 4)));
            float pyt = (float) ((mRadius + 30) * Math.sin(Math.toRadians(startAngle + sweepAngle / 4)));
            angles[i] = startAngle;
            startAngle += sweepAngle + 1;
            //绘制线和文本
            canvas.drawLine(pxs, pys, pxt, pyt, mLinePaint);
            float res = mDataList.get(i).getDataTime() / mTotalValue * 100;
            //提供精确的小数位四舍五入处理。
            double resToRound = CalculateUtil.round(res, 2);
            float v = startAngle % 360;
            Log.e(TAG, "drawPiePath: " + i);
            if (startAngle % 360.0 >= 120.0 && startAngle % 360.0 <= 300.0) {//2 3 象限
                canvas.drawLine(pxt, pyt, pxt - 200, pyt, mLinePaint);
                mBluePaint.setColor(0xff666666);
                canvas.drawText(mDataList.get(i).getNamed(), pxt - mTextPaint.measureText(resToRound + "%") - 80, pyt - 5, mTextPaint);
                canvas.drawText(mDataList.get(i).getDataTime() + "%", pxt - mTextPaint.measureText(resToRound + "%") - 80, pyt + 45, mBluePaint);
            } else {
                canvas.drawLine(pxt, pyt, pxt + 160, pyt, mLinePaint);
                if (!"Unused".equals(mDataList.get(i).getNamed())) {
                    mBluePaint.setColor(0xff666666);
                    canvas.drawText(mDataList.get(i).getNamed(), pxt + 50, pyt - 10, mTextPaint);
                    canvas.drawText(mDataList.get(i).getDataTime() + "%", pxt + 80, pyt + 40, mBluePaint);
                } else {
                    Log.e(TAG, "drawPiePath:2 " + i);
                    mBluePaint.setColor(0xff00a2ff);
                    canvas.drawText(mDataList.get(i).getNamed(), pxt - mTextPaint.measureText(resToRound + "%") + 180, pyt - 5, mTextPaint);
                    canvas.drawText(32*mDataList.get(i).getDataTime()+"G", pxt - mTextPaint.measureText(resToRound + "%") + 170, pyt + 45, mBluePaint);
                }
            }
        }

    }

    public void setDataList(List<Collection> mSGDatas) {
        this.mDataList = mSGDatas;
        mTotalValue = 0;
        for (Collection pieData : mDataList) {
            mTotalValue += pieData.getDataTime();
        }
        angles = new float[mDataList.size()];
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                float x = event.getX() - (mTotalWidth / 2);
//                float y = event.getY() - (mTotalHeight / 2);
//                float touchAngle = 0;
//                if (x < 0 && y < 0) {  //2 象限
//                    touchAngle += 180;
//                } else if (y < 0 && x > 0) {  //1象限
//                    touchAngle += 360;
//                } else if (y > 0 && x < 0) {  //3象限
//                    touchAngle += 180;
//                }
//                //Math.atan(y/x) 返回正数值表示相对于 x 轴的逆时针转角，返回负数值则表示顺时针转角。
//                //返回值乘以 180/π，将弧度转换为角度。
//                touchAngle += Math.toDegrees(Math.atan(y / x));
//                if (touchAngle < 0) {
//                    touchAngle = touchAngle + 360;
//                }
//                float touchRadius = (float) Math.sqrt(y * y + x * x);
//                if (touchRadius < mRadius) {
//                    position = -Arrays.binarySearch(angles, (touchAngle)) - 1;
//                    invalidate();
//                    if (mOnItemPieClickListener != null) {
//                        mOnItemPieClickListener.onClick(position - 1);
//                    }
//                }
//                break;
//        }
        return super.onTouchEvent(event);
    }
}
