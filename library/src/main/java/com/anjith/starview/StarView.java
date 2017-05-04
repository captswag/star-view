package com.anjith.starview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by anjithsasindran
 * on 04/05/17.
 */
public class StarView extends View {

    private static final int MINIMUM_WIDTH = 24;    // Minimum width of the view
    private static final int MINIMUM_HEIGHT = 24;   // Minimum height of the view

    private static final int LOWER_BOUND = 33;      // Below lower bound is the lowest rating
    private static final int UPPER_BOUND = 66;      // Above upper bound is the highest rating

    private Drawable mDrawable; // Drawable used to draw the star.
    private int mViewSize;      // View size which is min of width or height.
    private int mRating = 0;        // Rating bound is from 0 - 100, default value is 0.

    public StarView(Context context) {
        super(context);
        init(null);
    }

    public StarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.StarView);

        /** 
         * if attrs != null means user has provided value for rating.
         * Get the value using getInteger() of TypedArray a.
         */
        if (attrs != null) {
            mRating = a.getInteger(R.styleable.StarView_rating, mRating);
        }

        a.recycle();
        mDrawable = selectDrawableAccordingToRating(mRating);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = resolveSize(MINIMUM_WIDTH, widthMeasureSpec);
        int height = resolveSize(MINIMUM_HEIGHT, heightMeasureSpec);

        mViewSize = Math.min(width, height);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * centerX, centerY and halfOfViewSize is calculated for centering the drawable in case width
         * and height given by the user aren't equal (not square). If width is 100 and height is 500,
         * the canvas has to draw image with center as 50, 250. drawableBounds sets the left, top,
         * right and bottom bounds.
         */
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int halfOfViewSize = mViewSize / 2;
        Rect drawableBounds = new Rect(centerX - halfOfViewSize,
                centerY - halfOfViewSize,
                centerX + halfOfViewSize,
                centerY + halfOfViewSize);

        mDrawable.setBounds(drawableBounds);
        mDrawable.draw(canvas);
    }

    public int getRating() {
        return this.mRating;
    }

    public void setRating(int rating) {
        this.mRating = rating;
        this.mDrawable = selectDrawableAccordingToRating(this.mRating);
        invalidate();
    }

    /**
     * Computes the required drawable for the rating.
     *
     * @param rating Rating received from the user.
     * @return Drawable corresponding to the rating.
     */
    private Drawable selectDrawableAccordingToRating(int rating) {
        Drawable drawable;
        if (rating < LOWER_BOUND) {
            drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_star_border_yellow_24dp);
        } else if (rating >= LOWER_BOUND && rating <= UPPER_BOUND) {
            drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_star_half_yellow_24dp);
        } else {
            drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_star_yellow_24dp);
        }
        return drawable;
    }
}