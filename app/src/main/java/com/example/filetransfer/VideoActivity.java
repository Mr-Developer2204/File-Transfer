package com.example.filetransfer;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Intent.EXTRA_ALLOW_MULTIPLE;

public class VideoActivity extends AppCompatActivity {


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
        actionIntent.putExtra(EXTRA_ALLOW_MULTIPLE,true);
        actionIntent.setType("video/*");

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
            Show_PATH=findViewById(R.id.showpath);
            Show_PATH.setText(fullPhotoUri.getPath());
            Toast.makeText(this, fullPhotoUri.getPath(), Toast.LENGTH_SHORT).show();

        }
    }

}