package com.example.tugasleaf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PotActivity extends AppCompatActivity implements RecycleAdapterBarang.dataListener {

    RecycleAdapterBarang recycleAdapterPot;
    DatabaseReference databaseReference;
    //DatabaseReference databaseReference2 = databaseReference1.child("Pot");
    ArrayList<Barang> listpot;
    RecyclerView rvPot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pot);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        rvPot = findViewById(R.id.rv_pot);
        RecyclerView.LayoutManager mLayoutPot = new LinearLayoutManager(this);
        rvPot.setLayoutManager(mLayoutPot);
        rvPot.setItemAnimator(new DefaultItemAnimator());

        ShowData();
    }

    private void ShowData(){
        databaseReference.child("Barang").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listpot = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()) {
//                    String brg = item.child("Nama").getValue(String.class);
//                    Log.d("TAG", brg);
                    Barang brg = item.getValue(Barang.class);
                    brg.setKey(item.getKey());
                    listpot.add(brg);
                }

                recycleAdapterPot = new RecycleAdapterBarang(listpot, PotActivity.this);
                rvPot.setAdapter(recycleAdapterPot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void onDeleteData(Barang pot, int position) {

    }
}