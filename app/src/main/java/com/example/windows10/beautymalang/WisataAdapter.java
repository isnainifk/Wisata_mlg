package com.example.windows10.beautymalang;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.MyViewHolder> {
    //    private Context context;
    private List<WisataModel> myWisata;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, alamat, deskripsi;
        public MyViewHolder(View v) {
            super(v);
            nama = itemView.findViewById(R.id.nama_wisata);
            alamat = itemView.findViewById(R.id.alamat);
            deskripsi = itemView.findViewById(R.id.deskripsi);
        }
    }

    public WisataAdapter(List<WisataModel> myWisata) {
        this.myWisata = myWisata;
    }

    @Override
    public WisataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_wisata, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        WisataModel menu = myWisata.get(position);
        holder.nama.setText(menu.getNamaWisata());
        holder.alamat.setText(menu.getAlamat());
        holder.deskripsi.setText(menu.getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return myWisata.size();
    }
}

