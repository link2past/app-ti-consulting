package com.oalvarez.appticonsulting;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);

        try{
            //setSupportActionBar(toolbar);

            Bundle bundle = getIntent().getExtras();
            if (bundle.getString("nombreusuario") != null && bundle.getString("tipousuario") != null) {

                NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);

                View header = navigationView.getHeaderView(0);
                TextView tvUsuarioNav = (TextView)header.findViewById(R.id.tvUsuarioNav);
                tvUsuarioNav.setText(bundle.getString("nombreusuario"));

                TextView tvTipoUsuario = (TextView)header.findViewById(R.id.tvTipoUsuario);
                tvTipoUsuario.setText(bundle.getString("tipousuario"));


            }
        }
        catch (Exception ex){
            Log.d("Error", ex.getMessage());
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
