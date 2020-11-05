package com.example.filetransfer;

import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

    Button  EnableDisable, RECEIVE, FILES, SERVER;
    WifiManager wifiManager,w;
    WifiP2pManager mManager;
    WifiP2pManager.Channel mChannel;
    BroadcastReceiver receiver;
    IntentFilter filter;
    public static String ip;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        intialization();
        listeners();

    }

    public void intialization()
    {
        EnableDisable = findViewById(R.id.button);

        RECEIVE = findViewById(R.id.button2);

        FILES = findViewById(R.id.button4);
        SERVER = findViewById(R.id.button10);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mManager = (WifiP2pManager) (getSystemService(Context.WIFI_P2P_SERVICE));
        mChannel = mManager.initialize(this, getMainLooper(), null);
        receiver = new WifiDirectBroadcastReceiver(mManager, mChannel, HomeActivity.this);
        filter = new IntentFilter();
    }

    public void listeners() {
        EnableDisable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Toast.makeText(HomeActivity.this, "LOCATION & HOTSPOT REQUIRED", Toast.LENGTH_LONG).show();
                //Intent Location = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                locationEnabled();
                Intent hotspot = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                Intent[] intents = {hotspot};
                startActivities(intents);
                //Toast.makeText(HomeActivity.this, "LOCATION & HOTSPOT ARE TURNED ON", Toast.LENGTH_SHORT).show();
           }
        });
        RECEIVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Toast.makeText(HomeActivity.this, "WIFI is required", Toast.LENGTH_LONG).show();
                if (wifiManager.isWifiEnabled()) {
//                    wifiManager.setWifiEnabled(false);
                    Toast.makeText(HomeActivity.this, "WIFI is required", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(HomeActivity.this,Client.class);
                    startActivity(i);

                } else {
                    wifiManager.setWifiEnabled(true);
                    Toast.makeText(HomeActivity.this, "Wifi is turning ON", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(HomeActivity.this,Client.class);
                    startActivity(i);
                }

            }
        });

        FILES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(HomeActivity.this,FileActivity.class);
                startActivity(c);

            }
        });
        SERVER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent c = new Intent(HomeActivity.this,Server.class);
                 startActivity(c);

/*                Toast.makeText(HomeActivity.this, "LOCATION & HOTSPOT IS REQUIRED", Toast.LENGTH_LONG).show();
                Intent Location = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);*/

                //startActivity(c);
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

    public void Wifiscanner(View view) {
        Intent file =new Intent(HomeActivity.this,WifiScanner.class);
        startActivity(file);

    }
    private void locationEnabled () {
        LocationManager lm = (LocationManager)
                getSystemService(Context. LOCATION_SERVICE ) ;

        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager. GPS_PROVIDER ) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager. NETWORK_PROVIDER ) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
        if (!gps_enabled && !network_enabled) {
            new AlertDialog.Builder(HomeActivity.this )
                    .setMessage( "GPS Enable" )
                    .setPositiveButton( "Settings" , new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick (DialogInterface paramDialogInterface , int paramInt) {
                                    startActivity( new Intent(Settings. ACTION_LOCATION_SOURCE_SETTINGS )) ;
                                }
                            })
                    .setNegativeButton( "Cancel" , null )
                    .show() ;
        }



    }
}



