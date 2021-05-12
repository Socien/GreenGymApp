package com.example.greengym;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Search extends AppCompatActivity {

    private Button position, equip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("공원 검색");

        position = (Button) findViewById(R.id.byposition);
        equip = (Button) findViewById(R.id.byequip);

        //현재 위치로 검색
       position.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Search_Map.class);
                startActivity(intent);
            }
        });

        //운동 기구로 검색
        equip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Search_byEquip.class);
                startActivity(intent);
            }
        });
    }
}