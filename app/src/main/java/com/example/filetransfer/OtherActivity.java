package com.example.filetransfer;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static android.content.Intent.EXTRA_ALLOW_MULTIPLE;

public class OtherActivity extends AppCompatActivity {
    String[] mimeTypes =
            {"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
                    "application/vnd.ms-powerpoint", "application/vnd.openxmlformats-officedocument.presentationml.presentation", // .ppt & .pptx
                    "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xls & .xlsx
                    "text/plain",
                    "application/pdf",
                    "application/zip", "application/vnd.android.package-archive"};
    private TextView Show_PATH;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filedrawer);
        selectFile();
    }

    static final int REQUEST_IMAGE_GET = 1;
    public void selectFile() {
        Intent actionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        actionIntent.putExtra(EXTRA_ALLOW_MULTIPLE,true);
        actionIntent.setType("*/*"); // or ACTION_OPEN_DOCUMENT
        actionIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        actionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        actionIntent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);

        if (actionIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(actionIntent, REQUEST_IMAGE_GET);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            //  Bitmap thumbnail = data.getParcelable("data");

            Uri fullPhotoUri = data.getData();
            // Do work with photo saved at fullPhotoUri
            Show_PATH=findViewById(R.id.showpath);
            Show_PATH.setText(fullPhotoUri.getPath());
            Toast.makeText(this, fullPhotoUri.getPath(), Toast.LENGTH_SHORT).show();

        }
    }


}
