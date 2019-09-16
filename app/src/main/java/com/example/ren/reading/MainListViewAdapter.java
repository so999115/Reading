package com.example.ren.reading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ren on 2017. 10. 8..
 */
//수정중
public class MainListViewAdapter extends ArrayAdapter {


    private Context context;
    private ArrayList<MainInfo> main;


    public MainListViewAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context,textViewResourceId, objects);

        this.context= context;
        main=objects;

    }
    private class ViewHolder
    {

        TextView bookname;
        ProgressBar mProgressBar;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        MainListViewAdapter.ViewHolder holder=null;
        if (convertView == null)
        {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.main_activity_listview, null);

            holder = new MainListViewAdapter.ViewHolder();

            holder.bookname = (TextView) convertView.findViewById(R.id.book_title);
            holder.mProgressBar=(ProgressBar) convertView.findViewById(R.id.progressBar);
            convertView.setTag(holder);

        }
        else {
            holder = (MainListViewAdapter.ViewHolder) convertView.getTag();
        }

        MainInfo individualMain= main.get(position);

        holder.bookname.setText(individualMain.getBookname() );
        holder.mProgressBar.setMax(Integer.parseInt(individualMain.getPage()));
        holder.mProgressBar.setProgress(Integer.parseInt(individualMain.getProgress()));


        return convertView;


    }

}

