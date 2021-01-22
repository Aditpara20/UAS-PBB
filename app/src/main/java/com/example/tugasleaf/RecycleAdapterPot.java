package com.example.tugasleaf;

import android.content.Context;
import android.os.Debug;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class RecycleAdapterPot extends RecyclerView.Adapter<RecycleAdapterPot.MyViewHolder> {

    private List<Pot> nPot;
    private Context context;

    public RecycleAdapterPot(ArrayList<Pot> listpot,Context context) {
        this.nPot = listpot;
        this.context = context;
        listener = (dataListener) context;
    }

    public interface dataListener {
        void onDeleteData(Pot pot, int position);
    }

    //Deklarasi objek dari Interfece
    RecycleAdapterPot.dataListener listener;

    @NonNull
    @Override
    public RecycleAdapterPot.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_item_pot,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterPot.MyViewHolder holder, final int position) {
        String nama = nPot.get(position).getNama();
        String keterangan = nPot.get(position).getKeterangan();
        String harga = nPot.get(position).getHarga();

        holder.tv_nama.setText(nama);
        holder.tv_keterangan.setText(keterangan);
        holder.tv_harga.setText(harga);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(context,"Klik " + nPot.get(position).getNama(),Toast.LENGTH_SHORT).show();
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
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_nama = itemView.findViewById(R.id.tv_pot_nama);
            tv_keterangan = itemView.findViewById(R.id.tv_pot_keterangan);
            tv_harga = itemView.findViewById(R.id.tv_pot_harga);
            cardView = itemView.findViewById(R.id.CardViewListPot);
        }
    }
}
