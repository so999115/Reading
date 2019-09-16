package com.example.ren.reading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by so999 on 2017-11-03.
 */

public class PastReadAdapter extends ArrayAdapter{
    private Context context;
    private ArrayList<PastReadBookInfo> pastread;

    public PastReadAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context,textViewResourceId, objects);

        this.context= context;
        pastread=objects;

    }

    private class ViewHolder
    {
        TextView bookname;
        TextView page;
        TextView genre;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder=null;
        if (convertView == null)
        {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.my_library_listview_item, null);

            holder = new ViewHolder();
            holder.bookname = (TextView) convertView.findViewById(R.id.bookname);
            holder.page=(TextView)convertView.findViewById(R.id.page);
            holder.genre=(TextView)convertView.findViewById(R.id.genre);
            convertView.setTag(holder);

        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        PastReadBookInfo individualBook= pastread.get(position);
        holder.bookname.setText(individualBook.getBookname() );
        holder.page.setText(individualBook.getPage());
        holder.genre.setText(individualBook.getGenre());

        return convertView;


    }
}