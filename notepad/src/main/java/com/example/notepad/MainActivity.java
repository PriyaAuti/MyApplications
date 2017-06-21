package com.example.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class
MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<MyItem> dataSet = new ArrayList<>();

        dataSet.add(new MyItem(R.drawable.ic_note_add_black_24dp, "NEW"));
        dataSet.add(new MyItem(R.drawable.ic_edit_black_24dp, "EDIT"));

        final MyAdapter adapter = new MyAdapter(this, dataSet);
        ((GridView) findViewById(R.id.grdLayout)).setAdapter(adapter);



        ((GridView) findViewById(R.id.grdLayout)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (dataSet.get(position).equals(dataSet.get(0))) {
                    Intent intent = new Intent(MainActivity.this, newedit.class);
                    startActivity(intent);
                } else if (dataSet.get(position).equals(dataSet.get(1))) {
                    Intent intent = new Intent(MainActivity.this, ListOfFiles.class);
                    startActivity(intent);
                }
            }
        });
    }

}
