package com.example.filetransfer;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OtherActivity extends AppCompatActivity {
    private ArrayList<String> other=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.others_activity);
        for(int i=1;i<=14;i++)
            other.add("File "+i);

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, other);

        ListView listView = (ListView) findViewById(R.id.otherlist);

        listView.setAdapter(itemsAdapter);

    }
}
