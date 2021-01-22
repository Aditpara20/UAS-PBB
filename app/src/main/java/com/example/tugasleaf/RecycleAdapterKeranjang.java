package com.example.tugasleaf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleAdapterKeranjang extends RecyclerView.Adapter<RecycleAdapterKeranjang.MyViewHolder> {


    private List<Transaksi> trans;
    private Context context;
    //Deklarasi objek dari Interfece
    RecycleAdapterKeranjang.dataListener listener;

    public RecycleAdapterKeranjang(List<Transaksi> trans, Context context) {
        this.trans = trans;
        this.context = context;
        listener = (RecycleAdapterKeranjang.dataListener) context;
    }

    public interface dataListener {
        void onDeleteData(Transaksi transaksi, int position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_item_keranjang,parent,false);

        return new RecycleAdapterKeranjang.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String nama = trans.get(position).getNama();
        String jumlah = trans.get(position).getJumlah();
        String harga = trans.get(position).getHarga();
        String email = trans.get(position).getEmail();

        holder._nama.setText("Nama " + nama);
        holder._harga.setText("Harga " + harga);

    }

    @Override
    public int getItemCount() {
        return trans.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView _nama;
        TextView _harga;
        TextView _total;
        //TextView _email;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            _nama = itemView.findViewById(R.id.txtKeranjangNama);
            _harga = itemView.findViewById(R.id.txtKeranjangHarga);
            //_total = itemView.findViewById(R.id.txtKeranjangTotal);
            cardView = itemView.findViewById(R.id.CardViewListKeranjang);

        }
    }
}
