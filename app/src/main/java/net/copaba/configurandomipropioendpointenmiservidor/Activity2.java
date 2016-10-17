package net.copaba.configurandomipropioendpointenmiservidor;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import net.copaba.configurandomipropioendpointenmiservidor.adapter.PetAdaptador;
import net.copaba.configurandomipropioendpointenmiservidor.pojo.Pet;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    private RecyclerView listaPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.MiActionBar);
        setSupportActionBar(miActionBar);
        findViewById(R.id.btnFav).setVisibility(View.INVISIBLE);


        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle parametros = getIntent().getBundleExtra("extra");

        ArrayList<Pet> pets = (ArrayList<Pet>) parametros.getSerializable("pets");

        listaPets = (RecyclerView) findViewById(R.id.rvPetagramFav);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);


        inicializaAdaptador(pets);
        listaPets.setLayoutManager(llm);

    }

    public PetAdaptador adaptador;

    public void inicializaAdaptador(ArrayList<Pet> pets){
        adaptador = new PetAdaptador(pets,this);
        RecyclerView rvPetagramFav = (RecyclerView) findViewById(R.id.rvPetagramFav);
        rvPetagramFav.setAdapter(adaptador);
    }


}
