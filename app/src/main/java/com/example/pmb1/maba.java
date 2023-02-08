package com.example.pmb1;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class maba extends AppCompatActivity {
    RecyclerView tampil;
    List<tampil> results = new ArrayList<>();
    private adapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_maba);

        tampil = (RecyclerView)findViewById(R.id.rv_data);
        tampil.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        tampil.setLayoutManager(llm);
    }

    private void tampil() {
        API api = koneksi.getClient().create(API.class);
        Call<respon> aksi = api.list_mahasiswa();

        aksi.enqueue(new Callback<respon>() {
            @Override
            public void onResponse(Call<respon> call, Response<respon> response) {
                String kode = response.body().getValue();
                results.clear();
                if (kode.equals("1")) {
//                    results = response.body().getResult();
                    viewAdapter = new adapter(maba.this, results);
                    tampil.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onFailure(Call<respon> call, Throwable t) {
                Toast.makeText(maba.this,"tidak ada koneksi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
