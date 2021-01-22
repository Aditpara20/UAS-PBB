package com.example.tugasleaf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class RecycleAdapterBarang extends RecyclerView.Adapter<RecycleAdapterBarang.MyViewHolder> {

    private List<Barang> nPot;
    private Context context;
    String getUserID;
    FirebaseUser user;

    public RecycleAdapterBarang(ArrayList<Barang> listbarang, Context context) {
        this.nPot = listbarang;
        this.context = context;
        listener = (dataListener) context;
    }

    public interface dataListener {
        void onDeleteData(Barang barang, int position);
    }

    //Deklarasi objek dari Interfece
    RecycleAdapterBarang.dataListener listener;

    @NonNull
    @Override
    public RecycleAdapterBarang.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_item_pot,parent,false);

        return new RecycleAdapterBarang.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterBarang.MyViewHolder holder, final int position) {
        String nama = nPot.get(position).getNama();
        String keterangan = nPot.get(position).getKeterangan();
        final String harga = nPot.get(position).getHarga();

        holder.tv_nama.setText("Nama " + nama);
        holder.tv_keterangan.setText("Keterangan " + keterangan);
        holder.tv_harga.setText("Harga " + harga);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               Toast.makeText(context,"Klik " + nPot.get(position).getNama(),Toast.LENGTH_SHORT).show();
//            }
//        });

        holder.btnBeliPot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference dbref = database.getReference("Transaksi");
                FirebaseAuth auth;
                user = FirebaseAuth.getInstance().getCurrentUser();

                String _nama = nPot.get(position).getNama();
                String _jumlah = "1";
                String _harga = nPot.get(position).getHarga();
                String _email = user.getEmail();

                dbref.push().setValue(new Transaksi(_nama,_jumlah,_harga,_email)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context,"Sukses Masuk Keranjang",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context,"Data Gagal Masuk Keranjang",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });



    }

    @Override
    public int getItemCount() {
        return nPot.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nama;
        TextView tv_keterangan;
        TextView tv_harga;
        Button btnBeliPot;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_nama = itemView.findViewById(R.id.tv_pot_nama);
            tv_keterangan = itemView.findViewById(R.id.tv_pot_keterangan);
            tv_harga = itemView.findViewById(R.id.tv_pot_harga);
            btnBeliPot = itemView.findViewById(R.id.btn_pot_beli);
            cardView = itemView.findViewById(R.id.CardViewListPot);
        }
    }
}
