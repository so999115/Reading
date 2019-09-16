package com.example.ren.reading;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by so999 on 2017-11-04.
 */

public class ChangeDiaryActivity extends AppCompatActivity{

    DiaryDBHelper db=null;
    DiaryListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);

        Button picture_btn = (Button)findViewById(R.id.picture_button);
        picture_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ChangeDiaryActivity.this, OCRActivity.class);
                startActivity(intent);
            }
        });

        db= new DiaryDBHelper(this);

        final EditText etDate = (EditText) findViewById(R.id.date);
        final EditText etTitle = (EditText) findViewById(R.id.title);
        final EditText etContent= (EditText) findViewById(R.id.content);


        final Intent intent = getIntent();

        etDate.setText(intent.getStringExtra("date"));
        etTitle.setText(intent.getStringExtra("title"));
        etContent.setText(intent.getStringExtra("content"));

        final String id = intent.getStringExtra("id");

        final Button button = (Button) findViewById(R.id.ok_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = etDate.getText().toString();
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();

           db.changediary(date,title,content,id);

                Intent intent1 = new Intent(ChangeDiaryActivity.this, DiaryActivity.class);
                startActivity(intent1);

                finish();

            }

        });
    }

    //디테일 액티비티 하려면 코드 더 짜줘야댐...시간나면 하겠음

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, DiaryActivity.class);
        startActivity(intent);

        finish();
    }


}
