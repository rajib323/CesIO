package com.google.cesio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import static java.lang.Thread.sleep;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Intent intent=new Intent(splashActivity.this,MainActivity.class);
                    sleep(3000);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        });
        thread.start();


    }
}