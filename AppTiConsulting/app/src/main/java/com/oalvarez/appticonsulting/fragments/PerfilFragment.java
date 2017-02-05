package com.oalvarez.appticonsulting.fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oalvarez.appticonsulting.R;
import com.oalvarez.appticonsulting.entidades.Usuario;
import com.oalvarez.appticonsulting.servicios.HelperWs;
import com.oalvarez.appticonsulting.servicios.TicketsApiWs;
import com.oalvarez.appticonsulting.util.Encrypt;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {


    @BindView(R.id.etNombreUsuario)
    EditText etNombreUsuario;
    @BindView(R.id.tilNombreUsuario)
    TextInputLayout tilNombreUsuario;
    @BindView(R.id.etPerfilUsuario)
    EditText etPerfilUsuario;
    @BindView(R.id.tilPerfilUsuario)
    TextInputLayout tilPerfilUsuario;
    @BindView(R.id.etCorreoUsuario)
    EditText etCorreoUsuario;
    @BindView(R.id.tilCorreoUsuario)
    TextInputLayout tilCorreoUsuario;
    @BindView(R.id.etNuevaContrasena)
    EditText etNuevaContrasena;
    @BindView(R.id.tilNuevaContrasena)
    TextInputLayout tilNuevaContrasena;
    @BindView(R.id.etRepetirContrasena)
    EditText etRepetirContrasena;
    @BindView(R.id.tilRepetirContrasena)
    TextInputLayout tilRepetirContrasena;
    @BindView(R.id.btnGuardarCambiosPerfil)
    Button btnGuardarCambiosPerfil;

    String sIdUsuario;
    @BindView(R.id.llPerfil)
    LinearLayout llPerfil;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = this.getArguments();

        if (bundle != null) {

            sIdUsuario = bundle.getString("idusuario");

            TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
            Call<Usuario> respuesta = ticketsApiWs.ConsultarUsuario(sIdUsuario);
            respuesta.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    Usuario oUsuario = response.body();

                    if (oUsuario != null) {

                        etNombreUsuario.setText(oUsuario.get_nombre());
                        etPerfilUsuario.setText(oUsuario.get_tipoUsuario().get_descripcion());
                        etCorreoUsuario.setText(oUsuario.get_email());

                        desactivarCampos();

                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.d("ERROR: ", t.getMessage());
                }
            });


        }

        return view;
    }

    private void desactivarCampos() {
        etNombreUsuario.setFocusable(false);
        etNombreUsuario.setClickable(false);

        etPerfilUsuario.setFocusable(false);
        etPerfilUsuario.setClickable(false);

    }

    @OnClick(R.id.btnGuardarCambiosPerfil)
    public void onClick() {

        if (!etNuevaContrasena.getText().toString().equals(etRepetirContrasena.getText().toString())) {
            tilRepetirContrasena.setError("Las contraseñas deben ser iguales");
            etRepetirContrasena.requestFocus();
            return;
        } else {
            etRepetirContrasena.setError(null);
        }

        String sContrasena = new Encrypt().encrypt(etNuevaContrasena.getText().toString(), "!T1C0NSULT1NG2016!").trim();

        Usuario usuario = new Usuario();
        usuario.set_usuario(sIdUsuario);
        usuario.set_email(etCorreoUsuario.getText().toString());
        usuario.set_contraseña(sContrasena);

//        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//        String sJson = gson.toJson(usuario);
//        etNombreUsuario.setText(sJson);

        TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
        Call<ResponseBody> respuesta = ticketsApiWs.ActualizarUsuario("upd", "1", usuario);

        respuesta.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    Snackbar snackbar = Snackbar.make(llPerfil, "Se actualizaron los datos del usuario", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
