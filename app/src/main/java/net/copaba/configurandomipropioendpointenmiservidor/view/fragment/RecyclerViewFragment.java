package net.copaba.configurandomipropioendpointenmiservidor.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.copaba.configurandomipropioendpointenmiservidor.Activity2;
import net.copaba.configurandomipropioendpointenmiservidor.R;
import net.copaba.configurandomipropioendpointenmiservidor.adapter.PetAdaptador;
import net.copaba.configurandomipropioendpointenmiservidor.pojo.Pet;
import net.copaba.configurandomipropioendpointenmiservidor.presentador.IRecyclerViewFragmentPresenter;
import net.copaba.configurandomipropioendpointenmiservidor.presentador.RecyclerViewFragmentPresenter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Polo on 23/08/16.
 */
public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView{

//    private ArrayList<Pet> pets;
    private RecyclerView rvPets;
    private IRecyclerViewFragmentPresenter presenter;
    private PetAdaptador petAdaptadorG;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_recyclerview,container,false);
        rvPets = (RecyclerView) view.findViewById(R.id.rvPetagram);

        presenter = new RecyclerViewFragmentPresenter(this, getContext());

        return view;
    }


    public void irActivity2(View view) {
    //Mandamos al segundo activity solo las utimas 5 mascotas que se marcarn como favoritas
        ArrayList<Pet> pets ;

        pets = petAdaptadorG.getLast();


        Bundle extra = new Bundle();
        extra.putSerializable("pets",(Serializable) pets);
        Intent intent =  new Intent(view.getContext(),Activity2.class);
        intent.putExtra("extra",extra);
        startActivity(intent);
    }

    @Override
    public void generarLineaLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPets.setLayoutManager(llm);
    }

    @Override
    public PetAdaptador crearAdaptador(ArrayList<Pet> pets) {
        PetAdaptador adaptador =  new PetAdaptador(pets, getActivity());
        petAdaptadorG  = adaptador;
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(PetAdaptador adaptador) {

        rvPets.setAdapter(adaptador);
    }

}
