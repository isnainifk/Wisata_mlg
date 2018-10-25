package com.example.mochnirwanfirdaus.beautymalang;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyDataHelper dbHelper;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity layarutama;
    private List<WisataModel> myWisata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layarutama = this;
        dbHelper = new MyDataHelper(this);

        Button btn = findViewById(R.id.main_tambah);
        Button btn2 = findViewById(R.id.main_out);

        //aksi button untuk berpindah ke halaman insert
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(
                        MainActivity.this, Insert_Activity.class);
                startActivity(myintent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
//                resetPreference();
//                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
//                 i.putExtra("msg", "Logout success!");
//                startActivity(i);

                SharedPreferences handler = getSharedPreferences(
                        "loginData", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = handler.edit();
                editor.clear();
                editor.apply();

                Intent i = new Intent(getApplicationContext(), LoginActivity.class);;
                startActivity(i);
            }
        });

        mRecyclerView = findViewById(R.id.recycler);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        TampilData();

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, new ClickListenner() {
            @Override
            public void onClick(final View v, int position) {
                final WisataModel wisata = myWisata.get(position);
                final CharSequence[] dialogitem = {"Lihat Menu","Edit Menu","Hapus Menu"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent lihatMenu = new Intent(getApplicationContext(), View_Activity.class);
                                lihatMenu.putExtra("id", ""+wisata.getId());
                                startActivity(lihatMenu);
                                break;
                            case 1:
                                Intent editMenu = new Intent(getApplicationContext(),Update_Activity.class);
                                editMenu.putExtra("id",""+wisata.getId());
                                startActivity(editMenu);
                                break;
                            case 2:
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                int id = wisata.getId();
                                db.execSQL("DELETE FROM wisata WHERE id="+id+"");
                                TampilData();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        }));
    }

    //private void resetPreference() {
    //  SharedPreferences handler = getSharedPreferences("MainActivity", Context.MODE_PRIVATE);
    // SharedPreferences.Editor editor = handler.edit();
    // editor.remove("username");
    // editor.remove("password");
    // editor.apply();
    //}

    public void TampilData(){
        this.myWisata= new ArrayList<>();
        myWisata.addAll(getAll());
        mAdapter = new WisataAdapter(myWisata);
        mRecyclerView.setAdapter(mAdapter);
    }

    public List<WisataModel> getAll(){
        List<WisataModel> wisataList = new ArrayList<>();
        String selectQuery = "SELECT * FROM wisata";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                WisataModel wisata = new WisataModel(cursor.getInt(0),
                        cursor.getString(1),cursor.getString(2),
                        cursor.getString(3));
                wisataList.add(wisata);
            } while (cursor.moveToNext());
        }
        return wisataList;
    }
}
