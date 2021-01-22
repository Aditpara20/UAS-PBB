package com.example.tugasleaf;

public class Sampah {
    String Key;
    String Nama;
    String Keterangan;

    public Sampah(){

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

    public Sampah(String key, String nama, String keterangan) {
        Key = key;
        Nama = nama;
        Keterangan = keterangan;
    }
}
