package com.efkan.tirlazim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class addjobemployer extends AppCompatActivity {
    TextView urunturu,baslangictarihi,bitistarihi,isfiyati,baslangickonumu,bitiskonumu;
    Button isipaylas;
    String kullaniciadi;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addjobemployer);
        database=this.openOrCreateDatabase("Truck",MODE_PRIVATE,null);
        Intent intent=getIntent();
        kullaniciadi=intent.getStringExtra("kullaniciadi");
        urunturu=findViewById(R.id.urunturu);
        baslangictarihi=findViewById(R.id.baslangictarihi);
        bitistarihi=findViewById(R.id.bitistarihi);
        isfiyati=findViewById(R.id.isfiyati);
        baslangickonumu=findViewById(R.id.baslangickonumu);
        bitiskonumu=findViewById(R.id.sonkonumu);
        isipaylas=findViewById(R.id.isipaylas);
        isipaylas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(urunturu.getText().toString().matches("")||baslangictarihi.getText().toString().matches("")||
                bitistarihi.getText().toString().matches("")||isfiyati.getText().toString().matches("")||baslangickonumu.getText().toString().matches("")||
                bitiskonumu.getText().toString().matches("")){
                    Toast.makeText(addjobemployer.this,"Please do not leave empty space!",Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        database.execSQL("CREATE TABLE IF NOT EXISTS jobs(id INTEGER PRIMARY KEY,kullaniciadi VARCHAR,urunturu VARCHAR,baslangickonumu VARCHAR,bitiskonumu VARCHAR,fiyat VARCHAR,baslangictarihi VARCHAR,bitistarihi VARCHAR)");
                        String sql="INSERT INTO jobs(kullaniciadi,urunturu,baslangickonumu,bitiskonumu,fiyat,baslangictarihi,bitistarihi) VALUES(?,?,?,?,?,?,?)";
                        SQLiteStatement sqLiteStatement=database.compileStatement(sql);
                        sqLiteStatement.bindString(1,kullaniciadi);
                        sqLiteStatement.bindString(2,urunturu.getText().toString());
                        sqLiteStatement.bindString(3,baslangickonumu.getText().toString());
                        sqLiteStatement.bindString(4,bitiskonumu.getText().toString());
                        sqLiteStatement.bindString(5,isfiyati.getText().toString());
                        sqLiteStatement.bindString(6,baslangictarihi.getText().toString());
                        sqLiteStatement.bindString(7,bitistarihi.getText().toString());
                        sqLiteStatement.execute();
                        finish();

                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    Intent intent=new Intent(addjobemployer.this, menuemployer.class);
                    intent.putExtra("kullaniciadi",kullaniciadi);
                    startActivity(intent);
                }
            }
        });
    }
}