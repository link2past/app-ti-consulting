package com.oalvarez.appticonsulting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oalvarez.appticonsulting.entidades.SessionManager;
import com.oalvarez.appticonsulting.entidades.Token;
import com.oalvarez.appticonsulting.entidades.Usuario;
import com.oalvarez.appticonsulting.servicios.HelperWs;
import com.oalvarez.appticonsulting.servicios.TicketsApiWs;
import com.oalvarez.appticonsulting.util.Encrypt;

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
    @BindView(R.id.pbLogin)
    ProgressBar pbLogin;
    @BindView(R.id.tilUsuario)
    TextInputLayout tilUsuario;
    @BindView(R.id.tilContrasena)
    TextInputLayout tilContrasena;
    @BindView(R.id.btnConfiguracion)
    Button btnConfiguracion;

    private Boolean bLoginCorrecto = false;
    private SessionManager sessionManager;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(MainActivity.this);
        Toast.makeText(MainActivity.this, "User Login Status: " + sessionManager.estaLogeado(), Toast.LENGTH_LONG).show();

        if (sessionManager.estaLogeado()) {

            Token token = sessionManager.obtenerDatosSesion();
            Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("idusuario", token.get_idUsuario());
            intent.putExtra("nombreusuario", token.get_usuario().get_nombre());
            intent.putExtra("idtipousuario", token.get_usuario().get_idTipoUsuario());
            intent.putExtra("tipousuario", token.get_usuario().get_tipoUsuario().get_descripcion());
            startActivity(intent);
        }

        //throw new RuntimeException("Error de Prueba");
    }

    @OnClick(R.id.btnLogin)
    public void onClick() {

        if (etUsuario.getText().toString().trim().equals("") || etUsuario.getText() == null) {
            tilUsuario.setError(getString(R.string.mensajeLoginNoUsuario));
            etUsuario.requestFocus();
            return;
        } else {
            tilUsuario.setError(null);
        }

        if (etContrasena.getText().toString().trim().equals("") || etContrasena.getText() == null) {
            tilContrasena.setError(getString(R.string.mensajeLoginNoContr));
            etContrasena.requestFocus();
            return;
        } else {
            tilContrasena.setError(null);
        }

        String sUsuario = etUsuario.getText().toString().trim();
        String sContrasena = new Encrypt().encrypt(etContrasena.getText().toString(), "!T1C0NSULT1NG2016!").trim();

        Usuario oUsuario = new Usuario();
        oUsuario.set_usuario(sUsuario);
        oUsuario.set_contraseña(sContrasena);

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null :
                getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        pbLogin.setIndeterminate(true);
        pbLogin.setVisibility(View.VISIBLE);

        Gson gsonParse = new GsonBuilder().disableHtmlEscaping().create();
        String sJson = gsonParse.toJson(oUsuario);
        //Toast.makeText(TipoUsuarioActivity.this, sJson, Toast.LENGTH_SHORT).show();

        TicketsApiWs ws = HelperWs.getConfiguration(MainActivity.this).create(TicketsApiWs.class);

        Call<Token> resultadoLogin = ws.Login(oUsuario);
        resultadoLogin.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Token oToken = response.body();

                try {
                    if (oToken != null) {
                        //Toast.makeText(MainActivity.this, oToken.get_token(), Toast.LENGTH_SHORT).show();
                        pbLogin.setVisibility(View.INVISIBLE);

                        sessionManager.crearSesion(oToken.get_idUsuario(), oToken.get_usuario().get_nombre(),
                                oToken.get_usuario().get_idTipoUsuario(), oToken.get_usuario().get_tipoUsuario().get_descripcion(),
                                oToken.get_token());

                        Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("idusuario", oToken.get_idUsuario());
                        intent.putExtra("nombreusuario", oToken.get_usuario().get_nombre());
                        intent.putExtra("idtipousuario", oToken.get_usuario().get_idTipoUsuario());
                        intent.putExtra("tipousuario", oToken.get_usuario().get_tipoUsuario().get_descripcion());
                        startActivity(intent);
                        bLoginCorrecto = true;

                    } else {
                        pbLogin.setVisibility(View.INVISIBLE);
                        Toast.makeText(MainActivity.this, "Usuario o contraseña incorrecta, intente nuevamente.",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {

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

    @OnClick(R.id.btnConfiguracion)
    public void onClickConfiguracion() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
}
