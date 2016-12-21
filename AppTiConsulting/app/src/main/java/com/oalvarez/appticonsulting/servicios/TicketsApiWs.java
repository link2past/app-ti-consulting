package com.oalvarez.appticonsulting.servicios;

import com.oalvarez.appticonsulting.entidades.*;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by oalvarez on 14/12/2016.
 */

public interface TicketsApiWs {

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("usuario")
    Call<Token> Login(@Body Usuario usuario);

    @GET("ticket/{id}")
    Call<ArrayList<Ticket>> ConsultarTicketsAsignados(@Path("id") String sIdUsuarioAsignado);

}
