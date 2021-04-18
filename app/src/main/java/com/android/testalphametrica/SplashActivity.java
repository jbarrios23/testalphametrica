package com.android.testalphametrica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    public Timer timer;
    public static String CLASS_TAG=SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                backMainActivity();
                Log.d(CLASS_TAG,"backMainActivity");
            }
        },8000);
    }

    private void backMainActivity() {
        Intent intent=new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
    }
}