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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Report extends AppCompatActivity {

    private TextView date;
    private Spinner park;
    private EditText content, name, phone;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("운동기구 고장 신고");

        date = (TextView) findViewById(R.id.date);
        park = (Spinner) findViewById(R.id.park_spinner);
        content = (EditText) findViewById(R.id.content);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        ok = (Button) findViewById(R.id.report_ok);

        ok.setEnabled(false);
        Calendar cal = Calendar.getInstance();
        date.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH));

        //공원 선택
        ArrayAdapter parkAdapter = ArrayAdapter.createFromResource(this, R.array.park_nameArray, android.R.layout.simple_spinner_dropdown_item);
        parkAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        park.setAdapter(parkAdapter);

        //신고 내용
        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name_check = name.getText().toString();
                String phone_check = phone.getText().toString();

                if(s.length() > 9 && (name_check).length() > 1 && (phone_check).length() > 10){
                    ok.setEnabled(true);
                    ok.setBackgroundColor(Color.parseColor("#1EAE0E"));
                }
                else if(s.length() < 10 || (name_check).length() < 2 || (phone_check).length() < 11){
                    ok.setEnabled(false);
                    ok.setBackgroundColor(Color.parseColor("#70A6A1A1"));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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

        //제출 버튼
        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //통신
                sendRequest();

            }
        });
    }
    public void sendRequest(){

        String url = "http://15.164.250.186:8000/api/v1/report/insert";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response){

                AlertDialog.Builder log = new AlertDialog.Builder(Report.this);
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

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){
                //textView.append("에러 -> " + error.getMessage());

            }
        }) {
            @Override //Post방식
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("p_name", park.getSelectedItem().toString());
                params.put("r_text", content.getText().toString());
                params.put("r_phone", phone.getText().toString());
                params.put("r_name", name.getText().toString());
                params.put("r_date", date.getText().toString());
                return params;
            }
        };

        jsonObjectRequest.setShouldCache(false); //이전 결과 있어도 새로 요청하여 응답을 보여준다.
        AppHelper.requestQueue = Volley.newRequestQueue(this); // requestQueue 초기화 필수
        AppHelper.requestQueue.add(jsonObjectRequest);

    }
}