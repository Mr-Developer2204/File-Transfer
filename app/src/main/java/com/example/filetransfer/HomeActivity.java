package com.example.filetransfer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;



public class HomeActivity extends AppCompatActivity {

    Button DISCOVER, SEND, RECEIVE,FILES;
    ListView listView;
    TextView read_msg, Connection_status;
    EditText wrt_msg;
    LocationManager locationManager;
    WifiManager wifiManager;
    WifiP2pManager mManager;
    WifiP2pManager.Channel mChannel;
    LocationManager Loc;
    BroadcastReceiver receiver;
    IntentFilter filter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SEND = findViewById(R.id.button);
        RECEIVE = findViewById(R.id.button2);
        DISCOVER = findViewById(R.id.button3);
        FILES = findViewById(R.id.button4);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mManager =(WifiP2pManager) (getSystemService(Context.WIFI_P2P_SERVICE));
        mChannel = mManager.initialize(this,getMainLooper(),null);
        receiver = new WifiDirectBroadcastReceiver(mManager,mChannel,this);
        filter = new IntentFilter();





        SEND.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {

                Toast.makeText(HomeActivity.this, "LOCATION & HOTSPOT IS REQUIRED", Toast.LENGTH_LONG).show();
                Intent Location = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                Intent hotspot = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                Intent[] intents = {Location, hotspot};
                startActivities(intents);
                Toast.makeText(HomeActivity.this, "LOCATION & HOTSPOT ARE TURNED ON", Toast.LENGTH_SHORT).show();
                Toast.makeText(HomeActivity.this,"LOCATION & HOTSPOT ARE REQUIRED",Toast.LENGTH_LONG).show();

            }
        });
        RECEIVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callWifiSetting = new Intent(Settings.ACTION_WIFI_SETTINGS);
                Toast.makeText(HomeActivity.this,"WIFI is required",Toast.LENGTH_LONG).show();
                startActivity(callWifiSetting);
                filter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
                filter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
                filter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
                filter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
                if(wifiManager.isWifiEnabled()){
                    Toast.makeText(HomeActivity.this,"Wifi is turning ON",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(HomeActivity.this,"WIFI is required",Toast.LENGTH_LONG).show();
                }

            }
        });


        DISCOVER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent discover = new Intent(Settings.ACTION_APN_SETTINGS);
                startActivity(discover);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void clickTemporary(View view){
    Intent file =new Intent(HomeActivity.this,FileActivity.class);
    startActivity(file);
    }

}