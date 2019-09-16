package com.example.ren.reading;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.Date;
import java.text.SimpleDateFormat;



public class WriteDiaryActivity extends AppCompatActivity {

    DiaryDBHelper db=null;
    DiaryListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);



        db= new DiaryDBHelper(this);

        final EditText etDate = (EditText) findViewById(R.id.date);
        final EditText etTitle = (EditText) findViewById(R.id.title);
        final EditText etContent= (EditText) findViewById(R.id.content);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        //출력 포멧 YMD
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd");

        etDate.setText(simpleDateFormat.format(date));

        Intent intent = getIntent();

        etTitle.setText(intent.getStringExtra("bookname"));
        etContent.setText(intent.getStringExtra("content"));

        final Button button = (Button) findViewById(R.id.ok_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = etDate.getText().toString();
                String title = etTitle.getText().toString();
                String Content = etContent.getText().toString();

                db.insertdiary(date, title, Content);

                Intent intent = new Intent(WriteDiaryActivity.this, DiaryActivity.class);
                startActivity(intent);

                finish();
            }
        });

        Button picture_btn = (Button)findViewById(R.id.picture_button);
        picture_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(WriteDiaryActivity.this, OCRActivity.class);
                //갔다와도 제목 그대로 유지 될 수 있도록!
                intent.putExtra("bookname",etTitle.getText().toString());
                startActivity(intent);
            }
        });
    }

    //야매라 교수님한테 혼날거같지만 ㄲㄲ
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, DiaryActivity.class);
        startActivity(intent);

        finish();
    }



}



