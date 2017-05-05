package com.anjith.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.anjith.starview.StarView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private StarView mStarView;
    private TextView mTextRating;

    private Random mRandomNumberGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStarView = (StarView) findViewById(R.id.star_view);
        mTextRating = (TextView) findViewById(R.id.text_rating);
    }

    public void changeRating(View view) {
        mRandomNumberGenerator = new Random();
        int lowerLimit = 0;
        int upperLimit = 10;
        setRating(lowerLimit + (mRandomNumberGenerator.nextDouble() * (lowerLimit + (upperLimit - lowerLimit))));
    }

    private void setRating(double rating) {
        mStarView.setRating(rating);
        mTextRating.setText(getString(R.string.rating_placeholder, rating));
    }
}
