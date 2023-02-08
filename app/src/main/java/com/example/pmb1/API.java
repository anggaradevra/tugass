package com.example.pmb1;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    @FormUrlEncoded
    @POST("simpan.php")
    Call<respon> simpan(@Field("email") String email,
                        @Field("password") String password,
                        @Field("nama") String nama,
                        @Field("tanggal_lahir") String tanggal_lahir,
                        @Field("jenis_kelamin") String jenis_kelamin,
                        @Field("alamat") String alamat,
                         @Field("asal_sekolah") String asal_sekolah,
                         @Field("nomor") String nomor);

    @FormUrlEncoded
    @POST("signin.php")
    Call<respon> signin (@Field("email") String email,
                         @Field("password") String password);

    @FormUrlEncoded
    @POST("data.php")
    Call <respon> data (@Field("id_mahasiswa") String id_mahasiswa);

    @FormUrlEncoded
    @POST("edit.php")
    Call<respon> edit(@Field("id_mahasiswa") String id_mahasiswa,
                        @Field("nama") String nama,
                        @Field("tanggal_lahir") String tanggal_lahir,
                        @Field("jenis_kelamin") String jenis_kelamin,
                        @Field("alamat") String alamat,
                        @Field("asal_sekolah") String asal_sekolah,
                        @Field("nomor") String nomor,
                        @Field("email") String email);

    @GET("list_mahasiswa.php")
    Call<respon> list_mahasiswa();

}
