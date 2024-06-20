package com.efkan.tirlazim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splashekran extends AppCompatActivity {
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_splash);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(splashekran.this,girissayfasi.class);
                startActivity(intent);
                finish();

            }
        },2500);
        handler.removeCallbacks(runnable);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color));   //Ekranın rengine göre status barın rengini değiştiren kod
    }
}