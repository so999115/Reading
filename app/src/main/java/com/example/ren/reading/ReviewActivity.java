package com.example.ren.reading;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ReviewActivity extends AppCompatActivity {
    private BackBtnCloseHandler backBtnCloseHandler;

    FR_AllReview fr_allReview;
    FR_MybookReview fr_mybookReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        backBtnCloseHandler = new BackBtnCloseHandler(this);

        fr_allReview = new FR_AllReview();
        fr_mybookReview = new FR_MybookReview();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fr_allReview).commit();

        TabLayout tabs = (TabLayout)findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("모든서평"));
        tabs.addTab(tabs.newTab().setText("내서평"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("ReviewActivity", "선택된 탭 : " + position);

                Fragment selected = null;
                if (position == 0) {
                    selected = fr_allReview;
                } else if (position == 1) {
                    selected = fr_mybookReview;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        ImageButton menu_home_btn = (ImageButton)findViewById(R.id.home_button); //home_button을 누르면 MainActivity로 넘어간다.
        menu_home_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ReviewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton menu_library_btn = (ImageButton)findViewById(R.id.my_library_button); //my_library_button을 누르면 MyLibraryActivity로 넘어간다.
        menu_library_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ReviewActivity.this, MyLibraryActivity.class);
                startActivity(intent);
            }
        });

        ImageButton menu_diary_btn = (ImageButton)findViewById(R.id.diary_button); //diary_button을 누르면 DiaryActivity로 넘어간다.
        menu_diary_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ReviewActivity.this, DiaryActivity.class);
                startActivity(intent);
            }
        });

        ImageButton menu_setting_btn = (ImageButton)findViewById(R.id.user_setting_button); //user_setting_button을 누르면 SettingActivity로 넘어간다.
        menu_setting_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ReviewActivity.this, SettingActivity.class);
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
            Intent intent = new Intent(ReviewActivity.this, SearchBookActivity.class);
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
