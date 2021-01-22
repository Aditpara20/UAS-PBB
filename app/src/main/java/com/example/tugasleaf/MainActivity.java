package com.example.tugasleaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button LogOutBtn;
    private Button greenBtn;
    private Button kategoriPotbtn;
    private  Button kategoriPupukbtn;
    private  Button kategoriTanaman;
    private Button planBtn;
    private  Button keranjangBtn;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = FirebaseAuth.getInstance().getCurrentUser();
        LogOutBtn = findViewById(R.id.btnLogout);
        greenBtn = findViewById(R.id.btnGreen);
        kategoriPotbtn = findViewById(R.id.btnKategoriPot);
        kategoriPupukbtn = findViewById(R.id.btnKategoriPupuk);
        kategoriTanaman = findViewById(R.id.btnKategoriTanaman);
        keranjangBtn = findViewById(R.id.keranjangBtn);
        planBtn = findViewById(R.id.btnPlan);
        LogOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogOut();
            }
        });


        greenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Ke Halaman Green",Toast.LENGTH_SHORT).show();
                Intent greenActivity = new Intent(MainActivity.this,GreenActivity.class);
                startActivity(greenActivity);

            }
        });

        kategoriPotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent barangpotActivity = new Intent(MainActivity.this,BarangPotActivity.class);
                barangpotActivity.putExtra("Jenis","Pot");
                startActivity(barangpotActivity);
            }
        });

        kategoriPupukbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent barangpotActivity = new Intent(MainActivity.this,BarangPotActivity.class);
                barangpotActivity.putExtra("Jenis","Pupuk");
                startActivity(barangpotActivity);
            }
        });



        kategoriTanaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent barangpotActivity = new Intent(MainActivity.this,BarangPotActivity.class);
                barangpotActivity.putExtra("Jenis","Tanaman");
                startActivity(barangpotActivity);
            }
        });

        planBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent barangpotActivity = new Intent(MainActivity.this,BarangPotActivity.class);
                barangpotActivity.putExtra("Jenis","Plan");
                startActivity(barangpotActivity);
            }
        });

        keranjangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent keranjang = new Intent(MainActivity.this,Keranjang.class);
                keranjang.putExtra("Email", user.getEmail());
                startActivity(keranjang);
            }
        });

        Toast.makeText(MainActivity.this,"Selamat Datang " + user.getEmail(),Toast.LENGTH_LONG).show();

    }



    private void LogOut(){
        FirebaseAuth.getInstance().signOut();
        Intent loginActivity = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }
}