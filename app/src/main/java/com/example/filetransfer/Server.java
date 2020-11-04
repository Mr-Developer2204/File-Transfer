package com.example.filetransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;


public class Server extends AppCompatActivity {

    public ServerSocket serverSocket;
    Thread Thread1 = null;
    TextView tvIP, tvPort,filepath;
    TextView tvmessages;
    EditText etMessage;
    Button send,FILES,showpath;
    public static String SERVER_IP = "";
    public static final int SERVER_PORT = 8080;
    String message;
    public String path;
    int flag=0;
    byte[] bytes;
    File file_get;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        tvIP = findViewById(R.id.textView4);
        tvPort = findViewById(R.id.textView5);
        etMessage = findViewById(R.id.editTextTextPersonName);
        tvmessages = findViewById(R.id.textView6);
        send = findViewById(R.id.button6);
        SERVER_IP=getLocalIpAddress();
        FILES = findViewById(R.id.button9);
        filepath = findViewById(R.id.textView13);
        showpath = findViewById(R.id.button12);
        Thread1 = new Thread((new Thread1()));
        Thread1.start();
        path="";




        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                message = etMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    new Thread(new Thread3(message,bytes)).start();
                }
//                if(!path.isEmpty()){
//                    new Thread(new Thread3(message,bytes)).start();
//                }
            }
        });
        FILES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=1;
                Intent file = new Intent(Server.this,FileActivity.class);
                startActivity(file);


            }
        });
        showpath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                path = intent.getStringExtra("file");
                filepath.setText("Path: "+path);
//                file_get = new File(Environment.getExternalStorageDirectory(),path);
//                bytes = new byte[(int) file_get.length()];


            }
        });


    }
    public static String getLocalIpAddress() {
        String ip="";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        ip+=inetAddress.getHostAddress()+""+"\n";
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return ip;
    }
    private DataOutputStream output;
    private DataInputStream input;
//    private BufferedInputStream f_input;
//    private ObjectOutputStream f_output;
    class Thread1 implements Runnable {

        @Override
        public void run() {
            Socket socket;
            try {
                serverSocket = new ServerSocket(SERVER_PORT);
                runOnUiThread(new Runnable() {
                  @Override
                    public void run() {

                        tvmessages.setText("Not Connected");
                        tvIP.setText("IP :"+SERVER_IP);
                        tvPort.setText("Port :"+(SERVER_PORT+""));

                    }
                });
                try {
                    socket= serverSocket.accept();
                    socket.setSoTimeout(300000);
                    output = new DataOutputStream(socket.getOutputStream());
                    input=new DataInputStream(socket.getInputStream());
//                    f_input = new BufferedInputStream(new FileInputStream(file_get));
//                    f_output = new ObjectOutputStream(socket.getOutputStream());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvmessages.setText("Connected\n");
                        }
                    });
                    new Thread(new Thread2(socket)).start();

                }catch (IOException e) {
                e.printStackTrace();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private class Thread2 implements Runnable{
        Socket socket;
        Thread2(Socket socket){
            this.socket=socket;
        }
        @Override
        public void run() {
            while (true){
                try {
                    final String message = input.readUTF();
//                        try {
//                            f_input.read(bytes, 0, bytes.length);
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        }


                    if(message!=null){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvmessages.append("Client: "+message+"\n");
                            }
                        });
                    }
                    else{
                        Thread1 = new Thread(new Thread1());
                        Thread1.start();
                        return;
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    class Thread3 implements Runnable{
        private String message;
        private byte[] bytes;
        Thread3(String message, byte[] bytes){
            this.message= message;
            this.bytes = bytes;
        }
        @Override
        public void run() {
            try {
                output.writeUTF(message);

//                    f_output.writeObject(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                output.flush();
//                f_output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvmessages.append("Server: "+message+"\n");
                    etMessage.setText("");
                }
            });
        }
    }

}

