package com.zawxtutaung.yamethin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dell on 12/10/2016.
 */

public class DrawerListAdapter extends BaseAdapter {
    ArrayList<ListData>  listarray;
    Context context;


    public DrawerListAdapter(Context context,ArrayList<ListData> listarray) {
        this.listarray = listarray;
        this.context=context;


    }

    @Override
    public int getCount() {
        return listarray.size();
    }

    @Override
    public Object getItem(int position) {
        return listarray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listarray.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView==null){
            convertView=inflater.inflate(R.layout.drawer_list,null);
            viewHolder=new ViewHolder();
            viewHolder.listtext= (TextView) convertView.findViewById(R.id.listtext);
            viewHolder.listimg= (ImageView) convertView.findViewById(R.id.listimg);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        ListData listData= (ListData)  getItem(position);
        viewHolder.listtext.setText(listData.getListtext());
        viewHolder.listimg.setImageResource(listData.getListimg());
        return convertView;
    }
    private class ViewHolder{
        TextView listtext;
        ImageView listimg;
    }
}
