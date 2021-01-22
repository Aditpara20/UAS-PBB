package com.example.tugasleaf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BarangPotActivity extends AppCompatActivity implements RecycleAdapterBarang.dataListener  {

    RecycleAdapterBarang recycleAdapterBarangPot;
    DatabaseReference databaseReference;

    ArrayList<Barang> listpot;
    RecyclerView rvPot;
    String jenis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang_pot);

        Intent intent = getIntent();
        jenis = intent.getStringExtra("Jenis");

        databaseReference = FirebaseDatabase.getInstance().getReference();
        rvPot = findViewById(R.id.rv_barangpot_view);
        RecyclerView.LayoutManager mLayoutPot = new LinearLayoutManager(this);
        rvPot.setLayoutManager(mLayoutPot);
        rvPot.setItemAnimator(new DefaultItemAnimator());

        ShowData();

    }

    private void ShowData() {
        Query query;
        if (jenis.equals("Pot")) {
            query = databaseReference.child("Barang").orderByChild("Jenis").equalTo("Pot");
        }
        else if (jenis.equals("Pupuk")) {
            query = databaseReference.child("Barang").orderByChild("Jenis").equalTo("Pupuk");
        }
        else if (jenis.equals("Tanaman")){
            query =  databaseReference.child("Barang").orderByChild("Jenis").equalTo("Tanaman");
        }else{
            query =  databaseReference.child("Barang");
        }



//        databaseReference.child("Barang").orderByChild("Jenis").equalTo("Pot").addValueEventListener(new ValueEventListener() {
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listpot = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()) {
                    Barang brg = item.getValue(Barang.class);
                    brg.setKey(item.getKey());
                    listpot.add(brg);
                }

                recycleAdapterBarangPot = new RecycleAdapterBarang(listpot, BarangPotActivity.this);
                rvPot.setAdapter(recycleAdapterBarangPot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onDeleteData(Barang barang, int position) {

    }
}