package com.oalvarez.appticonsulting1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oalvarez.appticonsulting1.R;
import com.oalvarez.appticonsulting1.entidades.Usuario;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oalvarez on 11/02/2017.
 */

public class ListaUsuarioAdapter extends RecyclerView.Adapter<ListaUsuarioAdapter.ListaUsuarioHolder> {

    public ArrayList<Usuario> listaUsuario;


    public ListaUsuarioAdapter(ArrayList<Usuario> lista) {
        this.listaUsuario = lista;
    }

    @Override
    public ListaUsuarioHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tecnico, parent, false);
        return new ListaUsuarioHolder(view);
    }

    @Override
    public void onBindViewHolder(ListaUsuarioHolder holder, int position) {
        Usuario usuario = listaUsuario.get(position);
        holder.tvIdUsuario.setText(usuario.get_usuario());
        holder.tvNombreUsuario.setText(usuario.get_nombre());
    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }

    public class ListaUsuarioHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvIdUsuario)
        TextView tvIdUsuario;
        @BindView(R.id.tvNombreUsuario)
        TextView tvNombreUsuario;

        public ListaUsuarioHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
