package com.example.mochnirwanfirdaus.beautymalang;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class View_Activity extends AppCompatActivity {

    private MyDataHelper dbHelper;
    public TextView nama, alamat, deskripsi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_);
        Intent intent = getIntent();

        nama = findViewById(R.id.data_nama);
        alamat = findViewById(R.id.data_alamat);
        deskripsi = findViewById(R.id.data_deskripsi);

        dbHelper = new MyDataHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int id = Integer.parseInt(intent.getStringExtra("id"));
        Cursor cursor = db.rawQuery("SELECT * FROM wisata WHERE id="+id,null);
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            // Set Variabel dengan Intent
            nama.setText(cursor.getString(1));
            alamat.setText(cursor.getString(2));
            deskripsi.setText("Rp "+cursor.getString(3)); }
    }
}
