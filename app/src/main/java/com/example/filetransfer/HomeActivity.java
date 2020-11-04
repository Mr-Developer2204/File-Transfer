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

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

    Button DISCOVER, SEND, RECEIVE, FILES, SERVER;
    TextView Connection_status;
    WifiManager wifiManager,w;
    WifiP2pManager mManager;
    WifiP2pManager.Channel mChannel;
    LocationManager Loc;
    BroadcastReceiver receiver;
    IntentFilter filter;


    public static String ip;
    static final  int MESSAGE_READ = 1;
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
        SEND = findViewById(R.id.button);
        RECEIVE = findViewById(R.id.button2);
        SERVER = findViewById(R.id.button10);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mManager = (WifiP2pManager) (getSystemService(Context.WIFI_P2P_SERVICE));
        mChannel = mManager.initialize(this, getMainLooper(), null);
        receiver = new WifiDirectBroadcastReceiver(mManager, mChannel, HomeActivity.this);
        filter = new IntentFilter();

        filter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        filter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        filter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        filter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
    }

    public void listeners() {
        SEND.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                locationEnabled();
                Toast.makeText(HomeActivity.this, "LOCATION & HOTSPOT IS REQUIRED", Toast.LENGTH_LONG).show();
                Intent Location = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                Intent hotspot = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                Intent[] intents = {Location, hotspot};
                startActivities(intents);
                Toast.makeText(HomeActivity.this, "LOCATION & HOTSPOT ARE TURNED ON", Toast.LENGTH_SHORT).show();
            }
        });
        RECEIVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(HomeActivity.this, "WIFI is required", Toast.LENGTH_LONG).show();
                if (wifiManager.isWifiEnabled()) {
                    Toast.makeText(HomeActivity.this, "WIFI is On", Toast.LENGTH_LONG).show();
                    File f = new File("File.txt");
                    Intent i = new Intent(HomeActivity.this,Client.class);
                    startActivity(i);

                } else {
                    Toast.makeText(HomeActivity.this, "WIFI is Turning On", Toast.LENGTH_LONG).show();
                    wifiManager.setWifiEnabled(true);
                    Toast.makeText(HomeActivity.this, "Wifi is turning ON", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(HomeActivity.this,Client.class);
                    startActivity(i);
                }


            }
        });

        SERVER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(HomeActivity.this,Server.class);
                startActivity(c);
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




//
//if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//        new AlertDialog.Builder(HomeActivity.this)
//        .setTitle("LOCATION REQUIRED")
//        .setMessage("PLEASE TURN ON LOCATION")
//        .setPositiveButton("ALLOW", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//        //Prompt the user once explanation has been shown
//        ActivityCompat.requestPermissions(HomeActivity.this,
//        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//        MY_PERMISSIONS_REQUEST_LOCATION);
//        }
//        })
//        .setNegativeButton("DENY", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialog, int which) {
//
//        }
//        })
//        .create()
//        .show();
//        return;
//        }




//WifiP2pManager.ConnectionInfoListener connectionInfoListener = new WifiP2pManager.ConnectionInfoListener() {
//    @Override
//    public void onConnectionInfoAvailable(WifiP2pInfo info) {
//        final InetAddress groupOwnerAddress = info.groupOwnerAddress;
//
//        if(info.groupFormed && info.isGroupOwner){
//
//            Connection_status.setText("Server");
//
//        }
//        else if(info.groupFormed){
//
//            Connection_status.setText("Client");
//        }
//
//    }
//};



//        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                final WifiP2pDevice device = DeviceArray[position];
//                WifiP2pConfig config = new WifiP2pConfig();
//                config.deviceAddress = device.deviceAddress;
//
//                if (ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//                    return;
//                }
//
//                mManager.connect(mChannel, config, new WifiP2pManager.ActionListener() {
//                    @Override
//                    public void onSuccess() {
//                        Toast.makeText(getApplicationContext(),"Connected to "+device.deviceName,Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFailure(int reason) {
//                        Toast.makeText(getApplicationContext(),"Not Connected",Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//            }
//        });


//    public void clickTemporary(View view){
//    Intent file =new Intent(HomeActivity.this,FileActivity.class);
//    startActivity(file);
//    }
