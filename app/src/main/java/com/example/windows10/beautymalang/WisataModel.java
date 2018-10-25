package com.example.windows10.beautymalang;

public class WisataModel {

    private int id;
    private String nama_wisata;
    private String alamat;
    private String deskripsi;;

    public WisataModel(int id, String nama_wisata, String alamat, String deskripsi) {
        this.id = id;
        this.nama_wisata = nama_wisata;
        this.alamat = alamat;
        this.deskripsi = deskripsi;
    }

    public int getId() {
        return id;
    }

    public String getNamaWisata() {
        return nama_wisata;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

}