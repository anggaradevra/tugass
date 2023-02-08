package com.example.pmb1;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signUp extends AppCompatActivity implements View.OnClickListener {
    EditText nama, alamat, tanggal_lahir, agama, nomor, email, passLogin, pass2Login;
    Button daftar;
    RadioGroup jenis_kelamin;
    String r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nama = (EditText) findViewById(R.id.et_nama);
        alamat = (EditText) findViewById(R.id.et_alamat);
        tanggal_lahir = (EditText) findViewById(R.id.et_tgl);
        agama = (EditText) findViewById(R.id.et_asal);
        nomor = (EditText) findViewById(R.id.et_nomor);
        email = (EditText) findViewById(R.id.et_email);
        passLogin = (EditText) findViewById(R.id.et_passLogin);
        pass2Login = (EditText) findViewById(R.id.et_passConfirm);
        daftar = (Button) findViewById(R.id.bt_daftar);

        jenis_kelamin = (RadioGroup) findViewById(R.id.radiogroup);
        jenis_kelamin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.RB_Laki){
                    r = String.valueOf("laki-laki");
                }else {
                     r = String.valueOf("perempuan");
                }
            }
        });

        daftar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_daftar:
            daftar();
                break;
            default:
                break;
        }
    }
    public void daftar(){
        String nama_ = nama.getText().toString();
        String tanggal_lahir_ = tanggal_lahir.getText().toString();
        String jenis_kelamin_ = jenis_kelamin.toString();
        String alamat_ = alamat.getText().toString();
        String asal_sekolah_ = agama.getText().toString();
        String nomor_ = nomor.getText().toString();
        String email_ = email.getText().toString();
        String passlogin_ = passLogin.getText().toString();
        String passConfirm = pass2Login.getText().toString();

        API api = koneksi.getClient().create(API.class);

        Call<respon> aksi = api.simpan(email_, passlogin_,nama_,tanggal_lahir_ ,jenis_kelamin_, alamat_, asal_sekolah_, nomor_);
        aksi.enqueue(new Callback<respon>() {
            @Override
            public void onResponse(Call<respon> call, Response<respon> response) {
                String pesan = response.body().getMessage();
                Toast.makeText(signUp.this, pesan,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<respon> call, Throwable t) {
                Toast.makeText(signUp.this,"Tidak ada koneksi",Toast.LENGTH_SHORT).show();

            }
        });

        if (passlogin_.equals(passConfirm)){
            Toast.makeText(signUp.this,"password telah sesuai",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(signUp.this,"masukan kembali password anda",Toast.LENGTH_SHORT).show();
            daftar.setEnabled(false);
        }

    }
}
