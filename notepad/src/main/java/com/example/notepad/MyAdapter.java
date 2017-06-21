package com.example.notepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Home on 6/15/2017.
 */

public class MyAdapter extends BaseAdapter {

    private final Context context;
    private final List<MyItem> dataSet;
    private LayoutInflater inflater;

    public MyAdapter(Context context,List<MyItem> dataSet) {
        this.context = context;
        this.dataSet = dataSet;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = null;
        if(convertView == null){
            root = inflater.inflate(R.layout.firstscreen, parent, false);
        }else {
            root =convertView;
        }


        ((ImageView) root.findViewById(R.id.imgIcon)).setImageResource(dataSet.get(position).imgId);
       // ((TextView)root.findViewById(R.id.txtFileName)).setText(dataSet.get(position).txtFlName);
       // ((TextView)root.findViewById(R.id.txtFileLastModified)).setText(dataSet.get(position).txtFlMdfd);
        //((TextView)root.findViewById(R.id.txtFileSize)).setText(dataSet.get(position).txtFsz);
        ((TextView)root.findViewById(R.id.txtIcon)).setText(dataSet.get(position).txtId);

        return root;

    }
}
