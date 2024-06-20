package com.efkan.tirlazim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class menuactivity extends AppCompatActivity {
    TextView textView;
    CardView bankAccount,logOut,updateinformation,allworks,ratingBar;
    String name;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuactivity);
        ratingBar=findViewById(R.id.rating);
        bankAccount=findViewById(R.id.bankaccount);
        logOut=findViewById(R.id.logout);
        updateinformation=findViewById(R.id.updateinformation);
        textView=findViewById(R.id.tittleview);
        allworks=findViewById(R.id.allworks);
        Intent intent=getIntent();
        name=intent.getStringExtra("kullaniciadi");
        textView.setText("WELCOME \n"+name);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color));
        bankAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //BURADA İŞLEMLERİMİZİ GERÇEKLEŞTİRİYORUZ.
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menuactivity.this, girissayfasi.class);
                startActivity(intent);
            }
        });
        updateinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent =new Intent(menuactivity.this,updateinformation.class);
            intent.putExtra("kullaniciadi",name);
            startActivity(intent);
            }
        });
        allworks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menuactivity.this, alljobsemployee.class);
                intent.putExtra("kullaniciadi",name);
                startActivity(intent);
            }
        });
        ratingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menuactivity.this,ratingbarsayfasi.class);
                intent.putExtra("kullaniciadi",name);
                intent.putExtra("truefalse",1);
                startActivity(intent);
            }
        });
        bankAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menuactivity.this,webview.class);
                startActivity(intent);
            }
        });
    }
    @SuppressLint("MissingSuperCall")
    public void onBackPressed() {

    }
}