package net.copaba.configurandomipropioendpointenmiservidor.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import net.copaba.configurandomipropioendpointenmiservidor.db.ConstructorPets;
import net.copaba.configurandomipropioendpointenmiservidor.pojo.PetPerfil;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.EndpointsApi;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.adapter.ResApiAdapter;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.model.PetResponse;
import net.copaba.configurandomipropioendpointenmiservidor.view.fragment.IRecyclerViewFragmentViewPerfil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Polo on 01/09/16.
 */
public class RecyclerViewFragmentPresenterPerfil implements IRecyclerViewFragmentPresenterPerfil {

    private IRecyclerViewFragmentViewPerfil iRecyclerViewFragmentViewPerfil;
    private Context context;
    private ArrayList<PetPerfil> pets;
    private ConstructorPets constructorPets;

    private String userID;

    public String getName() {
        return name;
    }

    private String name;

    private String urlImg;

    public RecyclerViewFragmentPresenterPerfil(IRecyclerViewFragmentViewPerfil iRecyclerViewFragmentViewPerfil, Context context) {

        this.iRecyclerViewFragmentViewPerfil = iRecyclerViewFragmentViewPerfil;
        this.context = context;
        //mando obtener las mascotas almacenadas localmente
        getPetsBaseDatos();
        //mando obtener los medios recientes al webservice
        obtenerMediosRecientes();

    }


    @Override
    public void obtenerMediosRecientes() {
        ResApiAdapter resApiAdapter = new ResApiAdapter();
        Gson gsonMediaRecent = resApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointApi =resApiAdapter.establecerConexionResApiInstagram(gsonMediaRecent);
        Call<PetResponse> petResponseCall = endpointApi.getRecentMedia(userID);
        petResponseCall.enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {
                PetResponse petResponse = response.body();
                //almaceno en variables valores devueltos del webservice
                pets = petResponse.getPets();
                name = pets.get(0).getNombre();
                urlImg = pets.get(0).getUrlFotoPerfil();
                System.out.println("nombre "+name);
                showPetsRV();
            }

            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
                Toast.makeText(context, "Algo pasó en la conexión! Intenta nuevamente", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXION",t.toString());
            }
        });
    }

    @Override
    public void showPetsRV() {
        iRecyclerViewFragmentViewPerfil.inicializarAdaptadorRV(iRecyclerViewFragmentViewPerfil.crearAdaptador(pets));
        iRecyclerViewFragmentViewPerfil.generarGridLayout();
        //almaceno la información que va a ser regresada al presentador
        iRecyclerViewFragmentViewPerfil.setFullName(name);
        iRecyclerViewFragmentViewPerfil.setUserID(userID);
        iRecyclerViewFragmentViewPerfil.setUrlImg(urlImg);
    }

    @Override
    public void getPetsBaseDatos() {
        constructorPets = new ConstructorPets(context);
        //Consulto la configuración de usuario
        //almacenada en base de datos local
        userID = constructorPets.getUserID();
    }


}
