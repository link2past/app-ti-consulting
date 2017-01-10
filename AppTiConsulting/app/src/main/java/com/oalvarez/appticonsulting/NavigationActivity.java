package com.oalvarez.appticonsulting;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.oalvarez.appticonsulting.entidades.SessionManager;
import com.oalvarez.appticonsulting.fragments.LiquidacionFragment;
import com.oalvarez.appticonsulting.fragments.TicketDetalleFragment;
import com.oalvarez.appticonsulting.fragments.TicketFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String sIdUsuario;
    int nIdTipoUsuario;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);

        try{
            //setSupportActionBar(toolbar);
            sessionManager = new SessionManager(NavigationActivity.this);

            Bundle bundle = getIntent().getExtras();
            if (bundle.getString("idusuario") != null && bundle.getString("nombreusuario") != null
                    && bundle.getString("tipousuario") != null) {

                sIdUsuario = bundle.getString("idusuario");
                nIdTipoUsuario = bundle.getInt("idtipousuario");

                DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                        this, drawerLayout, toolbar, R.string.cliente, R.string.cliente
                );
                drawerLayout.setDrawerListener(toggle);
                toggle.syncState();

                NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);

                View header = navigationView.getHeaderView(0);
                TextView tvUsuarioNav = (TextView)header.findViewById(R.id.tvUsuarioNav);
                tvUsuarioNav.setText(bundle.getString("nombreusuario"));

                TextView tvTipoUsuario = (TextView)header.findViewById(R.id.tvTipoUsuario);
                tvTipoUsuario.setText(bundle.getString("tipousuario"));

                displaySelectedScreen(R.id.navTickets);
            }


        }
        catch (Exception ex){
            Log.d("Error", ex.getMessage());
        }

    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.navTickets:
                fragment = new TicketFragment();
                Bundle bundleFragment = new Bundle();
                bundleFragment.putString("idusuario", sIdUsuario);
                bundleFragment.putInt("idtipousuario", nIdTipoUsuario);
                fragment.setArguments(bundleFragment);
                toolbar.setTitle(R.string.ticketsasignados);
                break;
            case R.id.navPerfil:
                //fragment = new Menu2();
                fragment = new TicketDetalleFragment();
                toolbar.setTitle("Detalle de Tickets");
                break;
            case R.id.navLiquidacion:
                fragment = new LiquidacionFragment();
                toolbar.setTitle(R.string.liquidacion);
                break;
            case R.id.navCerrarSesion:
                sessionManager.cerrarSesion();
                break;

        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment).addToBackStack("fragment");
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int fragments = getSupportFragmentManager().getBackStackEntryCount();
            if (fragments == 1) {
                finish();
            } else {
                if (getFragmentManager().getBackStackEntryCount() > 1) {
                    getFragmentManager().popBackStack();
                } else {
                    super.onBackPressed();
                }
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }


}
