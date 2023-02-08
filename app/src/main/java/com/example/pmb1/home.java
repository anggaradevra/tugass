package com.example.pmb1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home extends AppCompatActivity implements View.OnClickListener {
    TextView biodata, jurusan, maba, signOut, txtUser;
    private List<tampil> results = new ArrayList<>();
    private Intent Intent;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        biodata = (TextView) findViewById(R.id.tv_biodata);
        jurusan = (TextView) findViewById(R.id.tv_jurusan);
        maba = (TextView) findViewById(R.id.tv_listmaba);


        biodata.setOnClickListener(this);
        jurusan.setOnClickListener(this);
        maba.setOnClickListener(this);
        data();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_biodata:
                Intent b = new Intent(home.this, biodata.class);
                startActivity(b);
                break;

            case R.id.tv_jurusan:
                Intent j = new Intent(home.this, jurusan.class);
                startActivity(j);
                break;

            case R.id.tv_listmaba:
                Intent d = new Intent(home.this, list_maba.class);
                startActivity(d);
                break;
        }
    }

    private void data() {
        SharedPreferences sharedPreferences = getSharedPreferences("tugas", Context.MODE_PRIVATE);
        String id_mahasiswa_ = sharedPreferences.getString("id_mahasiswa", "0");

        API api = koneksi.getClient().create(API.class);

        Call<respon> aksi = api.data(id_mahasiswa_);

        aksi.enqueue(new Callback<respon>() {
            @Override
            public void onResponse(Call<respon> call, Response<respon> response) {
                String kode = response.body().getValue();
                results.clear();
                if (kode.equals("1")) {
                    List<respon.Result> results = response.body().getResult();
                    for (int i =0; i<results.size(); i++){
                        txtUser.setText(results.get(i).getNama());
                    }
                }
            }

            @Override
            public void onFailure(Call<respon> call, Throwable t) {
                Toast.makeText(home.this,"tidak ada koneksi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
