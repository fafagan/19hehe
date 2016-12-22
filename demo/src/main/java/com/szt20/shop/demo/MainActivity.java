package com.szt20.shop.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void go(View view){
        int a=100;
        int b=a+a;
        int c=a+b;
        
        String str=null;
        str+="hehe "+c;
    
        String dododo = dododo(str);
    
    
        Toast.makeText(this, dododo, Toast.LENGTH_SHORT).show();
    }
    
    private String dododo(String str) {
        //heheda
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("xx1");
        stringBuilder.append("xx2");
        
        return stringBuilder.toString();
    }
}
