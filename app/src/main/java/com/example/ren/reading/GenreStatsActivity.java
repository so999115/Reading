package com.example.ren.reading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GenreStatsActivity extends AppCompatActivity {

    MyBookDBHelper mydb = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_stats);

        TextView total = (TextView)findViewById(R.id.total);
        TextView novel = (TextView)findViewById(R.id.novel);
        TextView essay = (TextView)findViewById(R.id.essay);
        TextView eco = (TextView)findViewById(R.id.eco);
        TextView self = (TextView)findViewById(R.id.self);
        TextView humanities = (TextView)findViewById(R.id.humanities);
        TextView diet = (TextView)findViewById(R.id.diet);
        TextView life = (TextView)findViewById(R.id.life);
        TextView religion = (TextView)findViewById(R.id.religion);
        TextView trip = (TextView)findViewById(R.id.trip);
        TextView foreign = (TextView)findViewById(R.id.foreign);
        TextView science = (TextView)findViewById(R.id.science);
        TextView kids = (TextView)findViewById(R.id.kids);
        TextView history = (TextView)findViewById(R.id.history);


        mydb = new MyBookDBHelper(this);

        total.setText(mydb.genre_state_total()+"권");
        novel.setText(mydb.genre_state("소설"));
        essay.setText(mydb.genre_state("시/에세이"));
        eco.setText(mydb.genre_state("경제/경영"));
        self.setText(mydb.genre_state("자기계발"));
        humanities.setText(mydb.genre_state("인문"));
        diet.setText(mydb.genre_state("건강"));
        life.setText(mydb.genre_state("가정/생활"));
        religion.setText(mydb.genre_state("종교"));
        trip.setText(mydb.genre_state("여행"));
        foreign.setText(mydb.genre_state("외국어"));
        science.setText(mydb.genre_state("과학"));
        kids.setText(mydb.genre_state("아동"));
        history.setText(mydb.genre_state("역사/문화"));

    }




}