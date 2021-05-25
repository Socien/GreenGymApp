package com.example.greengym;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Method extends AppCompatActivity {

    private Button turnwaist, walkingair, surfing, uppermuscle, run,
            pushlegs, situp, shoulder, rope, liftweight;

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
        pushlegs = (Button) findViewById(R.id.pushlegs);
        situp = (Button) findViewById(R.id.situp);
        shoulder = (Button) findViewById(R.id.shoulder);
        rope = (Button) findViewById(R.id.rope);
        liftweight = (Button) findViewById(R.id.liftweight);

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
    }
}