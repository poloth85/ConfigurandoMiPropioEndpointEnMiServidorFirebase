package net.copaba.configurandomipropioendpointenmiservidor.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.copaba.configurandomipropioendpointenmiservidor.R;
import net.copaba.configurandomipropioendpointenmiservidor.db.ConstructorPets;
import net.copaba.configurandomipropioendpointenmiservidor.pojo.Pet;

import java.util.ArrayList;

/**
 * Created by Polo on 17/08/16.
 */
public class PetAdaptador extends RecyclerView.Adapter<PetAdaptador.PetViewHolder> {

    ArrayList<Pet> pets;
    Activity activity;


    // Array donde almacenamos los perros a los que le demos me  gusta
    public ArrayList<Pet> petsFav = new ArrayList<Pet>();
    int inicio = 0;

    public PetAdaptador(ArrayList<Pet> pets, Activity activity){
        this.pets = pets;
        this.activity = activity;
    }



    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pet, parent, false);
        return new PetViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final PetViewHolder petViewHolder, final int position) {

        final Pet pet = pets.get(position);
//        petViewHolder.imgFoto.setImageResource(pet.getFoto());
        Picasso.with(activity).
                load(pet.getUrlFoto()).
                placeholder(R.drawable.dog_1).into(petViewHolder.imgFoto);
        petViewHolder.tvNombre.setText(pet.getNombre());
        petViewHolder.tvLikes.setText(String.valueOf(pet.getLikes()));



        petViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, pet.getNombre(),Toast.LENGTH_SHORT).show();
            }
        });

        petViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Diste like a " + pet.getNombre(), Toast.LENGTH_SHORT).show();
                ConstructorPets constructorPets = new ConstructorPets(activity);
                pet.setLikes(constructorPets.getPetLikes(pet));
                constructorPets.likePet(pet);
                petViewHolder.tvLikes.setText(String.valueOf(constructorPets.getPetLikes(pet)));

            }
        });
    }

    @Override
    public int getItemCount() {
        if(pets!=null)
        return pets.size();
        return 0;
    }
    //regresa las ultimas 5 mascotas
    public ArrayList<Pet> getLast(){
        ConstructorPets constructorPets = new ConstructorPets(activity);
        return constructorPets.getLastFiveLikePets();
    }

    public static  class PetViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvNombre;
        private TextView tvLikes;
        private ImageButton btnLike;
        private ImageButton btnFav;

        public PetViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvLikes = (TextView) itemView.findViewById(R.id.tvLikes);
            btnLike = (ImageButton) itemView.findViewById(R.id.btnLike);
            btnFav = (ImageButton) itemView.findViewById(R.id.btnFav);

        }
    }
}