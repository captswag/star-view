package com.anjith.starview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by anjithsasindran
 * on 27/10/17.
 */

public class StarView extends View {

    private static final int MINIMUM_WIDTH = 80;
    private static final int MINIMUM_HEIGHT = 80;
    private static final float STAR_STROKE_SCALE_FACTOR = 0.04f;

    private int mColor;
    private int mRating;                // 0 - 10, where 0 means no fill and 10 means fully filled

    private Paint mStrokePaint;
    private Paint mRectPaint;

    private RectF mRectF;

    private Path mStarPath;

    private Circle mOuterCircle;
    private Circle mInnerCircle;

    public StarView(Context context) {
        super(context);
    }

    public StarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.StarView);

        if (attrs != null) {
            mColor = a.getColor(R.styleable.StarView_color, Color.parseColor("#FDAB42"));
            mRating = a.getInteger(R.styleable.StarView_rating, 0);
        }

        a.recycle();

        mStrokePaint = new Paint();
        mStrokePaint.setColor(mColor);
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setStyle(Paint.Style.STROKE);

        mRectPaint = new Paint();
        mRectPaint.setColor(mColor);

        mRectF = new RectF();

        mStarPath = new Path();

        mOuterCircle = new Circle(Constants.OUTER_CIRCLE_ANGLES);
        mInnerCircle = new Circle(Constants.INNER_CIRCLE_ANGLES);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = resolveSize(MINIMUM_WIDTH, widthMeasureSpec);
        int height = resolveSize(MINIMUM_HEIGHT, heightMeasureSpec);

        int viewSize = Math.min(width, height);

        float strokeWidth = STAR_STROKE_SCALE_FACTOR * viewSize;
        mStrokePaint.setStrokeWidth(strokeWidth);

        setMeasuredDimension(viewSize, viewSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int availableWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int availableHeight = getHeight() - getPaddingTop() - getPaddingBottom();
        float sideLength = Math.min(availableWidth, availableHeight);

        int left = getPaddingLeft();
        int top = getPaddingTop();
        int bottom = getHeight() - getPaddingBottom();

        float outerCircleRadius = sideLength / 2f;
        float innerCircleRadius = CoordinateHelper.findInnerCircleRadius(outerCircleRadius);

        float centerX = left + outerCircleRadius;
        float centerY = top + outerCircleRadius;

        mOuterCircle.setCenterX(centerX);
        mOuterCircle.setCenterY(centerY);
        mOuterCircle.setRadius(outerCircleRadius);
        mOuterCircle.initCoordinates();

        mInnerCircle.setCenterX(centerX);
        mInnerCircle.setCenterY(centerY);
        mInnerCircle.setRadius(innerCircleRadius);
        mInnerCircle.initCoordinates();

        // getY() of coordinate 2 points to the bottom left point of the star
        float heightDelta = bottom - mOuterCircle.getCoordinates(2).getY();
        canvas.translate(0, heightDelta / 2);

        mStarPath.moveTo(mOuterCircle.getCoordinates(0).getX(), mOuterCircle.getCoordinates(0).getY());
        mStarPath.lineTo(mInnerCircle.getCoordinates(0).getX(), mInnerCircle.getCoordinates(0).getY());

        for (int i = 1; i < Constants.NUMBER_OF_COORDINATES; i++) {
            mStarPath.lineTo(mOuterCircle.getCoordinates(i).getX(), mOuterCircle.getCoordinates(i).getY());
            mStarPath.lineTo(mInnerCircle.getCoordinates(i).getX(), mInnerCircle.getCoordinates(i).getY());
        }

        mStarPath.lineTo(mOuterCircle.getCoordinates(0).getX(), mOuterCircle.getCoordinates(0).getY());
        mStarPath.close();
        canvas.drawPath(mStarPath, mStrokePaint);

        /*
         * clipPath can be used to fill the star partially
         * https://stackoverflow.com/questions/32691499/android-fill-path-with-color-partially
         */
        canvas.clipPath(mStarPath);

        // Positions 1, 0, 4 and 2 represent left, top, right and bottom
        mRectF.set(mOuterCircle.getCoordinates(1).getX(), mOuterCircle.getCoordinates(0).getY(), mOuterCircle.getCoordinates(4).getX(), mOuterCircle.getCoordinates(2).getY());
        fillRating(mRating, mRectF, canvas, mRectPaint);
    }

    private void fillRating(int rating, RectF rectF, Canvas canvas, Paint paint) {
        float availableWidth = rectF.right - rectF.left;

        float fillRight = (rating * availableWidth) / 10f;
        fillRight += rectF.left;

        canvas.drawRect(rectF.left, rectF.top, fillRight, rectF.bottom, paint);
    }

    public void setRating(int rating) {
        if (rating == mRating) {
            return;
        }

        mRating = rating;

        invalidate();
    }

    public int getRating() {
        return this.mRating;
    }

    public void setColor(@ColorInt int color) {
        if (color == mColor) {
            return;
        }

        mColor = color;
        mStrokePaint.setColor(mColor);
        mRectPaint.setColor(mColor);

        invalidate();
    }

    public void setColorResource(@ColorRes int colorResource) {
        setColor(ContextCompat.getColor(getContext(), colorResource));
    }

    public int getColor() {
        return this.mColor;
    }
}
