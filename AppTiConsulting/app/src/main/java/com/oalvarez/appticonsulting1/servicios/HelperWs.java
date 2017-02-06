package com.oalvarez.appticonsulting1.servicios;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oalvarez.appticonsulting1.entidades.Preferencias;
import com.oalvarez.appticonsulting1.entidades.SessionManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by oalvarez on 14/12/2016.
 */

public class HelperWs {


    @NonNull
    public static Retrofit getConfiguration(Context context){

        SessionManager sessionManager = new SessionManager(context);
        Preferencias preferencias = sessionManager.obtenerPreferencias();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        return new Retrofit.Builder()
                .baseUrl(preferencias.getUrlServer())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
