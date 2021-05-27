package com.example.greengym;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Report_Location extends AppCompatActivity {

    private TextView date;
    private EditText name, phone;
    private Spinner city, gu;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report__location);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("운동기구 고장 신고");

        name = (EditText) findViewById((R.id.name));
        phone = (EditText) findViewById((R.id.phone));
        city = (Spinner) findViewById(R.id.city_spinner);
        gu = (Spinner) findViewById(R.id.gu_spinner);
        ok = (Button) findViewById(R.id.report_ok);
        ok.setEnabled(false); //버튼 비활성화

        //이름
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String phone_check = phone.getText().toString();

                if((phone_check).length() > 10 && s.length() > 1){
                    ok.setEnabled(true);
                    ok.setBackgroundColor(Color.parseColor("#1EAE0E"));
                }
                else if((phone_check).length() < 11 || s.length() < 2){
                    ok.setEnabled(false);
                    ok.setBackgroundColor(Color.parseColor("#70A6A1A1"));
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        //전화번호
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name_check = name.getText().toString();

                if((name_check).length() > 1 && s.length() > 10){
                    ok.setEnabled(true);
                    ok.setBackgroundColor(Color.parseColor("#1EAE0E"));
                }
                else if((name_check).length() < 2 || s.length() < 11){
                    ok.setEnabled(false);
                    ok.setBackgroundColor(Color.parseColor("#70A6A1A1"));
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });



        //시/도 선택
        ArrayAdapter cityAdapter = ArrayAdapter.createFromResource(this, R.array.cityArray, android.R.layout.simple_spinner_dropdown_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(cityAdapter);

        //구 선택
        ArrayAdapter guAdapter = ArrayAdapter.createFromResource(this, R.array.guArray, android.R.layout.simple_spinner_dropdown_item);
        guAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gu.setAdapter(guAdapter);

        //다음버튼
        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder log = new AlertDialog.Builder(Report_Location.this);
                log.setMessage("신고가 접수되었습니다.");
                log.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });
                log.show();
            }
        });
    }
}