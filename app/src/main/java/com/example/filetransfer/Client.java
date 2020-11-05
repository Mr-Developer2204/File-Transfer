package com.example.filetransfer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class Client extends AppCompatActivity {
    Thread Thread1 = null;
    EditText etIP, etPort;
    TextView tvMessages, filepath;
    EditText etMessage;
    Button btnSend,btnConnect,FILES,showpath;
    String SERVER_IP;
    String path;
    int SERVER_PORT,flag=0;
    byte[] bytes;
    File file_get;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        etIP = findViewById(R.id.editTextTextPersonName2);
        etPort = findViewById(R.id.editTextTextPersonName3);
        tvMessages = findViewById(R.id.textView10);
        etMessage = findViewById(R.id.editTextTextPersonName4);
        btnSend = findViewById(R.id.button8);
        btnConnect = findViewById(R.id.button7);
        FILES = findViewById(R.id.button11);
        showpath = findViewById(R.id.button13);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMessages.setText("");
                SERVER_IP = etIP.getText().toString().trim();
                SERVER_PORT = Integer.parseInt(etPort.getText().toString().trim());
                Thread1 = new Thread(new Thread1());
                Thread1.start();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = etMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    new Thread(new Thread3(message,bytes)).start();
                }
                if(flag==1) {
                    Toast.makeText(Client.this, "Transferring", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < 1000; i++) ;
                    Toast.makeText(Client.this, "Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });
        FILES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent file = new Intent(Client.this,FileActivity.class);
                startActivity(file);

            }
        });
        showpath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filepath = findViewById(R.id.textView14);
                filepath.setText("Path: "+path);
                flag=1;
//                file_get = new File(Environment.getExternalStorageDirectory(), path);
//                bytes = new byte[(int) file_get.length()];
            }
        });

    }
    private DataOutputStream output;
    private DataInputStream input;
    private ObjectInputStream f_input;
    private FileOutputStream f_output;

    class Thread1 implements Runnable {
        public void run() {
            Socket socket;
            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                output = new DataOutputStream(socket.getOutputStream());
                input = new DataInputStream(socket.getInputStream());
//                f_input = new ObjectInputStream(socket.getInputStream());
//                f_output = new FileOutputStream(file_get);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvMessages.setText("Connected\n");
                    }
                });
                new Thread(new Thread2()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    class Thread2 implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
//                    if(flag==1) {
//
//                        try {
//                            bytes=(byte[])f_input.readObject();
//                        } catch (ClassNotFoundException e) {
//                            e.printStackTrace();
//                        }
//                    }
                    final String message = input.readUTF();

                    if (message!= null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvMessages.append("Server: " + message + "\n");
                            }
                        });
                    } else {
                        Thread1 = new Thread(new Thread1());
                        Thread1.start();
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class Thread3 implements Runnable {
        private String message;
        private byte[] bytes;
        Thread3(String message, byte[]bytes) {
            this.message = message;
            this.bytes = bytes;
        }
        @Override
        public void run() {
            try {
                output.writeUTF(message);
//                f_output.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                output.flush();
//                f_output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvMessages.append("client: " + message + "\n");
                    etMessage.setText("");
                }
            });
        }
    }
}