package com.example.tugasleaf;

import android.content.Intent;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SystemClock.sleep(3000);
        Intent loginIntent = new Intent(SplashActivity.this,LoginActivity.class);
        startActivity(loginIntent);

        finish();
    }
}