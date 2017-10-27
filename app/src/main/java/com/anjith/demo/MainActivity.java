package com.anjith.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openSample(View view) {
        switch (view.getId()) {
            case R.id.button_sample_1:
                startActivity(new Intent(this, Sample1Activity.class));
                break;
            case R.id.button_sample_2:
                startActivity(new Intent(this, Sample2Activity.class));
                break;
        }
    }
}