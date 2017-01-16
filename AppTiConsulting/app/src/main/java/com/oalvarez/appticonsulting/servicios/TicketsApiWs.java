package com.oalvarez.appticonsulting.servicios;

import com.oalvarez.appticonsulting.entidades.*;

import java.util.ArrayList;

import okhttp3.ResponseBody;
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

    //region Métodos de Usuario

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("usuario")
    Call<Token> Login(@Body Usuario usuario);

    //endregion

    //region Métodos de tickets
    @GET("ticket/{accion}/{id}")
    Call<ArrayList<Ticket>> ConsultarTicketsAsignados(@Path("accion") String sAccion,  @Path("id") String sIdUsuarioAsignado);

    @GET("ticket/{id}")
    Call<Ticket> ConsultarTicket(@Path("id") int id);

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("ticket")
    Call<ResponseBody> AtenderTicket(@Body Ticket oTicket);

    @GET("ticketdetalle/{id}")
    Call<ArrayList<TicketDetalle>> ListarDetalleTicket(@Path("id") int id);

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("ticketdetalle")
    Call<ResponseBody> AgregarRepuesto(@Body TicketDetalle ticketDetalle);

    @GET("repuesto/{id}")
    Call<ArrayList<Repuesto>> BuscarRepuesto(@Path("id") String id);

    //endregion

    //region Métodos de Estado de Tickets
    @GET("estadoticket")
    Call<ArrayList<EstadoTicket>> ListarEstadoTicket();
    //endregion
}
