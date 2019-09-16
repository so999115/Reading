package com.example.ren.reading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ren on 2017. 9. 14..
 */
public class DiaryListViewAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<DiaryInfo> diary;

    public DiaryListViewAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context,textViewResourceId, objects);

        this.context= context;
        diary=objects;

    }

    private class ViewHolder
    {
        TextView create_at;
        TextView title;
        TextView content;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder=null;
        if (convertView == null)
        {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.diary_activity_listview, null);

            holder = new ViewHolder();
            holder.create_at = (TextView) convertView.findViewById(R.id.create_at);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.content=(TextView)convertView.findViewById(R.id.content);
            convertView.setTag(holder);

        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        DiaryInfo individualDairy= diary.get(position);
        holder.create_at.setText(individualDairy.getCreate_at() );
        holder.title.setText(individualDairy.getTitle());
        holder.content.setText(individualDairy.getContent());
        return convertView;


    }
}