package com.efkan.tirlazim;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.efkan.tirlazim.R;

import java.util.ArrayList;

public class AllJobsEmployerActivity extends AppCompatActivity {
    ArrayList<String> urunTurleri, baslangicBitisTarihleri, baslangicBitisKonumlari, fiyatlar;
    ListView listview;
    String kullaniciadi;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alljobsemployer);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color));
        listview = findViewById(R.id.listview);
        Intent intent = getIntent();
        kullaniciadi = intent.getStringExtra("kullaniciadi");
        database = this.openOrCreateDatabase("Truck", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS jobs(id INTEGER PRIMARY KEY,kullaniciadi VARCHAR,urunturu VARCHAR,baslangickonumu VARCHAR,bitiskonumu VARCHAR,fiyat VARCHAR,baslangictarihi VARCHAR,bitistarihi VARCHAR)");
        urunTurleri = new ArrayList<>();
        baslangicBitisTarihleri = new ArrayList<>();
        baslangicBitisKonumlari = new ArrayList<>();
        fiyatlar = new ArrayList<>();

        String sql = "SELECT * FROM jobs WHERE kullaniciadi=?";
        Cursor cursor = database.rawQuery(sql, new String[]{kullaniciadi});
        int urunturuIndex = cursor.getColumnIndex("urunturu");
        int baslangickonumuIndex = cursor.getColumnIndex("baslangickonumu");
        int bitiskonumuIndex = cursor.getColumnIndex("bitiskonumu");
        int fiyatIndex = cursor.getColumnIndex("fiyat");
        int baslangictarihiIndex = cursor.getColumnIndex("baslangictarihi");
        int bitistarihiIndex = cursor.getColumnIndex("bitistarihi");

        while (cursor.moveToNext()) {
            String urunturu = cursor.getString(urunturuIndex);
            String baslangicKonumu = cursor.getString(baslangickonumuIndex);
            String bitisKonumu = cursor.getString(bitiskonumuIndex);
            String fiyat = cursor.getString(fiyatIndex);
            String baslangictarihi = cursor.getString(baslangictarihiIndex);
            String bitistarihi = cursor.getString(bitistarihiIndex);

            urunTurleri.add("Product: "+urunturu);
            baslangicBitisTarihleri.add(baslangictarihi + "-\n" + bitistarihi);
            baslangicBitisKonumlari.add(baslangicKonumu + " - " + bitisKonumu);
            fiyatlar.add(fiyat+" $");
        }

        String urunTurleriArray[] = urunTurleri.toArray(new String[0]);
        String baslangicBitisTarihleriArray[] = baslangicBitisTarihleri.toArray(new String[0]);
        String baslangicBitisKonumlariArray[] = baslangicBitisKonumlari.toArray(new String[0]);
        String fiyatlarArray[] = fiyatlar.toArray(new String[0]);

        BenimAdapter adapter = new BenimAdapter(this, urunTurleriArray, baslangicBitisTarihleriArray, baslangicBitisKonumlariArray, fiyatlarArray);
        listview.setAdapter(adapter);
    }

    class BenimAdapter extends ArrayAdapter<String> {
        Context context;
        String rUrunTurleri[], rBaslangicBitisTarihleri[], rBaslangicBitisKonumlari[], rFiyatlar[];

        BenimAdapter(Context c, String urunTurleri[], String baslangicBitisTarihleri[], String baslangicBitisKonumlari[], String fiyatlar[]) {
            super(c, R.layout.custom_listview, R.id.urunturuid, urunTurleri);
            this.context = c;
            this.rUrunTurleri = urunTurleri;
            this.rBaslangicBitisTarihleri = baslangicBitisTarihleri;
            this.rBaslangicBitisKonumlari = baslangicBitisKonumlari;
            this.rFiyatlar = fiyatlar;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View satir = layoutInflater.inflate(R.layout.custom_listview, parent, false);

            TextView urunturu = satir.findViewById(R.id.urunturuid);
            TextView baslangicbitistarihi = satir.findViewById(R.id.tarihid);
            TextView baslangicbitiskonumu = satir.findViewById(R.id.konumid);
            TextView fiyat = satir.findViewById(R.id.fiyatid);

            urunturu.setText(rUrunTurleri[position]);
            baslangicbitistarihi.setText(rBaslangicBitisTarihleri[position]);
            baslangicbitiskonumu.setText(rBaslangicBitisKonumlari[position]);
            fiyat.setText(rFiyatlar[position]);

            return satir;
        }
    }
}
