package com.example.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.FileNameMap;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListOfFiles extends AppCompatActivity {

    public static final String KEY_NAME = "fileName";
    long size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_files);


        loadFileList();


       // Intent responsibleIntent = getIntent();
       // Bundle bundle = responsibleIntent.getExtras();
       // if (bundle != null) {
       //     String fileName = bundle.getString("keyStr");
       //     ((TextView) findViewById(R.id.edtshowfname)).setText(fileName);
        //}

        findViewById(R.id.imgListBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentList = new Intent(ListOfFiles.this,MainActivity.class);
                startActivity(intentList);
                finish();
            }
        });


    }

    private void loadFileList(){
        //To Load all the files you saved in your notepad app.
        final List<MyMenu> fileItems = new ArrayList<>();

        final File file = getFilesDir();
        File [] files = file.listFiles();

        for(File f1 :files){
            Date date = new Date(f1.lastModified());

            size = f1.length();
            fileItems.add(new MyMenu(R.drawable.ic_note_add_black_24dp,""+f1.getName(),""+(new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss").format(date)),SizeChange(size),""));
        }

        ((ListView)findViewById(R.id.lstFiles)).setAdapter(new FileAdapter(this,fileItems));


        ((ListView)findViewById(R.id.lstFiles)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyMenu clickedItem = fileItems.get(position);
                String strFileName = clickedItem.txtFlName.toString();

                //Reading the content of file with particular file name..(Open file where file name = xyz)
                StringBuilder builder  = new StringBuilder();
                FileInputStream fis = null;

                try {
                    fis = openFileInput(strFileName);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                while (true){
                    int ch = 0;

                    try {
                        ch = fis.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if(ch == -1) break;
                    else {
                        builder.append((char) ch);
                    }
                }


                String strNoteInfo = builder.toString();

                Bundle bundle = new Bundle();

                //Puting file name into bundle

                bundle.putString(KEY_NAME,strFileName);

                startActivity(new Intent(ListOfFiles.this,EditFile.class).putExtras(bundle));
            }
        });
    }


    private String SizeChange(long size){

                        String hrSize = null;

                       double b = size;
               double k = size/1024.0;
               double m = ((size/1024.0)/1024.0);
               double g = (((size/1024.0)/1024.0)/1024.0);
               double t = ((((size/1024.0)/1024.0)/1024.0)/1024.0);

                        DecimalFormat dec = new DecimalFormat("0.00");

                        if ( t>1 ) {
                        hrSize = dec.format(t).concat(" TB");
                    } else if ( g>1 ) {
                        hrSize = dec.format(g).concat(" GB");
                    } else if ( m>1 ) {
                        hrSize = dec.format(m).concat(" MB");
                   } else if ( k>1 ) {
                       hrSize = dec.format(k).concat(" KB");
                   } else {
                       hrSize = dec.format(b).concat(" Bytes");
                   }

                        return hrSize;
            }
}
