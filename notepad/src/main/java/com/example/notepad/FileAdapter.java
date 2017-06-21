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
 * Created by Home on 6/20/2017.
 */

public class FileAdapter extends BaseAdapter {
    private final Context context;
    private final List<MyMenu> filedataSet;
    private LayoutInflater inflater;

    public FileAdapter(Context context, List<MyMenu> filedataSet) {
        this.context = context;
        this.filedataSet = filedataSet;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return filedataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return filedataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = null;
        if(convertView == null){
            root = inflater.inflate(R.layout.singlegrid,parent,false);
        }
        else{
            root = convertView;
        }

        ((ImageView)root.findViewById(R.id.imgIcon)).setImageResource(filedataSet.get(position).imgId);
        ((TextView)root.findViewById(R.id.txtFileName)).setText(filedataSet.get(position).txtFlName);
        ((TextView)root.findViewById(R.id.txtFileLastModified)).setText(filedataSet.get(position).txtFlMdfd);
        ((TextView)root.findViewById(R.id.txtFileSize)).setText(filedataSet.get(position).txtFsz);

        return root;
    }
}
