package com.example.mochnirwanfirdaus.beautymalang;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Activity extends AppCompatActivity {

    protected Cursor cursor;
    MyDataHelper dbHelper;
    Button btn1, btn2;
    EditText text6,text3,text4,text5,text2;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_);

        //menempatkan var data ke masing2 id field
        dbHelper = new MyDataHelper(this);
        text2 = (EditText) findViewById(R.id.update_nama);
        text3 = (EditText) findViewById(R.id.update_alamat);
        text4 = (EditText) findViewById(R.id.update_deskripsi);

        btn1 = (Button) findViewById(R.id.update);
        btn2 = (Button) findViewById(R.id.update_back);

        //inisialisasi db untuk dbhelper
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //query untuk mengambil data untuk judul yang dipilih
        cursor = db.rawQuery("SELECT * FROM wisata WHERE id = '" +
                getIntent().getStringExtra("id") + "'", null);
        cursor.moveToFirst();

        //mendapatkan data
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            ID = cursor.getString(0);
            text2.setText(cursor.getString(1));
            text3.setText(cursor.getString(2));
            text4.setText(cursor.getString(3));
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //inisialisasi db untuk menulis query
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //query untuk update
                db.execSQL(" UPDATE wisata SET nama_wisata='" + text2.getText().toString() + "', alamat = '" +
                        text3.getText().toString() +
                        "', deskripsi = '" + text4.getText().toString()
                        + "' WHERE id ='" + getIntent().getStringExtra("id") + "'");
                Toast.makeText(getApplicationContext(), "berhasil diubah", Toast.LENGTH_LONG).show();
                //kembali ke halaman utama atau listview
                MainActivity.layarutama.TampilData();
                finish();

            }
        });

        //button kembali
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });

    }
}
