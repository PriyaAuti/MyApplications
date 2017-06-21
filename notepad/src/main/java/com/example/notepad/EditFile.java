package com.example.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EditFile extends AppCompatActivity {


    String strTxtFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_file);

        Intent intentF = getIntent();
        Bundle bundle = intentF.getExtras();

        final  String strFileName = bundle.getString(ListOfFiles.KEY_NAME);

        ((EditText)findViewById(R.id.edtTFileName)).setText(strFileName);

        try {
            strTxtFile =loadClickedFileContent(strFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        findViewById(R.id.imgSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String afterEdit = ((EditText)findViewById(R.id.edtTNote)).getText().toString();
                if((strTxtFile.equals(afterEdit))){
                    Toast.makeText(EditFile.this,"File not edited !",Toast.LENGTH_LONG).show();
                }else {
                    ((EditText)findViewById(R.id.edtTNote)).setText("");
                    ((EditText)findViewById(R.id.edtTNote)).setText(afterEdit);
                   // FileOutputStream fos = null;
                    try {
                        FileOutputStream fos = openFileOutput(strFileName,1);
                        fos.write(afterEdit.getBytes());
                        fos.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(EditFile.this,"File Edited successfully !",Toast.LENGTH_LONG).show();

                }
            }
        });


        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditFile.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



        findViewById(R.id.imgDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filepath = getFilesDir().getAbsolutePath();
                File file = new File(filepath,strFileName);
                Boolean delFStatus = file.delete();
                Toast.makeText(EditFile.this,"Note Deleted Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditFile.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private String loadClickedFileContent(String strFileName)throws IOException{
        File file = getFilesDir();
        String str = new File(strFileName).getAbsolutePath();
        FileInputStream fis = openFileInput(strFileName);
        StringBuilder builder = new StringBuilder();

        while (true){
            int ch = fis.read();
            if(ch == -1)break;
            else builder.append((char) ch);
        }


        ((EditText)findViewById(R.id.edtTNote)).setText(builder.toString());
        String beforeEdit =builder.toString();
        return beforeEdit;
    }
}
