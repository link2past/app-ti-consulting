package com.oalvarez.appticonsulting1.fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.oalvarez.appticonsulting1.R;
import com.oalvarez.appticonsulting1.adapter.TicketArchivoAdapter;
import com.oalvarez.appticonsulting1.entidades.TicketArchivo;
import com.oalvarez.appticonsulting1.events.ClickListener;
import com.oalvarez.appticonsulting1.events.RecyclerTouchListener;
import com.oalvarez.appticonsulting1.servicios.HelperWs;
import com.oalvarez.appticonsulting1.servicios.TicketsApiWs;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketArchivoFragment extends Fragment {

    int nroTicket;
    ArrayList<TicketArchivo> listaArchivos;
    public TicketArchivoAdapter adapter;

    @BindView(R.id.rvArchivosAsociados)
    RecyclerView rvArchivosAsociados;
    @BindView(R.id.sdvArchivoAsociado)
    SimpleDraweeView sdvArchivoAsociado;
    @BindView(R.id.btnAgregarFoto)
    Button btnAgregarFoto;
    @BindView(R.id.nsvScroll)
    NestedScrollView nsvScroll;
//    @BindView(R.id.imgFoto)
//    ImageView imgFoto;

    public TicketArchivoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket_archivo, container, false);
        ButterKnife.bind(this, view);

        final Bundle bundle = this.getArguments();

        if (bundle != null) {
            nroTicket = Integer.parseInt(bundle.getString("nroticket"));

            Toast.makeText(getActivity(), String.valueOf(nroTicket), Toast.LENGTH_SHORT).show();


            TicketsApiWs ticketsApiWs = HelperWs.getConfiguration(getActivity()).create(TicketsApiWs.class);
            Call<ArrayList<TicketArchivo>> respuesta = ticketsApiWs.ListarArchivos(nroTicket);

            respuesta.enqueue(new Callback<ArrayList<TicketArchivo>>() {
                @Override
                public void onResponse(Call<ArrayList<TicketArchivo>> call, Response<ArrayList<TicketArchivo>> response) {
                    listaArchivos = response.body();

                    if (listaArchivos != null) {
                        adapter = new TicketArchivoAdapter(listaArchivos);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        rvArchivosAsociados.setLayoutManager(layoutManager);
                        rvArchivosAsociados.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<TicketArchivo>> call, Throwable t) {

                }
            });
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        rvArchivosAsociados.addOnItemTouchListener(new RecyclerTouchListener(
                getActivity(),
                rvArchivosAsociados,
                new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        TicketArchivo item = listaArchivos.get(position);

                        int width = 200, height = 200;
                        Uri uri = Uri.parse(getString(R.string.default_ruta_archivos) + item.get_nombreArchivo());

                        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                                .setResizeOptions(new ResizeOptions(width, height))
                                .setRotationOptions(RotationOptions.autoRotate())
                                .build();
                        DraweeController controller = Fresco.newDraweeControllerBuilder()
                                .setOldController(sdvArchivoAsociado.getController())
                                .setImageRequest(request)
                                .build();
                        sdvArchivoAsociado.setController(controller);

                        //sdvArchivoAsociado.setImageURI("http://192.168.10.196/webapitickets/files/" + item.get_nombreArchivo());
//                        Uri uri = Uri.parse("http://192.168.10.196/webapitickets/files/" + item.get_nombreArchivo());
//                        imgFoto.setImageURI(uri);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }
        ));
    }

    @OnClick(R.id.btnAgregarFoto)
    public void onClick() {
        Fragment fragment;
        fragment = new SubirArchivoFragment();
        Bundle bundleRepuesto = new Bundle();
        bundleRepuesto.putInt("nroticket", nroTicket);
        fragment.setArguments(bundleRepuesto);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment).addToBackStack("fragment");
        ft.commit();
    }
}
