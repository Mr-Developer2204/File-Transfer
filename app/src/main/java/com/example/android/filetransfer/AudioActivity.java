package com.example.android.filetransfer;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AudioActivity extends AppCompatActivity {

    private ArrayList<String> audios=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.audio_activity);
        for(int i=1;i<13;i++)
        audios.add("Audio "+i);

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, audios);

        ListView listView = (ListView) findViewById(R.id.audiolist);

        listView.setAdapter(itemsAdapter);

    }
}
