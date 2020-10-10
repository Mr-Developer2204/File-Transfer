package com.example.filetransfer;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {

    //private String[] arr={"ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT","NINE","TEN"};
    private ArrayList<String> EngWords=new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity);
//        getActionBar().setTitle("Numbers");
//        getSupportActionBar().setTitle("Numbers");

        EngWords.add("ONE");
        EngWords.add("TWO");
        EngWords.add("THREE");
        EngWords.add("FOUR");
        EngWords.add("FIVE");
        EngWords.add("SIX");
        EngWords.add("SEVEN");
        EngWords.add("EIGHT");
        EngWords.add("NINE");
        EngWords.add("TEN");

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, EngWords);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

    }

}
