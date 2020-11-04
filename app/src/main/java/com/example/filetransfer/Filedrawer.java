//package com.example.filetransfer;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.net.Uri;
//import android.os.Bundle;
//import android.widget.TextView;
//
//import java.io.File;
//
//public class Filedrawer extends AppCompatActivity {
//
//    int choose=0;
//    TextView Show_PATH;
//    public static String file_path;
//    static final int REQUEST_GET=1;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_filedrawer);
//
//        if(FileActivity.choose ==1) {
//            Intent myimageIntent = new Intent(Intent.ACTION_GET_CONTENT);
//            myimageIntent.setType("image/*");
//            startActivityForResult(myimageIntent, REQUEST_GET);
//
//        }
//        else if(FileActivity.choose==2){
//            Intent myvideoIntent = new Intent(Intent.ACTION_GET_CONTENT);
//            myvideoIntent.setType("video/*");
//            startActivityForResult(myvideoIntent, REQUEST_GET);
//        }
//        else if(FileActivity.choose==4) {
//            Intent myaudioIntent = new Intent(Intent.ACTION_GET_CONTENT);
//            myaudioIntent.setType("audio/*");
//            startActivityForResult(myaudioIntent, REQUEST_GET);
//        }
//        else if(FileActivity.choose==3){
//            Intent myfileIntent = new Intent(Intent.ACTION_GET_CONTENT);
//            myfileIntent.setType("*/*");
//            startActivityForResult(myfileIntent, REQUEST_GET);
//        }
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode==REQUEST_GET && resultCode==RESULT_OK){
//            Uri fullPhoto = data.getData();
//            file_path=fullPhoto.getPath();
//            Show_PATH = findViewById(R.id.showpath);
//            Show_PATH.setText("Path: "+"\n"+fullPhoto.getPath());
//
//        }
//    }
//
//    }