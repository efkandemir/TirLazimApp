package com.efkan.tirlazim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class searchperson extends AppCompatActivity {
    EditText searchtext;
    Button searchbt;
    String tableName;
    SQLiteDatabase database;
    ListView listview;

    ArrayList<String> kullaniciadiList = new ArrayList<>();
    ArrayList<String> ageList = new ArrayList<>();
    ArrayList<String> drivinglicenseList = new ArrayList<>();
    ArrayList<String> namesurnameList = new ArrayList<>();
    ArrayList<String> genderList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchperson);
        searchbt = findViewById(R.id.searchbuttonid);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color));
        database = this.openOrCreateDatabase("Truck", MODE_PRIVATE, null);
        listview = findViewById(R.id.listview);
        searchtext = findViewById(R.id.usernameentry);

        searchbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }

    class BenimAdapter extends ArrayAdapter<String> {
        Context context;
        String[] rKullaniciadi, rage, rnamesurname, rgender, rdrivinglicense;

        BenimAdapter(Context c, String[] rkullaniciAdi, String[] rnamesurname, String[] rgender, String[] rdrivinglicense, String[] rage) {
            super(c, R.layout.custom_searchlistview, R.id.tvnamesurname, rkullaniciAdi);
            this.context = c;
            this.rKullaniciadi = rkullaniciAdi;
            this.rnamesurname = rnamesurname;
            this.rage = rage;
            this.rgender = rgender;
            this.rdrivinglicense = rdrivinglicense;
        }

        @Override
        public View getView(int position,  View convertView,  ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View satir = layoutInflater.inflate(R.layout.custom_searchlistview, parent, false);

            TextView kullaniciadi = satir.findViewById(R.id.tvUserName);
            TextView age = satir.findViewById(R.id.tvage);
            TextView namesurname = satir.findViewById(R.id.tvnamesurname);
            TextView drivinglicense = satir.findViewById(R.id.tvdrivinglicense);
            TextView gender = satir.findViewById(R.id.tvgender);

            kullaniciadi.setText(rKullaniciadi[position]);
            age.setText(rage[position]);
            namesurname.setText(rnamesurname[position]);
            drivinglicense.setText(rdrivinglicense[position]);
            gender.setText(rgender[position]);
            return satir;
        }
    }

    public void search() {
        kullaniciadiList.clear();
        ageList.clear();
        drivinglicenseList.clear();
        namesurnameList.clear();
        genderList.clear();

        String searchText = searchtext.getText().toString();

        String sql = "SELECT * FROM employee WHERE nickname=?";
        Cursor cursor = database.rawQuery(sql, new String[]{searchText});

        int kullaniciadiIndex = cursor.getColumnIndex("nickname");
        int drivingIndex = cursor.getColumnIndex("drivinglicense");
        int ageIndex = cursor.getColumnIndex("age");
        int namesurnameIndex = cursor.getColumnIndex("namesurname");
        int genderIndex = cursor.getColumnIndex("gender");

        if (cursor.getCount() == 0) {
            // Kullanıcı bulunamadı, Toast mesajı göster
            Toast.makeText(this, "Registered user not found!", Toast.LENGTH_SHORT).show();
            // ListView'in içeriğini sıfırla
            listview.setAdapter(null);
        } else {
            // Kullanıcılar bulundu, ListView'e verileri yükle
            while (cursor.moveToNext()) {
                String kullaniciadi = cursor.getString(kullaniciadiIndex);
                String driving = cursor.getString(drivingIndex);
                String age = cursor.getString(ageIndex);
                String namesurname = cursor.getString(namesurnameIndex);
                String gender = cursor.getString(genderIndex);

                kullaniciadiList.add(kullaniciadi);
                drivinglicenseList.add(driving);
                ageList.add(age);
                namesurnameList.add(namesurname);
                genderList.add(gender);
            }

            cursor.close();

            // ArrayList'leri String dizilerine dönüştür
            String[] kullaniciadiArray = kullaniciadiList.toArray(new String[0]);
            String[] ageArray = ageList.toArray(new String[0]);
            String[] drivinglicenseArray = drivinglicenseList.toArray(new String[0]);
            String[] namesurnameArray = namesurnameList.toArray(new String[0]);
            String[] genderArray = genderList.toArray(new String[0]);

            BenimAdapter adapter = new BenimAdapter(this, kullaniciadiArray, namesurnameArray, genderArray, drivinglicenseArray, ageArray);
            listview.setAdapter(adapter);
        }
    }
}
