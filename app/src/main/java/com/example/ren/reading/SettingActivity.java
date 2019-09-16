package com.example.ren.reading;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


//사용자 정보들 아직 안건드림


public class SettingActivity extends AppCompatActivity {
    private BackBtnCloseHandler backBtnCloseHandler;

    private Button mBtnLogout;
    MyBookDBHelper mydb = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        backBtnCloseHandler = new BackBtnCloseHandler(this);


        //사용자 정보
        TextView IDType = (TextView)findViewById(R.id.IDType) ;
        TextView email = (TextView)findViewById(R.id.email) ;

        IDType.setText(GlobalApplication.gMemberInfo.getMemberIDInfo());
        email.setText(GlobalApplication.gMemberInfo.getMemberEmailInfo());

        //별명
        mydb = new MyBookDBHelper(this);
        TextView lev = (TextView)findViewById(R.id.lev) ;

        Integer x = Integer.parseInt(mydb.genre_state_total());
        if(x<=3){
            lev.setText("책 병아리");
        }
        else if(x<=10){
            lev.setText("다독가");
        }

        else if(x<=20){
            lev.setText("책벌레");
        }
        else if(x<=40){
            lev.setText("작가 지망생");
        }
        else{
            lev.setText("책 그자체");
        }


        //for kakao
        mBtnLogout = (Button) findViewById(R.id.user_logout);
        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmLogout();
            }
        });


        //통계
        Button user_stats_btn = (Button)findViewById(R.id.user_stats);
        user_stats_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, GenreStatsActivity.class);
                startActivity(intent);
            }
        });


        //문의하기
        Button send_email_btn = (Button)findViewById(R.id.send_message);
        send_email_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("plain/text");

                String[] address = {"so99115@naver.com"};
                email.putExtra(Intent.EXTRA_EMAIL, address);

                startActivity(email);

            }
        });



        ImageButton menu_home_btn = (ImageButton)findViewById(R.id.home_button); //home_button을 누르면 MainActivity로 넘어간다.
        menu_home_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton menu_library_btn = (ImageButton)findViewById(R.id.my_library_button); //my_library_button을 누르면 MyLibraryActivity로 넘어간다.
        menu_library_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, MyLibraryActivity.class);
                startActivity(intent);
            }
        });

        ImageButton menu_review_btn = (ImageButton)findViewById(R.id.review_button); //review_button을 누르면 ReviewActivity로 넘어간다.
        menu_review_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });

        ImageButton menu_diary_btn = (ImageButton)findViewById(R.id.diary_button); //diary_button을 누르면 DiaryActivity로 넘어간다.
        menu_diary_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, DiaryActivity.class);
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
            Intent intent = new Intent(SettingActivity.this, SearchBookActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //for kakao
    private void confirmLogout() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("로그아웃")
                .setMessage("로그아웃 하시겠습니까?")
                .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (GlobalApplication.gMemberInfo.isKakao())
                            LoginKakao.getInstance().logout(SettingActivity.this);
                        else
                            LoginNaver.getInstance().logout(SettingActivity.this);
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (dialogInterface != null)
                            dialogInterface.dismiss();
                    }
                })
                .create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        backBtnCloseHandler.onBackPressed();
    }
}