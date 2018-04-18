package com.example.zk.keepingservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zk.service.KeepingService;
import com.example.zk.service.MainService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent1 = new Intent(MainActivity.this, MainService.class);
        startService(intent1);
        Intent intent2 = new Intent(MainActivity.this, KeepingService.class);
        startService(intent2);
    }
}
