
package com.space.licht.envisiondemo.ui.fragment.chart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Region.Op;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.app.App;

/**
 * 水波浪球形进度View
 *
 * @author caizhiming
 */
public class DataSinkingView extends FrameLayout {
    private static final int DEFAULT_TEXTCOLOT = 0xff0000aa;

    private static final int DEFAULT_TEXTSIZE = 65;

    private float mPercent;

    private Paint mPaint = new Paint();

    private Bitmap mBitmap;

    private Bitmap mScaledBitmap;

    private float mLeft;

    private int mSpeed = 15;
    private Context mContext;

    private int mRepeatCount = 0;

    private Status mFlag = Status.NONE;

    private int mTextColor = DEFAULT_TEXTCOLOT;

    private int mTextSize = DEFAULT_TEXTSIZE;

    public DataSinkingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public void setTextColor(int color) {
        mTextColor = color;
    }

    public void setTextSize(int size) {
        mTextSize = size;
    }

    public void setPercent(float percent) {
        mFlag = Status.RUNNING;
        mPercent = percent;
        postInvalidate();

    }

    public void setStatus(Status status) {
        mFlag = status;
    }

    public void clear() {
        mFlag = Status.NONE;
        if (mScaledBitmap != null) {
            mScaledBitmap.recycle();
            mScaledBitmap = null;
        }

        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        //裁剪成圆区域
        Path path = new Path();
        canvas.save();
        path.reset();
        canvas.clipPath(path);
        path.addCircle(width / 2, height / 2, width / 2, Direction.CCW);
        canvas.clipPath(path, Op.REPLACE);

        if (mFlag == Status.RUNNING) {
            if (mScaledBitmap == null) {
                mBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.wave);
                mScaledBitmap = Bitmap.createScaledBitmap(mBitmap, mBitmap.getWidth(), getHeight(), false);
                mBitmap.recycle();
                mBitmap = null;
                mRepeatCount = (int) Math.ceil(getWidth() / mScaledBitmap.getWidth() + 0.5) + 1;
            }
            for (int idx = 0; idx < mRepeatCount; idx++) {
                canvas.drawBitmap(mScaledBitmap, mLeft + (idx - 1) * mScaledBitmap.getWidth(), (1 - mPercent) * getHeight(), null);
            }
            String str = (int) (mPercent * 100) + "";
            mPaint.setColor(0xff2073b3);
            mPaint.setTextSize(mTextSize);
            mPaint.setStyle(Style.FILL);

            mPaint.setTypeface(App.mRegularTf);
            canvas.drawText(str, (getWidth() - mPaint.measureText(str)) / 2, getHeight() / 2 - mTextSize / 2, mPaint);
            mPaint.setTextSize(30);
            canvas.drawText("%", (getWidth() + mPaint.measureText(str)) / 2 + mPaint.getTextSize() / 2, getHeight() / 2 - mTextSize / 2, mPaint);
            mPaint.setTypeface(App.mBoldTf);
            mPaint.setColor(0xffffffff);
            mPaint.setTextSize(35);
            canvas.drawText("Used Data", (getWidth() - mPaint.measureText("Used Data")) / 2, getHeight() / 2 + 2 * mPaint.getTextSize(), mPaint);

            mLeft += mSpeed;
            if (mLeft >= mScaledBitmap.getWidth())
                mLeft = 0;
            // 绘制外圆环
            mPaint.setStyle(Style.STROKE);
            mPaint.setStrokeWidth(0);
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.rgb(33, 211, 39));
            canvas.drawCircle(width / 2, height / 2, width / 2 - 2, mPaint);

            postInvalidateDelayed(20);
        }
        canvas.restore();

    }

    public enum Status {
        RUNNING, NONE
    }

}
