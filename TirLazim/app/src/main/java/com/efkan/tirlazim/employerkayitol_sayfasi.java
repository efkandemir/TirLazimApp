package com.efkan.tirlazim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class employerkayitol_sayfasi extends AppCompatActivity {
    SQLiteDatabase database;
    String businessname;
    String namesurname;
    String phonenumber;
    String nickname;
    String password;

    EditText namesurnameemployer,businessnameemployer,phonenumberemployer,nicknameemployer,passwordemployer;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employerkayitol_sayfasi);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color));
        database=this.openOrCreateDatabase("Truck",MODE_PRIVATE,null);
        namesurnameemployer=findViewById(R.id.kayitnamesurnameemployer);
        businessnameemployer=findViewById(R.id.kayitbusineesnameemployer);
        phonenumberemployer=findViewById(R.id.kayitphonenumberemployer);
        passwordemployer=findViewById(R.id.kayitpasswordemployer);
        nicknameemployer=findViewById(R.id.kayitnicknameemployer);
    }
    public void employerSignupbutton(View view){  //BUTONA TIKLANINCA ALERT DİALOG YAZDIRDIĞIMIZ KISIM
        if(namesurnameemployer.getText().toString().matches("")||businessnameemployer.getText().toString().matches("")||phonenumberemployer.getText().toString().matches("")||passwordemployer.getText().toString().matches("")
        ||nicknameemployer.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Please do not leave empty space",Toast.LENGTH_SHORT).show();
        }
        else{
            //VERİTABANI
             namesurname=namesurnameemployer.getText().toString();
             businessname=businessnameemployer.getText().toString();
             phonenumber=phonenumberemployer.getText().toString();
             nickname=nicknameemployer.getText().toString();
             password=passwordemployer.getText().toString();
            try {
                database = this.openOrCreateDatabase("Truck", MODE_PRIVATE, null);
                database.execSQL("CREATE TABLE IF NOT EXISTS employer(id INTEGER PRIMARY KEY,namesurname VARCHAR,businessnameemployer VARCHAR,phonenumberemployer VARCHAR,nicknameemployer VARCHAR,passwordEmployer VARCHAR) ");

                String sqlString = "INSERT INTO employer (namesurname, businessnameemployer, phonenumberemployer, nicknameemployer, passwordEmployer) VALUES (?, ?, ?, ?, ?)";
                SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
                sqLiteStatement.bindString(1, namesurname);
                sqLiteStatement.bindString(2, businessname);
                sqLiteStatement.bindString(3, phonenumber);
                sqLiteStatement.bindString(4, nickname);
                sqLiteStatement.bindString(5, password);
                sqLiteStatement.execute();

                Cursor cursor = database.rawQuery("SELECT * FROM employer", null);


                int nameIndex = cursor.getColumnIndex("namesurname");
                int businessIndex = cursor.getColumnIndex("businessnameemployer");
                int phoneIndex = cursor.getColumnIndex("phonenumberemployer");
                int nickIndex = cursor.getColumnIndex("nicknameemployer");
                int passIndex = cursor.getColumnIndex("passwordEmployer");
                while (cursor.moveToNext()) {
                    String name = cursor.getString(nameIndex);
                    String business = cursor.getString(businessIndex);
                    String phone = cursor.getString(phoneIndex);
                    String nick = cursor.getString(nickIndex);
                    String pass = cursor.getString(passIndex);
                }
                cursor.close();
                database.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Welcome");
            builder.setMessage("Successfully Registered");
            builder.show();
            Intent intent=new Intent(employerkayitol_sayfasi.this, girissayfasi.class);
            startActivity(intent);
        }

    }

}