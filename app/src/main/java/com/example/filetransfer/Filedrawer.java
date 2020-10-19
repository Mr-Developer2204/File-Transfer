package com.example.filetransfer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Filedrawer extends AppCompatActivity {


    TextView Show_PATH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filedrawer);

        Show_PATH = (TextView)findViewById(R.id.showpath);
        Intent myfileIntent= new Intent(Intent.ACTION_GET_CONTENT);
        myfileIntent.setType("*/*");
        startActivityForResult(myfileIntent,10);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case 10:

                if(requestCode==RESULT_OK){

                    String path = data.getData().getPath();
                    Show_PATH.setText(path);
                }


                break;
        }

    }
}