package net.copaba.configurandomipropioendpointenmiservidor.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.copaba.configurandomipropioendpointenmiservidor.R;
import net.copaba.configurandomipropioendpointenmiservidor.pojo.PetPerfil;

import java.util.ArrayList;

/**
 * Created by Polo on 24/08/16.
 */
public class PerfilAdaptador extends RecyclerView.Adapter<PerfilAdaptador.PerfilViewHolder> {

    ArrayList<PetPerfil> pets;
    Activity activity;

    public PerfilAdaptador(ArrayList<PetPerfil> pets, Activity activity) {
        this.pets = pets;
        this.activity = activity;
    }

    @Override
    public PerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil,parent,false);
        return new PerfilViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PerfilViewHolder perfilViewHolder, int position) {
        final PetPerfil pet = pets.get(position);

        Picasso.with(activity)
                .load(pet.getUrlFoto())
                .placeholder(R.drawable.dog_1).
                into(perfilViewHolder.imgFoto);
        perfilViewHolder.tvRaite.setText(String.valueOf(pet.getLikes()));

    }

    @Override
    public int getItemCount() {
        if(pets == null)
            return 0;
        return pets.size();
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvRaite;

        public PerfilViewHolder(View itemView) {
            super(itemView);

            imgFoto = (ImageView) itemView.findViewById(R.id.imgFotoCv);
            tvRaite = (TextView) itemView.findViewById(R.id.tvRaiteCv);
        }
    }
}
