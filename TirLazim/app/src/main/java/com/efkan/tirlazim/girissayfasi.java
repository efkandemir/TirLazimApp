package com.efkan.tirlazim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class girissayfasi extends AppCompatActivity {
    SQLiteDatabase database;
    String nick,pass;    //employer databese cursor kısmı
    EditText girisUsername,girisPassword;
    TextView employeraccountCreatTextView,employeeaccountCreatTextView,userquery;

    RadioButton employerRadio;
    RadioButton employeeRadio;
    RadioGroup radioGroup;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girissayfasi);
        database=this.openOrCreateDatabase("Truck",MODE_PRIVATE,null);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color));
        userquery=findViewById(R.id.userquery);
        radioGroup=findViewById(R.id.radioGroup);
        employeeRadio=findViewById(R.id.girisEmployeeradio);
        employerRadio=findViewById(R.id.girisEmployerradio);
        girisUsername=findViewById(R.id.girisUsername);
        girisPassword=findViewById(R.id.girisPassword);
        employeeaccountCreatTextView=findViewById(R.id.employeetextCreateaccount);
        employeraccountCreatTextView=findViewById(R.id.employertextCreateaccount);
        employeraccountCreatTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(girissayfasi.this, employerkayitol_sayfasi.class);
                startActivity(intent);
            }
        });
        employeeaccountCreatTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(girissayfasi.this, employeekayitol_sayfasi.class);
                startActivity(intent);
            }
        });
        userquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(girissayfasi.this,searchperson.class);
            startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.exit_menu) {
            Toast.makeText(girissayfasi.this, "EXIT", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    public void girissigninbutton(View view){
        if(girisUsername.getText().toString().matches("")||girisPassword.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Please do not leave empty space",Toast.LENGTH_SHORT).show();
        }
        else if(!employerRadio.isChecked()&&!employeeRadio.isChecked()){
            Toast.makeText(getApplicationContext(),"Please do not leave empty space",Toast.LENGTH_SHORT).show();
        }
        else{
             //VERİ TABANI
            try {
                database = this.openOrCreateDatabase("Truck", MODE_PRIVATE, null);
                if (employerRadio.isChecked()) {
                    Cursor cursor = database.rawQuery("SELECT * FROM employer", null);
                    int nickIndex = cursor.getColumnIndex("nicknameemployer");
                    int passIndex = cursor.getColumnIndex("passwordEmployer");
                    while (cursor.moveToNext()) {
                        String nick = cursor.getString(nickIndex);
                        String pass = cursor.getString(passIndex);
                        if (girisUsername.getText().toString().equals(nick) && girisPassword.getText().toString().equals(pass)) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setTitle("Welcome");
                            builder.setMessage("Successfully Registered");
                            builder.show();
                            Intent intent = new Intent(girissayfasi.this, menuemployer.class);
                            intent.putExtra("kullaniciadi", girisUsername.getText().toString());
                            startActivity(intent);
                            cursor.close();
                            database.close();
                            return;
                        }
                    }
                    Toast.makeText(girissayfasi.this, "The information entered is incorrect!!!", Toast.LENGTH_SHORT).show();
                    cursor.close();
                    database.close();
                } else {
                    Cursor cursor = database.rawQuery("SELECT * FROM employee", null);
                    int nickIndex = cursor.getColumnIndex("nickname");
                    int passIndex = cursor.getColumnIndex("password");
                    while (cursor.moveToNext()) {
                        String nick = cursor.getString(nickIndex);
                        String pass = cursor.getString(passIndex);
                        if (girisUsername.getText().toString().equals(nick) && girisPassword.getText().toString().equals(pass)) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setTitle("Welcome");
                            builder.setMessage("Successfully Registered");
                            builder.show();
                            Intent intent = new Intent(girissayfasi.this, menuactivity.class);
                            intent.putExtra("kullaniciadi", girisUsername.getText().toString());
                            startActivity(intent);
                            cursor.close();
                            database.close();
                            return;
                        }
                    }
                    Toast.makeText(girissayfasi.this, "The information entered is incorrect!!!", Toast.LENGTH_SHORT).show();
                    cursor.close();
                    database.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (database != null) {
                    database.close();
                }
            }


        }
    }
    @SuppressLint("MissingSuperCall")
    public void onBackPressed() {

    }


}