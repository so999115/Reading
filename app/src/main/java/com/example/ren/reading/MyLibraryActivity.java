package com.example.ren.reading;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

//기본적으로 now read book이 되어있음

public class MyLibraryActivity extends AppCompatActivity {
    private BackBtnCloseHandler backBtnCloseHandler;

    NowReadAdapter nowReadAdapter = null;
    ListView listview;
    MyBookDBHelper mydb = null;
    ArrayList<NowReadBookInfo> nowReadBookInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_library);

        getWindow().setWindowAnimations(0);

        mydb = new MyBookDBHelper(this);
        nowReadBookInfo =mydb.ngetData();
        nowReadAdapter = new NowReadAdapter(this,R.layout.my_library_listview_item,nowReadBookInfo);

        listview = (ListView)findViewById(R.id.book_listview);
        listview.setAdapter(nowReadAdapter);


        backBtnCloseHandler = new BackBtnCloseHandler(this);

        ImageButton menu_home_btn = (ImageButton)findViewById(R.id.home_button); //home_button을 누르면 MainActivity로 넘어간다.
        menu_home_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MyLibraryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton menu_review_btn = (ImageButton)findViewById(R.id.review_button); //review_button을 누르면 ReviewActivity로 넘어간다.
        menu_review_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MyLibraryActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });

        ImageButton menu_diary_btn = (ImageButton)findViewById(R.id.diary_button); //diary_button을 누르면 DiaryActivity로 넘어간다.
        menu_diary_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MyLibraryActivity.this, DiaryActivity.class);
                startActivity(intent);
            }
        });

        ImageButton menu_setting_btn = (ImageButton)findViewById(R.id.user_setting_button); //user_setting_button을 누르면 SettingActivity로 넘어간다.
        menu_setting_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MyLibraryActivity.this, SettingActivity.class);
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
            Intent intent = new Intent(MyLibraryActivity.this, SearchBookActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onMoreButtonClick(View button) {
        //PopupMenu 객체 생성
        PopupMenu more_popup = new PopupMenu(this, button);

        //설정한 popup XML을 inflate
        more_popup.getMenuInflater().inflate(R.menu.library_more_popup_menu, more_popup.getMenu());

        //팝업메뉴 클릭시 이벤트
        more_popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.now_read_book_menu:
                        Toast.makeText(MyLibraryActivity.this, "읽고있는 책", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.future_read_book_menu:
                        Intent intent1 = new Intent(MyLibraryActivity.this, MyLibraryFutureActivity.class);
                        startActivity(intent1);
                        Toast.makeText(MyLibraryActivity.this, "읽을 책", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.past_read_book_menu:
                        Intent intent2 = new Intent(MyLibraryActivity.this, MyLibraryPastActivity.class);
                        Toast.makeText(MyLibraryActivity.this, "읽었던 책", Toast.LENGTH_SHORT).show();
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });
        more_popup.show();
    }

    public void onAddButtonClick(View button) {
        //PopupMenu 객체 생성
        PopupMenu add_popup = new PopupMenu(this, button);

        //설정한 popup XML을 inflate
        add_popup.getMenuInflater().inflate(R.menu.library_add_popup_menu, add_popup.getMenu());

        //팝업메뉴 클릭시 이벤트
        add_popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search_book: //책 검색을 선택했을 때 이벤트 실행 코드 작성
                        Intent intent = new Intent(MyLibraryActivity.this, SearchBookActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.barcode_input: //바코드 입력을 선택했을 때 이벤트 실행 코드 작성
                        startBarcode();
                        break;

                }
                return true;
            }
        });
        add_popup.show();

    }

    public void startBarcode(){
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //바코드가 스캔되면 onAcitivityResult로 들어옴
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(this, SearchBookActivity.class);
                intent.putExtra("isbn",result.getContents());
                startActivity(intent);
                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show(); //스캔한 바코드 값을 toast로 보여줌.
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        backBtnCloseHandler.onBackPressed();
    }
}