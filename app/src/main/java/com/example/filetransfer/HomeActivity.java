package com.example.filetransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity {

    Button DISCOVER,SEND,RECEIVE;
    ListView listView;
    Switch wifi_toggle;
    TextView read_msg,Connection_status;
    EditText wrt_msg;

    WifiManager wifiManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SEND =findViewById(R.id.button);
        RECEIVE = findViewById(R.id.button2);
        DISCOVER = findViewById(R.id.button3);
        wifi_toggle = findViewById(R.id.switch2);
        wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        Connection_status = findViewById(R.id.textView3);

        if(wifiManager.isWifiEnabled()!=true) {
            Intent callWifiSetting = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivity(callWifiSetting);
        }

        // toast message
       /* new AlertDialog.Builder(this)
                .setTitle("Wifi Connection ")
                .setCancelable(false)
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HomeActivity.this.finish();
                    }
                })
                .show();
     */

    }
    private void Wifi_On_OFF()
    {

    }
    private void initialization()
    {
    }



}