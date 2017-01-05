package com.oalvarez.appticonsulting.servicios;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by oalvarez on 14/12/2016.
 */

public class HelperWs {

    @NonNull
    public static Retrofit getConfiguration(){

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        return new Retrofit.Builder()
                .baseUrl("http://192.168.1.9/webapitickets/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
