package com.example.filetransfer;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Intent.EXTRA_ALLOW_MULTIPLE;

public class Filedrawer extends AppCompatActivity {


    /*extView Show_PATH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filedrawer);
        selectImage();


    }
    static final int REQUEST_IMAGE_GET = 1;
    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*"); //allows any image file type. Change * to specific extension to limit it
//**The following line is the important one!
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_IMAGE_GET); //SELECT_PICTURES is simply a global int used to check the calling intent in onActivityResult
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_GET) {
            if(resultCode == Activity.RESULT_OK) {
                if(data.getClipData() != null) {
                    int count = data.getClipData().getItemCount(); //evaluate the count before the for loop --- otherwise, the count is evaluated every loop.
                    ArrayList<Uri> imageUri=new ArrayList<Uri>();

                    for(int i = 0; i < count; i++)
                        imageUri .add(data.getClipData().getItemAt(i).getUri());
                    //do something with the image (save it to some directory or whatever you need to do with it here)
                    Toast.makeText(this, ""+imageUri.size(), Toast.LENGTH_SHORT).show();
                    Show_PATH.setText(imageUri.toString());

                }
            } else if(data.getData() != null) {
                String imagePath = data.getData().getPath();
                //do something with the image (save it to some directory or whatever you need to do with it here)
                //Toast.makeText(this, ""+imageU.size(), Toast.LENGTH_SHORT).show();
                Show_PATH.setText(imagePath);

            }
        }
    }*/
    TextView Show_PATH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filedrawer);
        selectImage();
    }

    static final int REQUEST_GET = 1;

    public void selectImage() {
        Intent actionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        actionIntent.putExtra(EXTRA_ALLOW_MULTIPLE, true);
        actionIntent.setType("image/*");

        if (actionIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(actionIntent, REQUEST_GET);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GET && resultCode == RESULT_OK) {
            //  Bitmap thumbnail = data.getParcelable("data");
            Uri fullPhotoUri = data.getData();
            // Do work with photo saved at fullPhotoUri
            Show_PATH = findViewById(R.id.showpath);
            Show_PATH.setText(fullPhotoUri.getPath());
            Toast.makeText(this, fullPhotoUri.getPath(), Toast.LENGTH_SHORT).show();

        }

    }
}
