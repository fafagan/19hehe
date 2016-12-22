package com.szt20.shop.shop;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private ListView mLv;
    
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
    
            List<HomeEntity.HomeClasslistEntity> homeClasslistEntities= (List<HomeEntity.HomeClasslistEntity>) msg.obj;
            
            List<String> titles=new ArrayList<>();
    
            for (HomeEntity.HomeClasslistEntity homeClasslistEntity : homeClasslistEntities) {
                titles.add(homeClasslistEntity.getTitle());
            }
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,titles);
            mLv.setAdapter(arrayAdapter);
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        mLv = (ListView) findViewById(R.id.lv);
    }
    
    public void go1(View view) {
        Toast.makeText(this, "加载", Toast.LENGTH_SHORT).show();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL("http://192.168.1.236/home");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    
                    InputStream inputStream = httpURLConnection.getInputStream();
                    
                    byte[] bs = new byte[1024 * 100];
                    int total = -1;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((total = inputStream.read(bs)) != -1) {
                        
                        stringBuilder.append(new String(bs, 0, total));
                        
                    }
                    
                    String jsonStr = stringBuilder.toString();
                    Gson gson = new Gson();
                    HomeEntity homeEntity = gson.fromJson(jsonStr, HomeEntity.class);
                    Message message = mHandler.obtainMessage();
                    message.obj = homeEntity.getHome_classlist();
                    mHandler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                
            }
        }.start();
    }
    
    public void go2(View view) {
        
        
    }
    
    public void go3(View view) {
        
        
    }
}
