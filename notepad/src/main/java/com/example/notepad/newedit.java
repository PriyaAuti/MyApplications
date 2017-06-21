package com.example.notepad;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.R.id.message;

public class newedit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newedit);

        onimgBack();
        onimgSave();


    }

    private void onimgBack() {

        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(newedit.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }


    private void onimgSave() {
        findViewById(R.id.imgSave).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String filename = ((EditText) findViewById(R.id.edtFileName)).getText().toString();

                if (filename.equals("")) {

                    AlertDialog.Builder builder = new AlertDialog.Builder((newedit.this));
                    builder.setIcon(R.mipmap.ic_launcher_round);
                    builder.setTitle("Filename Missing");
                    builder.setMessage("Please enter filename and then save :)");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.create().show();
                } else {
                    String flName = ((EditText) findViewById(R.id.edtFileName)).getText().toString();
                    String txtNote = ((EditText) findViewById(R.id.edtNote)).getText().toString();


                    try {
                        FileOutputStream fos = openFileOutput(flName + ".txt", MODE_APPEND);
                        fos.write(txtNote.getBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    findViewById(R.id.edtFileName).setEnabled(false);
                    Toast.makeText(newedit.this, "Note Saved Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    private void old() {
        findViewById(R.id.imgSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = ((EditText) findViewById(R.id.edtFileName)).getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("keyStr", fileName);
                Intent intent = new Intent(newedit.this, ListOfFiles.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 4568);
            }
        });

        //@Override
        // protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //  super.onActivityResult(requestCode, resultCode, data);
        //  if (requestCode == 4568) {
        //       if (resultCode == RESULT_OK) {
        //          if (data != null) {
        //              Bundle bundle = data.getExtras();
        //              String res = bundle.getString("keyRes");
        //              ((EditText) findViewById(R.id.edtFileName)).setText(res);
        //          }
        //       }
        //    }
        //   }
    }

}

