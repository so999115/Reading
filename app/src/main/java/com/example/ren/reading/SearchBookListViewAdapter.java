package com.example.ren.reading;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by so999 on 2017-10-23.
 *
 */

public class SearchBookListViewAdapter extends ArrayAdapter{
    private Context context;
    private ArrayList<BookInfo> book;

    public SearchBookListViewAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context,textViewResourceId, objects);

        this.context= context;
        book=objects;

    }

    private class ViewHolder
    {
        TextView bookname;
        TextView writer;
        TextView publisher;
        TextView page;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder=null;
        if (convertView == null)
        {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.search_listview_item, null);

            holder = new ViewHolder();
            holder.bookname = (TextView) convertView.findViewById(R.id.bookname);
            holder.writer = (TextView) convertView.findViewById(R.id.writer);
            holder.publisher=(TextView)convertView.findViewById(R.id.publisher);
            holder.page=(TextView)convertView.findViewById(R.id.page);

            convertView.setTag(holder);

        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        BookInfo individualBook= book.get(position);
        holder.bookname.setText(individualBook.getBookname() );
        holder.writer.setText(individualBook.getWriter());
        holder.publisher.setText(individualBook.getPublisher());
        holder.page.setText(individualBook.getPage());


        return convertView;


    }
}