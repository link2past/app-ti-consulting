package com.oalvarez.appticonsulting.servicios;

import com.oalvarez.appticonsulting.entidades.*;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by oalvarez on 14/12/2016.
 */

public interface TicketsApiWs {

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("usuario")
    Call<Token> Login(@Body Usuario usuario);

}
