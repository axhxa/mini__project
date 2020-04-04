package com.example.miniproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class InfoAdapter extends BaseAdapter {
    Context context;
    private ArrayList<InfoResults> list;
    private LayoutInflater layoutInflater;
    InfoResults infoResult;

    public InfoAdapter(Context context, ArrayList<InfoResults> list) {
        this.context = context;
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.item_layout,null);
            holder.title=convertView.findViewById(R.id.title);
            holder.date=convertView.findViewById(R.id.date);
            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }

        infoResult=list.get(position);
        String date=infoResult.date;
        String title=infoResult.title;
        holder.date.setText(date);
        holder.title.setText(title);
        return convertView;
    }
}

class ViewHolder{
    TextView title;
    TextView date;
}
