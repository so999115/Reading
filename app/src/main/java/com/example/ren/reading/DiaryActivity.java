package com.example.ren.reading;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DiaryActivity extends AppCompatActivity {
    private BackBtnCloseHandler backBtnCloseHandler;


    DiaryListViewAdapter Adapter=null;
    ListView listView=null;
    DiaryDBHelper db=null;
    ArrayList<DiaryInfo> diary=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        db= new DiaryDBHelper(this);
        diary=db.getData();


        Adapter= new DiaryListViewAdapter(this,R.layout.diary_activity_listview,diary);

        listView = (ListView) findViewById(R.id.diary_listview);
        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        DiaryDetailActivity.class
                );
                intent.putExtra("date",diary.get(position).getCreate_at());
                intent.putExtra("title",diary.get(position).getTitle());
                intent.putExtra("content",diary.get(position).getContent());
                intent.putExtra("id",diary.get(position).getId());

                startActivity(intent);

            }
        });

        backBtnCloseHandler = new BackBtnCloseHandler(this);

        ImageButton add_btn = (ImageButton)findViewById(R.id.add_button);
        add_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(DiaryActivity.this, WriteDiaryActivity.class);
                startActivity(intent);
                Adapter.notifyDataSetChanged();
            }
        });

        ImageButton menu_home_btn = (ImageButton)findViewById(R.id.home_button); //home_button을 누르면 MainActivity로 넘어간다.
        menu_home_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(DiaryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton menu_library_btn = (ImageButton)findViewById(R.id.my_library_button); //my_library_button을 누르면 MyLibraryActivity로 넘어간다.
        menu_library_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(DiaryActivity.this, MyLibraryActivity.class);
                startActivity(intent);
            }
        });

        ImageButton menu_review_btn = (ImageButton)findViewById(R.id.review_button); //review_button을 누르면 ReviewActivity로 넘어간다.
        menu_review_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(DiaryActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });

        ImageButton menu_setting_btn = (ImageButton)findViewById(R.id.user_setting_button); //user_setting_button을 누르면 SettingActivity로 넘어간다.
        menu_setting_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(DiaryActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

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
            Intent intent = new Intent(DiaryActivity.this, SearchBookActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        backBtnCloseHandler.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Adapter.notifyDataSetChanged();

    }


}
