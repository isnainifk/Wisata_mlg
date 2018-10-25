package com.example.mochnirwanfirdaus.beautymalang;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insert_Activity extends AppCompatActivity {

    private MyDataHelper dbHelper;
    private EditText nama, alamat, deskripsi;
    Button simpan, kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_);

        dbHelper = new MyDataHelper(this);

        nama = findViewById(R.id.insert_nama);
        alamat = findViewById(R.id.update_alamat);
        deskripsi = findViewById(R.id.update_deskripsi);

        simpan = findViewById(R.id.insert_tambah);
        kembali = findViewById(R.id.update_back);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO wisata(nama_wisata, alamat, deskripsi) values('" +
                        nama.getText().toString() + "', '" +
                        alamat.getText().toString() + "', '" +
                        deskripsi.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), " Berhasil ditambahkan",
                        Toast.LENGTH_LONG).show();

                MainActivity.layarutama.TampilData();
                finish();
            }
        });

//        kembali ke halaman utama
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}
