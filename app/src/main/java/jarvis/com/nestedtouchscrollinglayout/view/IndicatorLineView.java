package jarvis.com.nestedtouchscrollinglayout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import jarvis.com.nestedtouchscrollinglayout.DisplayUtils;

/**
 * @author yyf @ Zhihu Inc.
 * @since 01-21-2019
 */
public class IndicatorLineView extends View {

    private Paint mPaint;

    private Path mPath;

    public IndicatorLineView(Context context) {
        super(context);
        init();
    }

    public IndicatorLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IndicatorLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initOperator();
    }

    private void initOperator() {
        setWillNotDraw(false);
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAlpha(255);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(DisplayUtils.dpToPixel(getContext(), 5));
        mPaint.setAntiAlias(true);
        mPaint.setPathEffect(new CornerPathEffect(DisplayUtils.dpToPixel(getContext(), 5)));

        mPath = new Path();
        updatePath(0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int width = DisplayUtils.dpToPixel(getContext(), 69);
        int height = DisplayUtils.dpToPixel(getContext(), 42);
        setMeasuredDimension(
                (modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : width,
                (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : height);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawPath(mPath, mPaint);
    }

    private void updatePath(float velocityY) {
        if (Math.abs(velocityY) <= 300) {
            velocityY = 0;
        }
        velocityY = velocityY > 3500 ? 3500 : (velocityY < -3500 ? -3500 : velocityY);
        Log.e("updatePath ---> ", velocityY + "");
        mPath.moveTo(DisplayUtils.dpToPixel(getContext(), 10), DisplayUtils.dpToPixel(getContext(), 16));
        mPath.lineTo(DisplayUtils.dpToPixel(getContext(), 32),  DisplayUtils.dpToPixel(getContext(), 16 + (velocityY / 3500) * 14));
        mPath.lineTo(DisplayUtils.dpToPixel(getContext(), 54), DisplayUtils.dpToPixel(getContext(), 16));
    }

    public void updateVelocity(float velocityY) {
        clearPath();
        ViewCompat.postInvalidateOnAnimation(this);
        updatePath(velocityY);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private void clearPath() {
        mPath.reset();
    }

}
