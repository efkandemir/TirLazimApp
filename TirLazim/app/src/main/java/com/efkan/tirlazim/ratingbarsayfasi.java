package com.efkan.tirlazim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class ratingbarsayfasi extends AppCompatActivity {
    RatingBar ratingBar;
    Button button;
    String kullaniciadi;
    int truefalse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratingbarsayfasi);
        Intent intent=getIntent();
        kullaniciadi=intent.getStringExtra("kullaniciadi");
        truefalse=intent.getIntExtra("truefalse",99);
        ratingBar = findViewById(R.id.ratingBar);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ratingbarsayfasi.this, "Rating: " + ratingBar.getRating()+" Thank You", Toast.LENGTH_SHORT).show();
                if(truefalse==1){
                    Intent intent=new Intent(ratingbarsayfasi.this, menuactivity.class);
                    intent.putExtra("kullaniciadi",kullaniciadi);
                    startActivity(intent);
                }
                else{
                    Intent intent=new Intent(ratingbarsayfasi.this,menuemployer.class);
                    intent.putExtra("kullaniciadi",kullaniciadi);
                    startActivity(intent);
                }

            }
        });

    }
}