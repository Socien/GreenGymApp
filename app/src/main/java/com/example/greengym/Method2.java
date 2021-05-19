package com.example.greengym;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Method2 extends AppCompatActivity {

    private Button pushlegs, situp, shoulder, rope, liftweight, before;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method2);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("사용 방법");

        pushlegs = (Button) findViewById(R.id.pushlegs);
        situp = (Button) findViewById(R.id.situp);
        shoulder = (Button) findViewById(R.id.shoulder);
        rope = (Button) findViewById(R.id.rope);
        liftweight = (Button) findViewById(R.id.liftweight);
        before = (Button) findViewById(R.id.before);

        //오금펴기
        pushlegs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Method_PushLegs.class);
                startActivity(intent);
            }
        });

        //윗몸일으키기
        situp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Method_SitUp.class);
                startActivity(intent);
            }
        });

        //어깨유연성운동
        shoulder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Method_Shoulder.class);
                startActivity(intent);
            }
        });

        //로프당기기
        rope.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Method_Rope.class);
                startActivity(intent);
            }
        });

        //역기올리기
        liftweight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Method_LiftWeight.class);
                startActivity(intent);
            }
        });

        //이전 버튼
        before.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Method.class);
                startActivity(intent);
            }
        });
    }
}