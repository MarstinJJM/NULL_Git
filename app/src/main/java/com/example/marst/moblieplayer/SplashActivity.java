package com.example.marst.moblieplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import java.util.HashMap;

public class SplashActivity extends Activity {
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        },2000);
    }
    private boolean isstartMain = false;
    private void startMainActivity(){
        if (!isstartMain) {
            isstartMain = true;
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            //close acting...
            finish();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startMainActivity();
        return super.onTouchEvent(event);
    }
/*
    MainActivity单例模式配合使用onDestory()方法
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }*/
}
