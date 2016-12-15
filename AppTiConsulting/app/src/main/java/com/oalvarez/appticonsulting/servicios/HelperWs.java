package com.oalvarez.appticonsulting.servicios;

import android.support.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by oalvarez on 14/12/2016.
 */

public class HelperWs {

    @NonNull
    public static Retrofit getConfiguration(){
        return new Retrofit.Builder()
                .baseUrl("http://192.168.10.189/webapitickets/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
