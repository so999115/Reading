package com.example.ren.reading;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class StartActivity extends AppCompatActivity {
    private BackBtnCloseHandler backBtnCloseHandler;

    Cursor cursor;
    SQLiteDatabase sqLiteDatabase;
    StartDBHelper startDBHelper = null;

    AlarmManager alarmManager;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        backBtnCloseHandler = new BackBtnCloseHandler(this);

        startDBHelper = new StartDBHelper(this); //첫 로그인 시 StartActivity에서 사용자가 입력한 값을 받아오기 위한 DB
        sqLiteDatabase = startDBHelper.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM start", null);

        if(cursor != null && cursor.moveToFirst()){ //startDB에 내용이 있으면, MainActivity로 넘어감
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            cursor.close();
        }

        final EditText answer1_month = (EditText) findViewById(R.id.answer1_month_view); //추후 서버로 데이터를 전송할 때 필요함
        final EditText answer2_year = (EditText) findViewById(R.id.answer2_year_view); //추후 서버로 데이터를 전송할 때 필요함
        final EditText answer3_reading_time = (EditText) findViewById(R.id.answer3_reading_time_view); //추후 서버로 데이터를 전송할 때 필요함

        timePicker = (TimePicker) findViewById(R.id.answer4_alarm_time_picker);

        Button done_btn = (Button) findViewById(R.id.done_button);

        done_btn.setOnClickListener(new Button.OnClickListener(){ //done_button을 누르면 MainActivity로 넘어간다.
            public void onClick(View v) {
                String month = answer1_month.getText().toString();
                String year = answer2_year.getText().toString();
                String reading_time = answer3_reading_time.getText().toString();

                startDBHelper.insertStart(month, year, reading_time);

                setAlarm();

                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        });
    }

    private void setAlarm() {
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();

        if(Build.VERSION.SDK_INT >= 23) {
            calendar.set(Calendar.HOUR_OF_DAY, this.timePicker.getHour());
            calendar.set(Calendar.MINUTE, this.timePicker.getMinute());
        } else {
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, timePicker.getCurrentHour());
        }

        Intent alarmIntent = new Intent(this, AlarmReceiver.class); //알람 설정
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    @Override
    public void onBackPressed() {
        backBtnCloseHandler.onBackPressed();
    }
}
