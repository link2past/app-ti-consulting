package com.oalvarez.appticonsulting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    private Boolean bLoginCorrecto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //throw new RuntimeException("Error de Prueba");
    }

    @OnClick(R.id.btnLogin)
    public void onClick() {


        String sUsuario = etUsuario.getText().toString().trim();
        String sContrasena = new Encrypt().encrypt(etContrasena.getText().toString(), "!T1C0NSULT1NG2016!").trim();

        Usuario oUsuario = new Usuario();
        oUsuario.set_usuario(sUsuario);
        oUsuario.set_contraseña(sContrasena);

        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null :
                getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS );

        pbLogin.setIndeterminate(true);
        pbLogin.setVisibility(View.VISIBLE);

        Gson gsonParse = new GsonBuilder().disableHtmlEscaping().create();
        String sJson = gsonParse.toJson(oUsuario);
        //Toast.makeText(TipoUsuarioActivity.this, sJson, Toast.LENGTH_SHORT).show();

        TicketsApiWs ws = HelperWs.getConfiguration().create(TicketsApiWs.class);

        Call<Token> resultadoLogin = ws.Login(oUsuario);
        resultadoLogin.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Token oToken = response.body();

                try {
                    if (oToken != null) {
                        //Toast.makeText(MainActivity.this, oToken.get_token(), Toast.LENGTH_SHORT).show();
                        pbLogin.setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                        intent.putExtra("idusuario", oToken.get_idUsuario());
                        intent.putExtra("nombreusuario", oToken.get_usuario().get_nombre());
                        intent.putExtra("tipousuario", oToken.get_usuario().get_tipoUsuario().get_descripcion());
                        startActivity(intent);
                        bLoginCorrecto = true;

                    }
                    else{
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
}
