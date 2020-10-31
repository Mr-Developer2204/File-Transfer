package com.example.filetransfer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

    Button DISCOVER, SEND, RECEIVE, FILES;
    ListView list1;
    TextView read_msg, Connection_status;
    EditText wrt_msg;
    LocationManager locationManager;
    WifiManager wifiManager;
    WifiP2pManager mManager;
    WifiP2pManager.Channel mChannel;
    LocationManager Loc;
    BroadcastReceiver receiver;
    IntentFilter filter;
    List<WifiP2pDevice> peers = new ArrayList<WifiP2pDevice>();
    String[] deviceNames;
    WifiP2pDevice[] DeviceArray;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        intialization();



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
                Toast.makeText(HomeActivity.this, "LOCATION & HOTSPOT ARE REQUIRED", Toast.LENGTH_LONG).show();

            }
        });
        RECEIVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callWifiSetting = new Intent(Settings.ACTION_WIFI_SETTINGS);
                Toast.makeText(HomeActivity.this, "WIFI is required", Toast.LENGTH_LONG).show();
                startActivity(callWifiSetting);
                filter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
                filter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
                filter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
                filter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
                if (wifiManager.isWifiEnabled()) {

                    Toast.makeText(HomeActivity.this, "Wifi is turning ON", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HomeActivity.this, "WIFI is required", Toast.LENGTH_LONG).show();
                }

            }
        });

        DISCOVER.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


                    new AlertDialog.Builder(HomeActivity.this)
                            .setTitle("LOCATION REQUIRED")
                            .setMessage("PLEASE TURN ON LOCATION")
                            .setPositiveButton("ALLOW", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //Prompt the user once explanation has been shown
                                    ActivityCompat.requestPermissions(HomeActivity.this,
                                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                            MY_PERMISSIONS_REQUEST_LOCATION);
                                }
                            })
                            .setNegativeButton("DENY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .create()
                            .show();
                    return;
                }

                mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {

                        String s = "Discovery Started";
                        Connection_status.setText(s);

                    }

                    @Override
                    public void onFailure(int reason) {
                        String s = "Discovery Failed";
                        Connection_status.setText(s);

                    }
                });
            }
        });

    }

    WifiP2pManager.PeerListListener peerListListener = new WifiP2pManager.PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peersList) {
            if(!peersList.getDeviceList().equals(peers))
            {
                int len = peersList.getDeviceList().size();
                peers.clear();
                peers.addAll(peersList.getDeviceList());
                deviceNames = new String[len];
                DeviceArray = new WifiP2pDevice[len];

                int index=0;

                for(WifiP2pDevice device : peersList.getDeviceList()){
                    deviceNames[index]= device.deviceName;
                    DeviceArray[index] = device;
                    index+=1;

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,deviceNames);
                list1.setAdapter(adapter);

                if(peers.size()==0){

                    Toast.makeText(getApplicationContext(), "No device Found", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

        }
    };

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

    public void intialization()
    {
        SEND = findViewById(R.id.button);
        RECEIVE = findViewById(R.id.button2);
        DISCOVER = findViewById(R.id.button3);
        FILES = findViewById(R.id.button4);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mManager = (WifiP2pManager) (getSystemService(Context.WIFI_P2P_SERVICE));
        mChannel = mManager.initialize(this, getMainLooper(), null);
        receiver = new WifiDirectBroadcastReceiver(mManager, mChannel, this);
        filter = new IntentFilter();
        Connection_status = findViewById(R.id.textView2);
    }

    public void clickTemporary(View view){
    Intent file =new Intent(HomeActivity.this,FileActivity.class);
    startActivity(file);
    }

}