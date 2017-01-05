package com.oalvarez.appticonsulting.entidades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.oalvarez.appticonsulting.MainActivity;

/**
 * Created by oalvarez on 05/01/2017.
 */

public class SessionManager {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    int PRIVATE_MODE=0;

    private static final String PREF_NAME = "GesTIPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_USER = "Usuario";
    public static final String KEY_NAME = "Nombre";
    public static final String KEY_TYPE_USER_CODE = "IdTipoUsuario";
    public static final String KEY_TYPE_USER = "TipoUsuario";
    public static final String KEY_TOKEN = "Token";

    public SessionManager(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void crearSesion(String sUsuario, String sNombreusuario, int nIdTipoUsuario, String sTipoUsuario, String sToken){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USER, sUsuario);
        editor.putString(KEY_NAME, sNombreusuario);
        editor.putInt(KEY_TYPE_USER_CODE, nIdTipoUsuario);
        editor.putString(KEY_TYPE_USER, sTipoUsuario);
        editor.putString(KEY_TOKEN, sToken);

        editor.commit();
    }

    public void cerrarSesion(){
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public Token obtenerDatosSesion(){
        Token token = new Token();
        Usuario usuario = new Usuario();
        TipoUsuario tipoUsuario = new TipoUsuario();

        usuario.set_nombre(preferences.getString(KEY_NAME, ""));
        usuario.set_idTipoUsuario(preferences.getInt(KEY_TYPE_USER_CODE, 0));
        tipoUsuario.set_idTipoUsuario(preferences.getInt(KEY_TYPE_USER_CODE, 0));
        tipoUsuario.set_descripcion(preferences.getString(KEY_TYPE_USER, ""));
        usuario.set_tipoUsuario(tipoUsuario);

        token.set_idUsuario(preferences.getString(KEY_USER, ""));
        token.set_usuario(usuario);

        return token;
    }

    public void validarLogin(){
        if (!this.estaLogeado()){
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public Boolean estaLogeado(){
        return preferences.getBoolean(IS_LOGIN, false);
    }

}
