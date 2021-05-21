package com.example.greengym;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import static android.speech.tts.TextToSpeech.ERROR;

public class Method_UpperMuscle extends AppCompatActivity {

    private TextToSpeech tts;
    private TextView text;
    private Button sound, youtube;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method__upper_muscle);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("상체근육풀기");

        text = (TextView) findViewById(R.id.text);
        sound = (Button) findViewById(R.id.sound);
        youtube = (Button) findViewById(R.id.youtube);

        //소리
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR){
                    tts. setLanguage(Locale.KOREAN);
                }
            }
        });

        //소리 버튼
        sound.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(count == 0){
                    count = 1;
                    tts.speak(text.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                }
                else if(count == 1) {
                    count = 0;
                    tts.stop();
                }
            }
        });

        //유튜브 버튼
        youtube.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String url = "https://www.youtube.com/watch?v=K2TaU_ne75I";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(tts != null){
            tts.stop();
            tts.shutdown();
        }
    }
}