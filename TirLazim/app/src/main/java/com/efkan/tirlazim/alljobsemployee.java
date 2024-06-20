package com.efkan.tirlazim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class alljobsemployee extends AppCompatActivity {
    ListView listView;
    ArrayList<String> urunTurleri, baslangicBitisTarihleri, baslangicBitisKonumlari, fiyatlar, kullaniciadim;
    SQLiteDatabase database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alljobsemployee);
        listView = findViewById(R.id.listview1); // ListView ID'sinin doğru olduğundan emin olun
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color));

        database = this.openOrCreateDatabase("Truck", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS jobs(id INTEGER PRIMARY KEY, kullaniciadi VARCHAR, urunturu VARCHAR, baslangickonumu VARCHAR, bitiskonumu VARCHAR, fiyat VARCHAR, baslangictarihi VARCHAR, bitistarihi VARCHAR)");

        urunTurleri = new ArrayList<>();
        baslangicBitisTarihleri = new ArrayList<>();
        baslangicBitisKonumlari = new ArrayList<>();
        fiyatlar = new ArrayList<>();
        kullaniciadim = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM jobs", null);
        int kullaniciadiIndex = cursor.getColumnIndex("kullaniciadi");
        int urunturuIndex = cursor.getColumnIndex("urunturu");
        int baslangickonumuIndex = cursor.getColumnIndex("baslangickonumu");
        int bitiskonumuIndex = cursor.getColumnIndex("bitiskonumu");
        int fiyatIndex = cursor.getColumnIndex("fiyat");
        int baslangictarihiIndex = cursor.getColumnIndex("baslangictarihi");
        int bitistarihiIndex = cursor.getColumnIndex("bitistarihi");

        while (cursor.moveToNext()) {
            String kullaniciadi = cursor.getString(kullaniciadiIndex);
            String urunturu = cursor.getString(urunturuIndex);
            String baslangicKonumu = cursor.getString(baslangickonumuIndex);
            String bitisKonumu = cursor.getString(bitiskonumuIndex);
            String fiyat = cursor.getString(fiyatIndex);
            String baslangictarihi = cursor.getString(baslangictarihiIndex);
            String bitistarihi = cursor.getString(bitistarihiIndex);

            kullaniciadim.add(kullaniciadi);
            urunTurleri.add("Product: " + urunturu);
            baslangicBitisTarihleri.add(baslangictarihi + "-\n" + bitistarihi);
            baslangicBitisKonumlari.add(baslangicKonumu + " - " + bitisKonumu);
            fiyatlar.add(fiyat + " $");
        }
        cursor.close();  // Cursor'u kapatmayı unutmayın

        String kullaniciAdiArray[] = kullaniciadim.toArray(new String[0]);
        String urunTurleriArray[] = urunTurleri.toArray(new String[0]);
        String baslangicBitisTarihleriArray[] = baslangicBitisTarihleri.toArray(new String[0]);
        String baslangicBitisKonumlariArray[] = baslangicBitisKonumlari.toArray(new String[0]);
        String fiyatlarArray[] = fiyatlar.toArray(new String[0]);

        BenimAdapter adapter = new BenimAdapter(this, kullaniciAdiArray, urunTurleriArray, baslangicBitisTarihleriArray, baslangicBitisKonumlariArray, fiyatlarArray);
        listView.setAdapter(adapter);
    }

    class BenimAdapter extends ArrayAdapter<String> {
        Context context;
        String[] rKullaniciadi, rUrunTurleri, rBaslangicBitisTarihleri, rBaslangicBitisKonumlari, rFiyatlar;

        BenimAdapter(Context c, String[] kullaniciAdi, String[] urunTurleri, String[] baslangicBitisTarihleri, String[] baslangicBitisKonumlari, String[] fiyatlar) {
            super(c, R.layout.custom_listviewemployee, R.id.usernameTextView, kullaniciAdi);
            this.context = c;
            this.rKullaniciadi = kullaniciAdi;
            this.rUrunTurleri = urunTurleri;
            this.rBaslangicBitisTarihleri = baslangicBitisTarihleri;
            this.rBaslangicBitisKonumlari = baslangicBitisKonumlari;
            this.rFiyatlar = fiyatlar;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View satir = layoutInflater.inflate(R.layout.custom_listviewemployee, parent, false);

            TextView kullaniciadi = satir.findViewById(R.id.usernameTextView);
            TextView urunturu = satir.findViewById(R.id.productTypeTextView);
            TextView baslangicbitistarihi = satir.findViewById(R.id.dateTextView);
            TextView baslangicbitiskonumu = satir.findViewById(R.id.locationTextView);
            TextView fiyat = satir.findViewById(R.id.priceTextView);

            kullaniciadi.setText(rKullaniciadi[position]);
            urunturu.setText(rUrunTurleri[position]);
            baslangicbitistarihi.setText(rBaslangicBitisTarihleri[position]);
            baslangicbitiskonumu.setText(rBaslangicBitisKonumlari[position]);
            fiyat.setText(rFiyatlar[position]);

            return satir;
        }
    }
}
