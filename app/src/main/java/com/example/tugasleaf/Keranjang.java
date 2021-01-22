package com.example.tugasleaf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Keranjang extends AppCompatActivity implements RecycleAdapterKeranjang.dataListener {

    RecycleAdapterKeranjang rc;
    DatabaseReference db;
    ArrayList<Transaksi> tr;
    RecyclerView rv;
    TextView total;
    Integer jmlTotal = 0;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);

        //total = findViewById(R.id.txtTotalKeranjang);
        db = FirebaseDatabase.getInstance().getReference();
        rv = findViewById(R.id.rv_keranjang);
        RecyclerView.LayoutManager mLayoutPot = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutPot);
        rv.setItemAnimator(new DefaultItemAnimator());
        showData();
    }

    private void showData() {
        Query query;
        FirebaseAuth auth;
        user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail().toString();
        query = db.child("Transaksi").orderByChild("email").equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tr = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()) {
                    tr.add(new Transaksi(item.child("nama").getValue(String.class),item.child("harga").getValue(String.class),
                            item.child("jumlah").getValue(String.class),item.child("email").getValue(String.class)));
//                    Integer jml = Integer.parseInt(item.child("jumlah").getValue(String.class));
//                    Integer hrg = Integer.parseInt(item.child("harga").getValue(String.class));
//                    jmlTotal += jml * hrg;
                }

                rc = new RecycleAdapterKeranjang(tr, Keranjang.this);
                rv.setAdapter(rc);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //total.setText(jmlTotal);
    }

    @Override
    public void onDeleteData(Transaksi transaksi, int position) {

    }
}