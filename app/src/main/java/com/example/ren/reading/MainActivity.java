package com.example.ren.reading;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;


//nowReadAdapter 이거 받는거 다 다르니까 어뎁터랑 item 다시 만들어줘
public class MainActivity extends AppCompatActivity {
    private BackBtnCloseHandler backBtnCloseHandler;


    MainListViewAdapter MainAdapter = null;
    ListView listview;
    MyBookDBHelper mydb = null;
    ArrayList<MainInfo> MainInfo = null;

    StartDBHelper startDBHelper = null;
    Cursor cursor;
    SQLiteDatabase sqLiteDatabase;
    String str_minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new MyBookDBHelper(this);
        MainInfo =mydb.maingetData();
        MainAdapter = new MainListViewAdapter(this,R.layout.my_library_listview_item,MainInfo);

        listview = (ListView)findViewById(R.id.book_info_listview);
        listview.setAdapter(MainAdapter);


        //리스트뷰 클릭 시 인텐트로 디비 값들 넘겨줌.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        BookInfoActivity.class
                );
                intent.putExtra("bookname",MainInfo.get(position).getBookname());
                intent.putExtra("page",MainInfo.get(position).getPage());
                intent.putExtra("genre",MainInfo.get(position).getGenre());
                intent.putExtra("id",MainInfo.get(position).getId());

                startActivity(intent);

            }
        });

        startDBHelper = new StartDBHelper(this);
        sqLiteDatabase = startDBHelper.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM start", null);

        backBtnCloseHandler = new BackBtnCloseHandler(this);

        TextView answer2_year = (TextView) findViewById(R.id.desire_reading_book_answer);
        TextView answer3_reading_time = (TextView) findViewById(R.id.remain_time_answer);

        while(cursor.moveToNext()) {
            answer2_year.append(cursor.getString(2));
            str_minute=cursor.getString(3);
        }
        cursor.close();
        sqLiteDatabase.close();

        int minute = Integer.parseInt(str_minute)*60;
        answer3_reading_time.setText(formatMinutes(minute));

        //읽은 권수 카운트
        TextView reading_book = (TextView) findViewById(R.id.reading_book_answer);
        reading_book.setText(mydb.genre_state_total());

        ImageButton menu_library_btn = (ImageButton)findViewById(R.id.my_library_button); //my_library_button을 누르면 MyLibraryActivity로 넘어간다.
        menu_library_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyLibraryActivity.class);
                startActivity(intent);
            }
        });

        ImageButton menu_review_btn = (ImageButton)findViewById(R.id.review_button); //review_button을 누르면 ReviewActivity로 넘어간다.
        menu_review_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });

        ImageButton menu_diary_btn = (ImageButton)findViewById(R.id.diary_button); //diary_button을 누르면 DiaryActivity로 넘어간다.
        menu_diary_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
                startActivity(intent);
            }
        });

        ImageButton menu_setting_btn = (ImageButton)findViewById(R.id.user_setting_button); //user_setting_button을 누르면 SettingActivity로 넘어간다.
        menu_setting_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

    }

    public static String formatMinutes(int int_minute)
    {
        int hours = int_minute / 3600;
        int secondsLeft = int_minute - hours * 3600;
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String formattedTime = "";
        if (hours < 10)
            formattedTime += "0";
        formattedTime += hours + ":";

        if (minutes < 10)
            formattedTime += "0";
        formattedTime += minutes + ":";

        if (seconds < 10)
            formattedTime += "0";
        formattedTime += seconds ;

        return formattedTime;
    }

    //액션버튼 메뉴 액션바에 집어 넣기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //액션버튼을 클릭했을 때의 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_search) {
            Intent intent = new Intent(MainActivity.this, SearchBookActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        backBtnCloseHandler.onBackPressed();
    }
}