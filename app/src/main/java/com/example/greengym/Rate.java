package com.example.greengym;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Rate extends AppCompatActivity {
    private Button startBtn, stopBtn, statistic;
    private TextView timeTextView, max_recordTextView, recordTextView;
    private Thread timeThread = null;
    private Boolean isRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("운동량 측정");

        startBtn = (Button) findViewById(R.id.start);
        stopBtn = (Button) findViewById(R.id.stop);
        statistic = (Button) findViewById(R.id.statistic);
        timeTextView = (TextView) findViewById(R.id.time);
        max_recordTextView = (TextView) findViewById(R.id.max_record);
        recordTextView = (TextView) findViewById(R.id.record);

        //시작 버튼
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeThread = new Thread(new timeThread());
                timeThread.start();
            }
        });

        //정지 버튼
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordTextView.setText(recordTextView.getText() + timeTextView.getText().toString() + "\n");

                timeThread.interrupt();
            }
        });

        //운동량 통계
        statistic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Rate_Statistic.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int ss = msg.arg1 % 60;
            int s = (msg.arg1 / 60) % 60;
            int m = (msg.arg1 / 3600);

        @SuppressLint("DefaultLocale") String result = String.format("%02d : %02d : %02d", m, s, ss);
        timeTextView.setText(result);
        }
    };

    public class timeThread implements Runnable {
        @Override
        public void run() {
            int i = 0;

            while (true) {
                while (isRunning) {
                    Message msg = new Message();
                    msg.arg1 = i++;
                    handler.sendMessage(msg);

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                timeTextView.setText("00 : 00 : 00");
                            }
                        });
                        return;
                    }
                }
            }
        }
    }


}