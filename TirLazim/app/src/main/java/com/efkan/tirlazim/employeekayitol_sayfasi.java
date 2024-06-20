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
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class employeekayitol_sayfasi extends AppCompatActivity {
    Cursor cursor;
    String gender;
    SQLiteDatabase database;
    EditText namesurnameemployee,drivinglicenseemployee,ageemployee,nicknameemployee,passwordemployee;
    CheckBox checkBox1 ;
    String nickName,age,password,drivingLicense,nameSurname;
    CheckBox checkBox2 ;
    Button employeeSignupButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeekayitol_sayfasi);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color));
        database=this.openOrCreateDatabase("Truck",MODE_PRIVATE,null);
        namesurnameemployee=findViewById(R.id.kayitnamesurnameemployee);
        drivinglicenseemployee=findViewById(R.id.kayitdrivinglicenseemployee);
        ageemployee=findViewById(R.id.kayitageemployee);
        nicknameemployee=findViewById(R.id.kayitnicknameemployee);
        passwordemployee=findViewById(R.id.kayitpasswordemployee);
        checkBox1 = findViewById(R.id.kayitwomencheckBox);
        checkBox2 = findViewById(R.id.kayitmalecheckBox);
        employeeSignupButton=findViewById(R.id.employeeSignupbutton);


        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox1.isChecked()) {
                    checkBox2.setChecked(false); // checkBox1 seçiliyse, checkBox2'yi iptal et
                }
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox2.isChecked()) {
                    checkBox1.setChecked(false); // checkBox2 seçiliyse, checkBox1'i iptal et
                }
            }
        });

    }
    public void employeeSignupbutton(View view){  //BUTONA TIKLANINCA ALERT DİALOG YAZDIRDIĞIMIZ KISIM
        if(namesurnameemployee.getText().toString().matches("")||drivinglicenseemployee.getText().toString().matches("")||ageemployee.getText().toString().matches("")||nicknameemployee.getText().toString().matches("")
        ||passwordemployee.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Please do not leave empty space",Toast.LENGTH_SHORT).show();
        }
        else if((!checkBox2.isChecked()&&!checkBox1.isChecked())){
            Toast.makeText(getApplicationContext(),"Please do not leave empty space",Toast.LENGTH_SHORT).show();
        }
        else{
            //VERİTABANI
            nameSurname=namesurnameemployee.getText().toString();
            age=ageemployee.getText().toString();
            password=passwordemployee.getText().toString();
            nickName=nicknameemployee.getText().toString();
            drivingLicense=drivinglicenseemployee.getText().toString();
            if(checkBox1.isChecked()){
                gender="woman";
            }
            else{
                gender="man";
            }
            try{
                database=this.openOrCreateDatabase("Truck",MODE_PRIVATE,null);
                database.execSQL("CREATE TABLE IF NOT EXISTS employee(id INTEGER PRIMARY KEY,namesurname VARCHAR,drivinglicense VARCHAR,age VARCHAR,nickname VARCHAR,password VARCHAR,gender VARCHAR) ");
                String sqlString = "INSERT INTO employee (nameSurname, drivingLicense, age, nickName, password,gender) VALUES (?, ?, ?, ?, ?,?)";
                SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
                sqLiteStatement.bindString(1, nameSurname);
                sqLiteStatement.bindString(2, drivingLicense);
                sqLiteStatement.bindString(3, age);
                sqLiteStatement.bindString(4, nickName);
                sqLiteStatement.bindString(5, password);
                sqLiteStatement.bindString(6,gender);
                sqLiteStatement.execute();
                int nameIndex = cursor.getColumnIndex("namesurname");
                int drivingIndex = cursor.getColumnIndex("drivinglicense");
                int ageIndex = cursor.getColumnIndex("age");
                int nickIndex = cursor.getColumnIndex("nickname");
                int passIndex = cursor.getColumnIndex("password");
                int genderIndex=cursor.getColumnIndex("gender");
                while (cursor.moveToNext()) {
                    String name = cursor.getString(nameIndex);
                    String driving = cursor.getString(drivingIndex);
                    String age = cursor.getString(ageIndex);
                    String nick = cursor.getString(nickIndex);
                    String pass = cursor.getString(passIndex);
                    String gender=cursor.getString(genderIndex);
                }
                cursor.close();
                database.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }


            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Welcome");
            builder.setMessage("Successfully Registered");
            builder.show();
            Intent intent=new Intent(employeekayitol_sayfasi.this,girissayfasi.class);
            startActivity(intent);
        }

    }

}