package com.example.tugasleaf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private ArrayList<Sampah> nList;
    private Context context;

    //Membuat Interfece
    public interface dataListener{
        void onDeleteData(Sampah sampah, int position);
    }

    //Deklarasi objek dari Interfece
    dataListener listener;


    public RecycleAdapter( ArrayList<Sampah> nList,Context context){
        this.nList = nList;
        this.context = context;
        listener = (dataListener)context;
    }

    @NonNull
    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_item_sampah,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.MyViewHolder holder, int position) {
        //Sampah smp = nList.get(position);
        String nama = nList.get(position).getNama();
        String keterangan = nList.get(position).getKeterangan();

        holder._nama.setText("Nama : " + nama);
        holder._keterangan.setText("Keterangan : " + keterangan);
    }

    @Override
    public int getItemCount() {
        return nList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView _nama;
        TextView _keterangan;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            _nama = itemView.findViewById(R.id.txtNama);
            _keterangan = itemView.findViewById(R.id.txtKeterangan);
            cardView = itemView.findViewById(R.id.CardViewListSampah);

        }
    }
}
