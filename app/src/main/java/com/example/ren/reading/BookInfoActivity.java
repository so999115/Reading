package com.example.ren.reading;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class BookInfoActivity extends AppCompatActivity {
    MyBookDBHelper mydb=null;

    StartDBHelper startDBHelper = null;
    Cursor cursor;
    SQLiteDatabase sqLiteDatabase;

    String str_minute;
    CountDownTimer countDownTimer;

    static String remain_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        mydb = new MyBookDBHelper(this);

        final TextView book_name_textview = (TextView)findViewById(R.id.book_info_textview);
        final EditText memo_page_edittext = (EditText) findViewById(R.id.memo_page_edittext);

        //인텐트값 받아오기
        final Intent intent1 = getIntent();

        final String B = intent1.getStringExtra("bookname");
        final String id = intent1.getStringExtra("id");
        final String page = intent1.getStringExtra("page");
        final String genre =  intent1.getStringExtra("genre");
        final int intpage = Integer.parseInt(page);

        book_name_textview.setText(intent1.getStringExtra("bookname"));

        startDBHelper = new StartDBHelper(this);
        sqLiteDatabase = startDBHelper.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM start", null);

        final TextView answer3_reading_time = (TextView) findViewById(R.id.remain_time_answer);
        Button start_btn = (Button) findViewById(R.id.start_button);
        Button stop_btn = (Button) findViewById(R.id.stop_button);

        final Button review = (Button) findViewById(R.id.go_to_review);

        while(cursor.moveToNext()) {
            str_minute=cursor.getString(3);
        }
        cursor.close();
        sqLiteDatabase.close();

        int minute = Integer.parseInt(str_minute)*60;
        answer3_reading_time.setText(formatMinutes(minute));

        countDownTimer = new CountDownTimer(minute*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                final long millis = millisUntilFinished; //Convert milliseconds into hour,minute and seconds

                //String re_hour = Long.toString(TimeUnit.MILLISECONDS.toHours(millis));
                String re_minute = Long.toString(TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)));
                //String re_second = Long.toString(TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                answer3_reading_time.setText(hms);//set text
                remain_time = re_minute;
            }

            @Override
            public void onFinish() {
                answer3_reading_time.setText("00:00:00");
                countDownTimer.cancel();
                countDownTimer.start();
            }
        };

        start_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                countDownTimer.start();
            }
        });

        stop_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                countDownTimer.cancel();
                startDBHelper.changeReadingTime(remain_time);
            }
        });

        //다이어리 쓰러가기
        final Button button = (Button) findViewById(R.id.go_to_diary);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent2 = new Intent(
                        getApplicationContext(),
                        WriteDiaryActivity.class
                );

                intent2.putExtra("bookname",intent1.getStringExtra("bookname"));

                startActivity(intent2);
            }
        });


        final Button button2 = (Button) findViewById(R.id.save_progress);
        button2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String P = memo_page_edittext.getText().toString();
                int intP = Integer.parseInt(memo_page_edittext.getText().toString());

                if (intpage < intP){
                    Toast.makeText(getApplicationContext(), "페이지가 너무 큽니다.", Toast.LENGTH_LONG).show();
                }
                else if(intP<intpage*0.9){

                    mydb.changeprogress(P,id);

                    Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_LONG).show();
                }
                else if(intP<intpage){

                    mydb.changeprogress(P,id);
                    Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_LONG).show();

                    review.setVisibility(View.VISIBLE);

                }
                else{
                    mydb.InsertPast(B,page,genre,P);
                    mydb.deletenow(id);
                    Toast.makeText(getApplicationContext(), "완독하셨습니다. 서평 쓰는 페이지로 넘어갑니다.", Toast.LENGTH_LONG).show();
                    Intent PastIntent = new Intent(BookInfoActivity.this, WriteReviewActivity.class);
                    PastIntent.putExtra("bookname", intent1.getStringExtra("bookname"));
                    startActivity(PastIntent);
                }
            }
        });

        review.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v){
                String P = memo_page_edittext.getText().toString();
                mydb.InsertPast(B,page,genre,P);
                mydb.deletenow(id);
                Toast.makeText(getApplicationContext(), "완독하셨습니다. 서평 쓰는 페이지로 넘어갑니다.", Toast.LENGTH_LONG).show();
                Intent PastIntent = new Intent(BookInfoActivity.this, WriteReviewActivity.class);
                PastIntent.putExtra("bookname", intent1.getStringExtra("bookname"));
                startActivity(PastIntent);
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

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        finish();
    }
}
