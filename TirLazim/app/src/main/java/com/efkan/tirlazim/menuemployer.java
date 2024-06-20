package com.efkan.tirlazim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class menuemployer extends AppCompatActivity {
    String kullaniciadi;
    CardView logOut,addJobs,allJobs,ratingbar,bankaccount;
    TextView tittletextview;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuemployer);
        bankaccount=findViewById(R.id.bankaccount);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color));
        ratingbar=findViewById(R.id.ratingbarcard);
        allJobs=findViewById(R.id.alljobs);
        Intent intent=getIntent();
        kullaniciadi=intent.getStringExtra("kullaniciadi");
        tittletextview=findViewById(R.id.tittleemployer);
        tittletextview.setText("WELCOME \n"+kullaniciadi);
        logOut=findViewById(R.id.logoutemployer);
        addJobs=findViewById(R.id.addjobs);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menuemployer.this, girissayfasi.class);
                startActivity(intent);
            }
        });
        addJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menuemployer.this, addjobemployer.class);
                intent.putExtra("kullaniciadi",kullaniciadi);
                startActivity(intent);
            }
        });
        allJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(menuemployer.this, AllJobsEmployerActivity.class);
            intent.putExtra("kullaniciadi",kullaniciadi);
            startActivity(intent);
            }
        });
        ratingbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menuemployer.this,ratingbarsayfasi.class);
                intent.putExtra("kullaniciadi",kullaniciadi);
                intent.putExtra("truefalse",0);
                startActivity(intent);
            }
        });
        bankaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menuemployer.this,webview.class);
                startActivity(intent);
            }
        });


    }





}