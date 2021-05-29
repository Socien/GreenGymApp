package com.example.greengym;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Search_byEquip extends AppCompatActivity {

    private Spinner up, down, all, stretch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_equip);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("운동 기구로 검색");

        up = (Spinner) findViewById(R.id.up);
        down = (Spinner) findViewById(R.id.down);
        all = (Spinner) findViewById(R.id.all);
        stretch = (Spinner) findViewById(R.id.stretch);

        //상체 운동기구
        ArrayAdapter upAdapter = ArrayAdapter.createFromResource(this, R.array.search_upArray, R.layout.spinner);
        upAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        up.setAdapter(upAdapter);

        up.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(up.getSelectedItemPosition() != 0){
                    Intent intent = new Intent(getApplicationContext(), Search_Map.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        //하체 운동기구
        ArrayAdapter downAdapter = ArrayAdapter.createFromResource(this, R.array.search_downArray, R.layout.spinner);
        downAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        down.setAdapter(downAdapter);

        down.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(down.getSelectedItemPosition() != 0){
                    Intent intent = new Intent(getApplicationContext(), Search_Map.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        //전신 운동기구
        ArrayAdapter allAdapter = ArrayAdapter.createFromResource(this, R.array.search_allArray, R.layout.spinner);
        allAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        all.setAdapter(allAdapter);

        all.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(all.getSelectedItemPosition() != 0){
                    Intent intent = new Intent(getApplicationContext(), Search_Map.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        //스트레칭 기구
        ArrayAdapter stretchAdapter = ArrayAdapter.createFromResource(this, R.array.search_stretchArray, R.layout.spinner);
        stretchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stretch.setAdapter(stretchAdapter);

        stretch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(stretch.getSelectedItemPosition() != 0){
                    Intent intent = new Intent(getApplicationContext(), Search_Map.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }
}