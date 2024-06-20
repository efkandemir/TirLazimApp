package com.efkan.tirlazim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class updateinformation extends AppCompatActivity {
    Spinner genderspinner;
    String kullaniciadi;
    Drawable originalIcon;
    Drawable newIcon;
    boolean isNameEditOriginal = true;
    boolean isAgeEditOriginal=true;
    boolean isDrivingEditOriginal = true;
    boolean isGenderEditOriginal = true;
    boolean isPasswordEditOriginal=true;
    boolean isNicknameOriginal=true;
    SQLiteDatabase database;
    FloatingActionButton nameedit,ageedit,nicknameedit,passwordedit,drivingedit,genderedit;
    EditText namesurname,agetext,drivinglicensetext,nicknametext,passwordtext;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateinformation);
        passwordtext=findViewById(R.id.passwordtext);
        nicknametext=findViewById(R.id.nicknametext);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color));
        genderspinner=findViewById(R.id.spinner);
        genderspinner.setEnabled(false);
        String[] genders = {"Man", "Woman"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderspinner.setAdapter(adapter);

        Intent intent = getIntent();
        kullaniciadi = intent.getStringExtra("kullaniciadi");
        nameedit = findViewById(R.id.nameedit);
        ageedit=findViewById(R.id.ageedit);
        nicknameedit=findViewById(R.id.nicknameedit);
        passwordedit=findViewById(R.id.passwordedit);
        drivingedit=findViewById(R.id.drivingedit);
        genderedit=findViewById(R.id.genderedit);
        namesurname = findViewById(R.id.namesurnametext);
        drivinglicensetext=findViewById(R.id.drivinglicensetext);
        agetext = findViewById(R.id.agetext);
        originalIcon = getResources().getDrawable(R.drawable.baseline_edit_24);
        newIcon = getResources().getDrawable(R.drawable.circle);
        database = this.openOrCreateDatabase("Truck", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS employee(id INTEGER PRIMARY KEY,namesurname VARCHAR,drivinglicense VARCHAR,age VARCHAR,nickname VARCHAR,password VARCHAR,gender VARCHAR)");
        String query = "SELECT * FROM employee WHERE nickname  = ?";
        Cursor cursor = database.rawQuery(query, new String[]{kullaniciadi});

        int adIndex = cursor.getColumnIndex("namesurname");
        int ageIndex = cursor.getColumnIndex("age");
        int drivingIndex=cursor.getColumnIndex("drivinglicense");
        int nicknameIndex=cursor.getColumnIndex("nickname");
        int passwordIndex=cursor.getColumnIndex("password");
        while (cursor.moveToNext()) {
            String ad = cursor.getString(adIndex);
            String age = cursor.getString(ageIndex);
            String driving=cursor.getString(drivingIndex);
            String nickname=cursor.getString(nicknameIndex);
            String password=cursor.getString(passwordIndex);
            namesurname.setText(ad);
            agetext.setText(age);
            drivinglicensetext.setText(driving);
            nicknametext.setText(nickname);
            passwordtext.setText(password);
        }

        nameedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNameEditOriginal){
                    nameedit.setImageDrawable(newIcon);
                    namesurname.setEnabled(true);

                }
                else{
                    nameedit.setImageDrawable(originalIcon);
                    namesurname.setEnabled(false);
                    String nameedittextnew = namesurname.getText().toString();
                    try {
                        String updateQuery = "UPDATE employee SET namesurname=? WHERE nickname=?";
                        SQLiteStatement statement = database.compileStatement(updateQuery);
                        statement.bindString(1, nameedittextnew);
                        statement.bindString(2, kullaniciadi);
                        statement.execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                isNameEditOriginal = !isNameEditOriginal;
            }
        });
        drivingedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDrivingEditOriginal){
                    drivingedit.setImageDrawable(newIcon);
                    drivinglicensetext.setEnabled(true);

                }
                else{
                    drivingedit.setImageDrawable(originalIcon);
                    drivinglicensetext.setEnabled(false);
                    String drivingedittext= drivinglicensetext.getText().toString();
                    try {
                        String updateQuery = "UPDATE employee SET  drivinglicense=? WHERE nickname=?";
                        SQLiteStatement statement = database.compileStatement(updateQuery);
                        statement.bindString(1, drivingedittext);
                        statement.bindString(2, kullaniciadi);
                        statement.execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                isDrivingEditOriginal = !isDrivingEditOriginal;

            }
        });
        ageedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAgeEditOriginal){
                    ageedit.setImageDrawable(newIcon);
                    agetext.setEnabled(true);
                }
                else{
                    ageedit.setImageDrawable(originalIcon);
                    agetext.setEnabled(false);
                    String ageedittext = agetext.getText().toString();
                    try {
                        String updateQuery = "UPDATE employee SET age=? WHERE nickname=?";
                        SQLiteStatement statement = database.compileStatement(updateQuery);
                        statement.bindString(1, ageedittext);
                        statement.bindString(2, kullaniciadi);
                        statement.execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                isAgeEditOriginal=!isAgeEditOriginal;
            }

        });
        genderedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGenderEditOriginal) {
                    genderedit.setImageDrawable(newIcon);
                    genderspinner.setEnabled(true);
                }
                else{
                    genderedit.setImageDrawable(originalIcon);
                    genderspinner.setEnabled(false);
                        String selectedgender=genderspinner.getSelectedItem().toString();
                        if(selectedgender=="Man"){
                            try{
                                String updateQuery = "UPDATE employee SET  gender=? WHERE nickname=?";
                                SQLiteStatement statement = database.compileStatement(updateQuery);
                                statement.bindString(1, "Man");
                                statement.bindString(2, kullaniciadi);
                                statement.execute();
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        else {
                            try{
                                String updateQuery = "UPDATE employee SET  gender=? WHERE nickname=?";
                                SQLiteStatement statement = database.compileStatement(updateQuery);
                                statement.bindString(1, "Woman");
                                statement.bindString(2, kullaniciadi);
                                statement.execute();
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                isGenderEditOriginal=!isGenderEditOriginal;
                }

        });
        nicknameedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNicknameOriginal){
                    nicknameedit.setImageDrawable(originalIcon);
                    nicknametext.setEnabled(true);
                }
                else{
                    nicknameedit.setImageDrawable(newIcon);
                    nicknametext.setEnabled(false);
                    String nicknamenew=nicknametext.getText().toString();
                    try{
                        String updateQuery = "UPDATE employee SET  nickname=? WHERE nickname=?";
                        SQLiteStatement statement = database.compileStatement(updateQuery);
                        statement.bindString(1, nicknamenew);
                        statement.bindString(2, kullaniciadi);
                        statement.execute();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    kullaniciadi=nicknamenew;
                    AlertDialog.Builder builder=new AlertDialog.Builder(updateinformation.this);
                    builder.setMessage("Please Log in Again");
                    builder.show();
                    Handler handler=new Handler();
                    Runnable runnable;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(updateinformation.this,girissayfasi.class);
                            startActivity(intent);
                        }
                    },1000);
                }
                isNicknameOriginal=!isNicknameOriginal;
            }
        });
        passwordedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPasswordEditOriginal){
                    passwordedit.setImageDrawable(originalIcon);
                    passwordtext.setEnabled(true);
                }
                else{
                    passwordedit.setImageDrawable(newIcon);
                    passwordtext.setEnabled(false);
                    String newpasswordtext=passwordtext.getText().toString();
                    try{
                        String updateQuery = "UPDATE employee SET  password=? WHERE nickname=?";
                        SQLiteStatement statement = database.compileStatement(updateQuery);
                        statement.bindString(1, newpasswordtext);
                        statement.bindString(2, kullaniciadi);
                        statement.execute();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    AlertDialog.Builder builder=new AlertDialog.Builder(updateinformation.this);
                    builder.setMessage("Please Log in Again");
                    builder.show();
                    Handler handler=new Handler();
                    Runnable runnable;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(updateinformation.this,girissayfasi.class);
                            startActivity(intent);
                        }
                    },1000);
                }
                isPasswordEditOriginal=!isPasswordEditOriginal;
            }
        });
    }

}