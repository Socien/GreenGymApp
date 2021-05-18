package com.example.greengym;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button search, method, rate, report;
    private long backTime;

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
    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis() > backTime + 2000){
            backTime = System.currentTimeMillis();
            Toast.makeText(this, "'뒤로' 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(System.currentTimeMillis() <= backTime + 2000){
            finish();
            return;
        }
    }
}