package com.codev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        getSupportActionBar ().hide ();
        Timer timer=new Timer ();
        timer.schedule (new TimerTask () {
            @Override
            public void run() {
                Intent intent=new Intent (MainActivity.this,Home.class);
                startActivity (intent);
                finish ();
            }
        },2000);
    }
}
