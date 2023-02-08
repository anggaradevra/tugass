package com.example.pmb1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.MyHolder> {
    public Context context;
    public List<tampil> results;

    public adapter(Context context, List<tampil> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_data_maba, viewGroup, false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.id_mahasiswa.setText(results.get(i).getId_mahasiswa());
        myHolder.nama.setText(results.get(i).getNama());
        myHolder.tanggal_lahir.setText(results.get(i).getTanggal_lahir());
        myHolder.jenis_kelamin.setText(results.get(i).getJenis_kelamin());
        myHolder.alamat.setText(results.get(i).getAlamat());
        myHolder.asal_sekolah.setText(results.get(i).getAsal_sekolah());
        myHolder.nomor.setText(results.get(i).getNomor());
        myHolder.email.setText(results.get(i).getEmail());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView id_mahasiswa, nama, tanggal_lahir, jenis_kelamin, alamat, asal_sekolah, nomor, email;

        public MyHolder(View itemView) {
            super(itemView);
            id_mahasiswa = itemView.findViewById(R.id.tv_DId);
            nama = itemView.findViewById(R.id.tv_DNama);
            tanggal_lahir = itemView.findViewById(R.id.tv_DTgl);
            jenis_kelamin = itemView.findViewById(R.id.tv_DJk);
            alamat = itemView.findViewById(R.id.tv_DAlamat);
            asal_sekolah = itemView.findViewById(R.id.tv_DAsal);
            nomor = itemView.findViewById(R.id.tv_DNomor);
            email = itemView.findViewById(R.id.tv_DEmail);

        }
    }
}
