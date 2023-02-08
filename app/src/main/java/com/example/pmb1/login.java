 package com.example.pmb1;

 import android.content.Context;
 import android.content.Intent;
 import android.content.SharedPreferences;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.TextView;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;

 import java.util.ArrayList;
 import java.util.List;

 import retrofit2.Call;
 import retrofit2.Callback;
 import retrofit2.Response;

 public class login extends AppCompatActivity implements View.OnClickListener {
    EditText email, password;
    Button signIn;
    TextView signUp;
    private List<tampil> results = new ArrayList<>();
    private boolean login = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);
        signIn = (Button) findViewById(R.id.bt_signIn);
        signUp = (TextView) findViewById(R.id.tv_signUp);

        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_signIn:
               signin();
                break;
            case R.id.tv_signUp:
                Intent in = new Intent(login.this, com.example.pmb1.signUp.class);
                startActivity(in);
                break;
            default:
                break;
        }
    }

    @Override
    protected void  onResume(){
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("tugas", Context.MODE_PRIVATE);
    login = sharedPreferences.getBoolean("login", false);

    if (login) {
        Intent intent = new Intent(login.this, home.class);
        startActivity(intent);
        finish();

    }
    }

    public void signin (){
        final String email_ = email.getText().toString();
        final String password_ = password.getText().toString();

        API api = koneksi.getClient().create(API.class);

        Call<respon> aksi = api.signin(email_, password_);
        aksi.enqueue(new Callback<respon>() {
            @Override
            public void onResponse(Call<respon> call, Response<respon> response) {
                String kode = response.body().getValue();
                results.clear();
                if (kode.equals("1")){
                    for (int i = 0; i< results.size(); i++) {
                        SharedPreferences sharedPreferences = getSharedPreferences("tugas", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("login", true);
                        editor.putString("id_mahasiswa", results.get(i).id_mahasiswa);
                        editor.commit();
                        startActivity(new Intent(login.this, home.class));
                    finish();
                    }
                }else {
                    Toast.makeText(login.this, "Email dan Password anda salah!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<respon> call, Throwable t) {
                Toast.makeText(login.this, "Tidak ada koneksi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}