package com.example.greengym;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Method extends AppCompatActivity {

    private Button turnwaist, walkingair, surfing, uppermuscle, run, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("사용 방법");

        turnwaist = (Button) findViewById(R.id.turnwaist);
        walkingair = (Button) findViewById(R.id.walkingair);
        surfing = (Button) findViewById(R.id.surfing);
        uppermuscle = (Button) findViewById(R.id.uppermuscle);
        run = (Button) findViewById(R.id.run);
        next = (Button) findViewById(R.id.next);

        //허리돌리기
        turnwaist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Method_TurnWaist.class);
                startActivity(intent);
            }
        });

        //공중걷기
        walkingair.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Method_WalkingAir.class);
                startActivity(intent);
            }
        });

        //파도타기
        surfing.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Method_Surfing.class);
                startActivity(intent);
            }
        });

        //상체근육풀기
        uppermuscle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Method_UpperMuscle.class);
                startActivity(intent);
            }
        });

        //달리기
        run.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Method_Run.class);
                startActivity(intent);
            }
        });

        //다음 버튼
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Method2.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}