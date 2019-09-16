package com.example.ren.reading;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.Toast;
import android.widget.EditText;


import java.sql.SQLException;


//검색기능 왜 안되는거니?

public class SearchBookActivity extends AppCompatActivity {

    SearchBookListViewAdapter Adapter=null;
    SearchBookListViewAdapter Adapter2=null;
    ListView listView=null;
    BookDBHelper db=null;
    ArrayList<BookInfo> book=null;
    ArrayList<BookInfo> book2=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);


        db = new BookDBHelper(this);
        book = db.getData();
        db.close();

        Adapter = new SearchBookListViewAdapter(this, R.layout.search_listview_item, book);

        listView = (ListView) findViewById(R.id.book_info_listview);
        listView.setAdapter(Adapter);

//리스트뷰 클릭 시 인텐트로 디비 값들 넘겨줌
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        BookDetailActivity.class
                );
                intent.putExtra("bookname", book.get(position).getBookname());
                intent.putExtra("writer", book.get(position).getWriter());
                intent.putExtra("publisher", book.get(position).getPublisher());
                intent.putExtra("page", book.get(position).getPage());
                intent.putExtra("genre", book.get(position).getGenre());
                intent.putExtra("id", book.get(position).getId());

                startActivity(intent);

            }
        });

        final EditText search = (EditText)findViewById(R.id.book_search);

        String str = getIntent().getStringExtra("isbn");
        search.setText(str);

        final Button ok = (Button)findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchBookActivity2.class);
                intent.putExtra("a",search.getText().toString());
                startActivity(intent);
                finish();
            }
        });

    }


}
