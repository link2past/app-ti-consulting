package com.oalvarez.appticonsulting1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oalvarez.appticonsulting1.entidades.Preferencias;
import com.oalvarez.appticonsulting1.entidades.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.etUrlServer)
    EditText etUrlServer;
    @BindView(R.id.btnGuardarConfig)
    Button btnGuardarConfig;

    private SessionManager sessionManager;
    Preferencias preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(SettingsActivity.this);

        preferencias = sessionManager.obtenerPreferencias();
        etUrlServer.setText(preferencias.getUrlServer());
    }

    @OnClick(R.id.btnGuardarConfig)
    public void onClick() {
        preferencias = new Preferencias();
        preferencias.setUrlServer(etUrlServer.getText().toString());

        sessionManager.guardarPreferencias(preferencias);
        Toast.makeText(SettingsActivity.this, "Se guardó la configuración.",
                Toast.LENGTH_SHORT).show();

        if (!sessionManager.estaLogeado()){
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else{
            finish();
        }

    }
}
