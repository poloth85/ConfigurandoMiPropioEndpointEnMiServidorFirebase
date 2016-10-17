package net.copaba.configurandomipropioendpointenmiservidor.view.fragment;


import net.copaba.configurandomipropioendpointenmiservidor.adapter.PetAdaptador;
import net.copaba.configurandomipropioendpointenmiservidor.pojo.Pet;

import java.util.ArrayList;

/**
 * Created by Polo on 01/09/16.
 */
public interface IRecyclerViewFragmentView {

    public void generarLineaLayoutVertical();

    public PetAdaptador crearAdaptador(ArrayList<Pet> pets);

    public void inicializarAdaptadorRV(PetAdaptador adaptador);
}
