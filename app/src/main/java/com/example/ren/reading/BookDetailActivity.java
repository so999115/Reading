package com.example.ren.reading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;


public class BookDetailActivity extends AppCompatActivity {

    MyBookDBHelper mydb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        mydb = new MyBookDBHelper(this);


        final TextView bookname = (TextView)findViewById(R.id.bookname);
        final TextView writer = (TextView)findViewById(R.id.writer);
        final TextView publisher = (TextView)findViewById(R.id.publisher);
        final TextView page = (TextView)findViewById(R.id.page);

        final Intent intent = getIntent(); // 보내온 Intent를 얻는다

        final String b = intent.getStringExtra("bookname");
        final String w =  intent.getStringExtra("writer");
        final String pu =  intent.getStringExtra("publisher");
        final String p =  intent.getStringExtra("page");
        final String g =  intent.getStringExtra("genre");

        bookname.setText(b);
        writer.setText(w);
        publisher.setText(pu);
        page.setText(p);


        //읽는 책 추가
        Button now =  (Button) findViewById(R.id.now);
        now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.InsertNow(b, p,g,"0");
                Toast.makeText(getApplicationContext(), "읽는 책에 추가 되었습니다", Toast.LENGTH_LONG).show();
                finish();
                Intent intent1 = new Intent(getApplicationContext(),MyLibraryActivity.class);
                startActivity(intent1);

            }
        });

        //읽을 책 추가
        Button future =  (Button) findViewById(R.id.future);
        future.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.InsertFuture(b, p,g,"0");
                Toast.makeText(getApplicationContext(), "읽을 책에 추가 되었습니다", Toast.LENGTH_LONG).show();
                finish();
                Intent intent1 = new Intent(getApplicationContext(),MyLibraryFutureActivity.class);
                startActivity(intent1);

            }
        });

        //읽은 책 추가
        Button past =  (Button) findViewById(R.id.past);
        past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.InsertPast(b, p,g,"0");
                Toast.makeText(getApplicationContext(), "읽은 책에 추가 되었습니다", Toast.LENGTH_LONG).show();
                finish();
                Intent intent1 = new Intent(getApplicationContext(),MyLibraryPastActivity.class);
                startActivity(intent1);


            }
        });

    }



}