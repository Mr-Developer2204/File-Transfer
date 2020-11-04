package com.example.filetransfer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FileActivity extends AppCompatActivity {

    static final int REQUEST_GET=1;
    public static String file_path;
    public static int choose =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.files_activity);

        TextView image = (TextView) findViewById(R.id.numbers);
        // Set a click listener on that View

        image.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {

                Intent myimageIntent = new Intent(Intent.ACTION_GET_CONTENT);
                myimageIntent.setType("image/*");
                startActivityForResult(myimageIntent, REQUEST_GET);


            }
        });

        TextView video = (TextView) findViewById(R.id.colors);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myvideoIntent = new Intent(Intent.ACTION_GET_CONTENT);
                myvideoIntent.setType("video/*");
                startActivityForResult(myvideoIntent, REQUEST_GET);
            }
        });

        TextView other = (TextView) findViewById(R.id.phrases);

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myfileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                myfileIntent.setType("*/*");
                startActivityForResult(myfileIntent, REQUEST_GET);
            }
        });

        TextView audio = (TextView) findViewById(R.id.family);
        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myaudioIntent = new Intent(Intent.ACTION_GET_CONTENT);
                myaudioIntent.setType("audio/*");
                startActivityForResult(myaudioIntent, REQUEST_GET);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        file_path="";
        if(requestCode==REQUEST_GET && resultCode==RESULT_OK){
            Uri fullPhoto = data.getData();
            file_path=fullPhoto.getPath()+"";
            Intent intent = new Intent(FileActivity.this,Server.class);
            intent.putExtra("file",file_path);
            intent.putExtra("selected",fullPhoto);
            startActivity(intent);
        }
    }

}