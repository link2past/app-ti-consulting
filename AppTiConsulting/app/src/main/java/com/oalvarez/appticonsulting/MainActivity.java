package com.oalvarez.appticonsulting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oalvarez.appticonsulting.entidades.*;
import com.oalvarez.appticonsulting.servicios.*;
import com.oalvarez.appticonsulting.util.*;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @BindView(R.id.etUsuario)
    EditText etUsuario;
    @BindView(R.id.etContrasena)
    EditText etContrasena;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    private Boolean bLoginCorrecto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    public void onClick() {


        String sUsuario = etUsuario.getText().toString().trim();
        String sContrasena = new Encrypt().encrypt(etContrasena.getText().toString(),"!T1C0NSULT1NG2016!").trim();

        Usuario oUsuario = new Usuario();
        oUsuario.set_usuario(sUsuario);
        oUsuario.set_contraseña(sContrasena);

        Gson gsonParse = new GsonBuilder().disableHtmlEscaping().create();
        String sJson = gsonParse.toJson(oUsuario);
        //Toast.makeText(TipoUsuarioActivity.this, sJson, Toast.LENGTH_SHORT).show();

        TicketsApiWs ws = HelperWs.getConfiguration().create(TicketsApiWs.class);

        Call<Token> resultadoLogin = ws.Login(oUsuario);
        resultadoLogin.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Token oToken = response.body();

                try{
                    if (oToken != null) {
                        //Toast.makeText(MainActivity.this, oToken.get_token(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                        intent.putExtra("nombreusuario",oToken.get_usuario().get_nombre() );
                        intent.putExtra("tipousuario", oToken.get_usuario().get_tipoUsuario().get_descripcion());
                        startActivity(intent);
                        bLoginCorrecto = true;

                    }
                }
                catch (Exception ex){

                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });

        /*if (bLoginCorrecto){
            Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
            //intent.putExtra("nombreusuario",oUsuario.getNombre() );
            startActivity(intent);
        }*/
    }
}