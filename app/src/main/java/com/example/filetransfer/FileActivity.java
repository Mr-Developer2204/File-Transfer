package com.example.filetransfer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FileActivity extends AppCompatActivity {

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
                Intent imageIntent = new Intent(FileActivity.this, ImageActivity.class);
                startActivity(imageIntent);
            }
        });

        TextView video = (TextView) findViewById(R.id.colors);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent videoIntent = new Intent(FileActivity.this, VideoActivity.class);
                startActivity(videoIntent);
            }
        });

        TextView other = (TextView) findViewById(R.id.phrases);

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherIntent = new Intent(FileActivity.this, OtherActivity.class);
                startActivity(otherIntent);
            }
        });

        TextView audio = (TextView) findViewById(R.id.family);
        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent audioIntent = new Intent(FileActivity.this, AudioActivity.class);
                startActivity(audioIntent);
            }
        });
    }
}