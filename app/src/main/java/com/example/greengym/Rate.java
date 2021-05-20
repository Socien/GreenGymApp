package com.example.greengym;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Rate extends AppCompatActivity {

    private Button start, stop, statistic;
    private TextView time, max_record, record;

    long startTime;
    long sumTime = 0;
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("운동량 측정");

        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        statistic = (Button) findViewById(R.id.statistic);
        time = (TextView) findViewById(R.id.time);
        max_record = (TextView) findViewById(R.id.max_record);
        record = (TextView) findViewById(R.id.record);

        //버튼 비활성화
        stop.setEnabled(false);

        //기록 스크롤
        record.setMovementMethod(new ScrollingMovementMethod());

        //운동량 통계
        statistic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Rate_Statistic.class);
                startActivity(intent);
            }
        });

        //시작 버튼
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = SystemClock.elapsedRealtime();
                Timer.sendEmptyMessage(0);

                //버튼
                start.setEnabled(false);
                start.setBackgroundColor(Color.parseColor("#70A6A1A1"));
                stop.setEnabled(true);
                stop.setBackgroundColor(Color.parseColor("#1EAE0E"));
            }
        });

        //정지 버튼
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //기록
                String result1 = String.format("%d  |  " + time.getText().toString() + "\n" + record.getText() , i);
                record.setText(result1);
                i++;

                //멈춤
                Timer.removeMessages(0);
                long nowTime = SystemClock.elapsedRealtime();
                sumTime += (nowTime - startTime);
                String result2 = String.format("%02d : %02d : %02d", (sumTime / 1000) / 60, (sumTime / 1000) % 60, (sumTime % 1000) / 10);
                max_record.setText(result2);
                time.setText("00 : 00 : 00");

                //버튼
                stop.setEnabled(false);
                stop.setBackgroundColor(Color.parseColor("#70A6A1A1"));
                start.setEnabled(true);
                start.setBackgroundColor(Color.parseColor("#1EAE0E"));
            }
        });
    }

    @SuppressLint({"DefaultLocale", "HandlerLeak"})
    Handler Timer = new Handler(){
        @Override
        public void handleMessage(Message msg){
            long nowTime = SystemClock.elapsedRealtime();
            long outTime = nowTime - startTime;
             String result = String.format("%02d : %02d : %02d", outTime / 1000 / 60, (outTime / 1000) % 60, (outTime % 1000) / 10);
            time.setText(result);
            Timer.sendEmptyMessage(0);
        }
    };
}

