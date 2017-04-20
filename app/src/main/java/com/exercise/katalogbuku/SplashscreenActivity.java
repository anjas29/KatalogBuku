package com.exercise.katalogbuku;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("KATALOG_BUKU_DATA", Context.MODE_PRIVATE);

        if(sharedPreferences.getBoolean("login", false)){
            Intent intent = new Intent(SplashscreenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(SplashscreenActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
