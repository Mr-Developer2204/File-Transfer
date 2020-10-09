package com.example.android.filetransfer;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FileActivity extends AppCompatActivity  {

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
                Intent imageIntent = new Intent(FileActivity.this, ImagesActivity.class);
                startActivity(imageIntent);
            }
        });

        TextView video=(TextView)findViewById(R.id.colors);
        video.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent videoIntent =new Intent(FileActivity.this,VideoActivity.class);
                startActivity(videoIntent);
            }
        });

        TextView other=(TextView)findViewById(R.id.phrases);

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherIntent=new Intent(FileActivity.this,OtherActivity.class);
                startActivity(otherIntent);
            }
        });

        TextView audio=(TextView)findViewById(R.id.family);
        audio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent audioIntent=new Intent(FileActivity.this,AudioActivity.class);
                startActivity(audioIntent);
            }
        });
    }

//    public void ViewNumberList(View view){
//        //the first argument of the constructor is the current activity (will useful when we press back button on our phone) ANd the second arguement : is the activity , we want.
//        //intent will be stored in 'i'
//        //and then we will call Start activity by passing intent i.
//        Intent i=new Intent(this,NumbersActivity.class);
//        startActivity(i);
//    }



}