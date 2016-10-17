package net.copaba.configurandomipropioendpointenmiservidor.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.copaba.configurandomipropioendpointenmiservidor.R;
import net.copaba.configurandomipropioendpointenmiservidor.adapter.PerfilAdaptador;
import net.copaba.configurandomipropioendpointenmiservidor.pojo.PetPerfil;
import net.copaba.configurandomipropioendpointenmiservidor.presentador.IRecyclerViewFragmentPresenterPerfil;
import net.copaba.configurandomipropioendpointenmiservidor.presentador.RecyclerViewFragmentPresenterPerfil;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilViewFragment extends Fragment implements IRecyclerViewFragmentViewPerfil {

    private ArrayList<PetPerfil> pets;
    private RecyclerView rvPerfil;
    private TextView tvNombreCv;
    private ImageView imgFotoPerfil;
    private IRecyclerViewFragmentPresenterPerfil presenter;


    public PerfilViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this view.fragment
        //return inflater.inflate(R.layout.fragment_perfil, container, false);
        View view = inflater.inflate(R.layout.fragment_perfil,container,false);
        rvPerfil = (RecyclerView) view.findViewById(R.id.rvPerfil);
        tvNombreCv = (TextView) view.findViewById(R.id.tvNombreCv);
        imgFotoPerfil = (ImageView) view.findViewById(R.id.imgFotoPerfil);
        RecyclerViewFragmentPresenterPerfil rvFPP = new RecyclerViewFragmentPresenterPerfil(this, getContext());
        presenter = rvFPP;

        return view;
    }
//    public PerfilAdaptador adaptador;
//    public void inicializaAdaptador(View view){
//        adaptador = new PerfilAdaptador(pets,getActivity());
//        RecyclerView rvPerfil = (RecyclerView) view.findViewById(R.id.rvPerfil);
//        rvPerfil.setAdapter(adaptador);
//    }
//    public void inicializaListaFotosPets(){
//        pets = new ArrayList<Pet>();
//        nombre.setText("Caris");
//        pets.add(new Pet(1, "Caris", R.drawable.dog_5,6));
//        pets.add(new Pet(2, "Caris",R.drawable.dog_5_1,10));
//        pets.add(new Pet(3, "Caris", R.drawable.dog_5_2,18));
//        pets.add(new Pet(4, "Caris", R.drawable.dog_5_3,25));

//    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
        rvPerfil.setLayoutManager(glm);
    }
    @Override
    public void setFullName(String name){
        //fijo el nombre del usuario configurado en el perfil
        tvNombreCv.setText(name);
    }

    @Override
    public void setUserID(String userID) {
    }

    @Override
    public void setUrlImg(String urlImg) {
        //fijo la imagen a mostrar el el perfil configurado
        Picasso.with(getActivity())
                .load(urlImg)
                .placeholder(R.drawable.dog_2).
                into(imgFotoPerfil);
    }

    @Override
    public PerfilAdaptador crearAdaptador(ArrayList<PetPerfil> pets) {
        PerfilAdaptador adaptador =  new PerfilAdaptador(pets, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(PerfilAdaptador adaptador) {
        rvPerfil.setAdapter(adaptador);
    }

}
