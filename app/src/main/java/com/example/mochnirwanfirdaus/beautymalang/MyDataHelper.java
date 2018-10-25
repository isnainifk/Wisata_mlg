package com.example.mochnirwanfirdaus.beautymalang;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataHelper extends SQLiteOpenHelper {
    //    MENGISI VARIABEL NAMA_DB DENGAN NAMA DB
    private static final String NAMA_DB = "dataWisata.db";
    //    MENGISI VARIABEL NAMA_TABEL DENGAN NAMA TABEL
    private static final String NAMA_TABEL = "wisata";
    //    MEMASUKKAN VERSI DARI DATABASE
    private static final int VERSI_DB = 1;

    //    DEKALARI VARIABEL YANG BERISI NAMA KOLOM
    private static final String ID = "id";
    private static final String NAMA_WISATA = "nama_wisata";
    private static final String ALAMAT = "alamat";
    private static final String DESKRIPSI = "deskripsi";

    //    MEMASUKKAN QUERI PADA VARIBEL CREATE_TABLE
    private static final String CREATE_TABLE = "CREATE TABLE " + NAMA_TABEL + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAMA_WISATA + " VARCHAR(255), "+ ALAMAT + " VARCHAR(255), "+ DESKRIPSI + " VARCHAR(255));";

    //    MEMASUKKAN QUERI CADANGAN PADA VARIBEL CREATE_TABLE
    //    private static final String CREATE_TABLE_REVISI = "CREATE TABLE " + NAMA_TABEL + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + JUDUL + " VARCHAR(255), " + TAHUNTERBIT + " VARCHAR(255)," +
    //    PENERBIT + " VARCHAR(255)," + SUTRADARA + " VARCHAR(255)," + RATING + " VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NAMA_TABEL;

    public MyDataHelper(Context context){
        super(context,NAMA_DB,null,VERSI_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//    EKSEKUSI QUERI
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<1){
            db.execSQL(DROP_TABLE);
//            db.execSQL(CREATE_TABLE_REVISI);
        }
    }
}
