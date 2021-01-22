package com.example.tugasleaf;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GreenActivity extends AppCompatActivity implements RecycleAdapter.dataListener {

    RecycleAdapter recycleAdapter;
    DatabaseReference databaseReference;
    ArrayList<Sampah> sampahArrayList;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.rv_sampah_view);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("List Data Sampah");
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ShowData();
    }

    private void ShowData(){
        databaseReference.child("Sampah").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sampahArrayList = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()){
                    Sampah smp = item.getValue(Sampah.class);
                    smp.setKey(item.getKey());

                    sampahArrayList.add(smp);
                }

                recycleAdapter = new RecycleAdapter(sampahArrayList,GreenActivity.this);
                recyclerView.setAdapter(recycleAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDeleteData(Sampah sampah, int position) {

    }
}