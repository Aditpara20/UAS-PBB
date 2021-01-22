package com.example.tugasleaf;

public class Tanaman {
    String Key;
    String Nama;
    String Keterangan;
    String Harga;

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String keterangan) {
        Keterangan = keterangan;
    }

    public Tanaman() {

    }

    public Tanaman(String key, String nama, String keterangan, String harga) {
        Key = key;
        Nama = nama;
        Keterangan = keterangan;
        Harga = harga;
    }
}
