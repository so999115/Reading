package com.example.ren.reading;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MonthlyStatsActivity extends AppCompatActivity {

    MyBookDBHelper mydb = null;
    ArrayList<PastReadBookInfo> pastReadBookInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_stats);

        mydb = new MyBookDBHelper(this);
        pastReadBookInfo =mydb.pgetData();

        Button genre_stats_btn = (Button)findViewById(R.id.genre_button);
        genre_stats_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MonthlyStatsActivity.this, GenreStatsActivity.class);
                startActivity(intent);
            }
        });
    }
}
