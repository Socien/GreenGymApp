package com.example.greengym;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;

public class Report extends AppCompatActivity {

    private EditText content;
    private TextView year, month, day;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("운동기구 고장 신고");

        content = (EditText) findViewById((R.id.report_content));
        year = (TextView) findViewById(R.id.year);
        month = (TextView) findViewById(R.id.month);
        day = (TextView) findViewById(R.id.day);
        next = (Button) findViewById(R.id.report_next);

        next.setEnabled(false); //버튼 비활성화
        Calendar cal = Calendar.getInstance();
        year.setText(cal.get(Calendar.YEAR) + "년");
        month.setText((cal.get(Calendar.MONTH) + 1) + "월");
        day.setText(cal.get(Calendar.DATE) + "일");

        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 9){
                    next.setEnabled(true);
                    next.setBackgroundColor(Color.parseColor("#1EAE0E"));
                }
                else{
                    next.setEnabled(false);
                    next.setBackgroundColor(Color.parseColor("#70A6A1A1"));
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        //다음버튼
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Report_Location.class);
                startActivity(intent);
            }
        });
    }
}