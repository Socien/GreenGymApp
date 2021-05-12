package com.example.greengym;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class IntroActivity extends AppCompatActivity {
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //액션바 제거
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent i = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);

    }
}