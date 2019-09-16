package com.example.ren.reading;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DiaryDetailActivity extends AppCompatActivity {

    DiaryDBHelper db=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_detail);

        db= new DiaryDBHelper(this);

        TextView date = (TextView)findViewById(R.id.date_textview);
        TextView title = (TextView)findViewById(R.id.book_title_textview);
        TextView content = (TextView)findViewById(R.id.content_textview);

        Intent intent = getIntent(); // 보내온 Intent를 얻는다

        final String A = intent.getStringExtra("date");
        final String B = intent.getStringExtra("title");
        final String C = intent.getStringExtra("content");
        final String D = intent.getStringExtra("id");

        date.setText(A);
        title.setText(B);
        content.setText(C);

        //수정버튼
        final Button change = (Button) findViewById(R.id.change_button);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(
                        DiaryDetailActivity.this,
                        ChangeDiaryActivity.class
                );

                intent1.putExtra("date",A);
                intent1.putExtra("title",B);
                intent1.putExtra("content",C);
                intent1.putExtra("id",D);

            startActivity(intent1);

            }

        });

        //확인버튼
        final Button ok = (Button) findViewById(R.id.ok_button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //삭제버튼
        final Button delete = (Button) findViewById(R.id.delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                db.deletediary(D);
                Toast.makeText(getApplicationContext(), "삭제됌", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(
                        getApplicationContext(),
                        DiaryActivity.class
                );
                startActivity(intent);
            }
        });


    }
}