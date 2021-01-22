package com.example.tugasleaf;

public class Transaksi {
    private String Nama;
    private String Harga;
    private String Jumlah;
    private String Email;

    public Transaksi() {

    }

    public Transaksi(String nama, String harga, String jumlah, String email) {
        Nama = nama;
        Harga = harga;
        Jumlah = jumlah;
        Email = email;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        nama = nama;
    }

    public String getJumlah() {
        return Jumlah;
    }

    public void setJumlah(String jumlah) {
        jumlah = jumlah;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        harga = harga;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        email = email;
    }
}
