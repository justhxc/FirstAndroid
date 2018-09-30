package com.example.a.activitylifecycletest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NomalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nomal_activity);
    }
}
