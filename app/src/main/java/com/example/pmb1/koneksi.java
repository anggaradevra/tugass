package com.example.pmb1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class koneksi {
    public static final String Link = "http://192.168.240.1/maba/";
    public static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(Link).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return null;
    }
}