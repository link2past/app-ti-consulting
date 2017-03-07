package com.oalvarez.appticonsulting1.servicios;

import com.oalvarez.appticonsulting1.entidades.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by oalvarez on 14/12/2016.
 */

public interface TicketsApiWs {

    //region Métodos de Usuario

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("usuario")
    Call<Token> Login(@Body Usuario usuario);

    @GET("usuario/{id}")
    Call<Usuario> ConsultarUsuario(@Path("id") String sIdUsuario);

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("usuario/{accion}/{id}")
    Call<ResponseBody> ActualizarUsuario(@Path("accion") String sAccion,  @Path("id") String sId, @Body Usuario usuario);

    @GET("usuario/{accion}/{id}")
    Call<ArrayList<Usuario>> ListarUsuario(@Path("accion") int nIdTipoUsuario, @Path("id") String sNombreUsuario);

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

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("ticket/{id}")
    Call<RespuestaCodigo> RegistrarTicket(@Path("id") String id, @Body Ticket ticket);

    @Multipart
    @POST("TicketArchivo/{id}")
    Call<ResponseBody> CargarArchivo(@Part MultipartBody.Part file, @Part("description") RequestBody description, @Path("id") int id);

    @GET("TicketArchivo/{id}")
    Call<ArrayList<TicketArchivo>> ListarArchivos(@Path("id") int id);
    //endregion

    //region Métodos de Listas
    @GET("estadoticket")
    Call<ArrayList<EstadoTicket>> ListarEstadoTicket();

    @GET("categoriaproblema")
    Call<ArrayList<CategoriaProblema>> ListarCategoriaProblema();
    //endregion

    //region Métodos de Clientes
    @GET("cliente")
    Call<ArrayList<Cliente>> ListarClientes();

    @GET("unidadnegocio/{id}")
    Call<ArrayList<UnidadNegocio>> ListarUnidadNegocioCliente(@Path("id") String sIdCliente);

    @GET("sede/{accion}/{id}")
    Call<ArrayList<SedeCliente>> ListarSedePorUn(@Path("accion") String sIdCliente, @Path("id") int nIdUnidadNegocio);

    @GET("usuariosede/{id}")
    Call<ArrayList<UsuarioSede>> ListarUsuarioSede(@Path("id") int nIdSede);
    //endregion

    //region Liquidacion
    @GET("liquidacion/{accion}/{id}")
    Call<ArrayList<Liquidacion>> ListarLiquidacion(@Path("accion") String sAccion, @Path("id") String sIdUsuario);

    @GET("liquidacion/{id}")
    Call<Liquidacion> ConsultarLiquidacion(@Path("id") int id);

    @GET("liquidaciondet/{id}")
    Call<ArrayList<LiquidacionDetalle>> ListarLiquidacionDetalle(@Path("id") int id);

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("liquidaciondet/{id}")
    Call<ResponseBody> AgregarDetalleLiquidacion(@Path("id") String id, @Body LiquidacionDetalle liquidacionDetalle);
    //endregion
}
