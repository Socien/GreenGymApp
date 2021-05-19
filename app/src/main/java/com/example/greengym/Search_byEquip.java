package com.example.greengym;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Search_byEquip extends AppCompatActivity {

    private Button up, down, all, stretch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_equip);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("운동 기구로 검색");

        up = (Button) findViewById(R.id.up);
        down = (Button) findViewById(R.id.down);
        all = (Button) findViewById(R.id.all);
        stretch = (Button) findViewById(R.id.stretch);

        //상체 운동기구
        up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Search_up.class);
                startActivity(intent);
            }
        });

        //하체 운동기구
        down.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Search_down.class);
                startActivity(intent);
            }
        });

        //전신 운동기구
        all.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Search_all.class);
                startActivity(intent);
            }
        });

        //스트레칭 기구
        stretch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Search_stretch.class);
                startActivity(intent);
            }
        });
    }
}