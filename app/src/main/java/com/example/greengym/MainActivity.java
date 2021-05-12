package com.example.greengym;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
//
    private Button search, method, rate, report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Green Gym");

        search = (Button) findViewById(R.id.search);
        method = (Button) findViewById(R.id.method);
        rate = (Button) findViewById(R.id.rate);
        report = (Button) findViewById(R.id.report);

        //공원 검색
        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Search.class);
                startActivity(intent);
            }
        });

        //사용 방법
        method.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Method.class);
                startActivity(intent);
            }
        });

        //운동량
        rate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Rate.class);
                startActivity(intent);
            }
        });

        //신고
        report.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Report.class);
                startActivity(intent);
            }
        });
    }
}