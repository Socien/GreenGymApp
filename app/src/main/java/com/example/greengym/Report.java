package com.example.greengym;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.InputStream;

public class Report extends AppCompatActivity {

    private EditText content;
    private ImageView image = null;
    private Button add, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("운동기구 고장 신고");

        content = (EditText) findViewById((R.id.report_content));
        image = (ImageView) findViewById((R.id.image));
        add = (Button) findViewById((R.id.add_image));
        next = (Button) findViewById(R.id.report_next);
        next.setEnabled(false); //버튼 비활성화

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

        //사진 추가 버튼
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               Intent intent = new Intent(Intent.ACTION_PICK);
               intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
               startActivityForResult(intent, 1);
            }
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

    //사진 추가 관련
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                try{
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    image.setImageBitmap(img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}