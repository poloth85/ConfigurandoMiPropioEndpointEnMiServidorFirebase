package net.copaba.configurandomipropioendpointenmiservidor.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import net.copaba.configurandomipropioendpointenmiservidor.db.ConstructorPets;
import net.copaba.configurandomipropioendpointenmiservidor.pojo.Pet;
import net.copaba.configurandomipropioendpointenmiservidor.pojo.PetPerfil;
import net.copaba.configurandomipropioendpointenmiservidor.pojo.User;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.EndpointsApi;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.adapter.ResApiAdapter;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.model.PetResponse;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.model.UserResponse;
import net.copaba.configurandomipropioendpointenmiservidor.view.fragment.IRecyclerViewFragmentView;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Polo on 01/09/16.
 */
public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorPets constructorPets;
    ArrayList<Pet> pets = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {

        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
//        getPetsBaseDatos();
        obtenUsuariosSandbox();


    }

    @Override
    public void getPetsBaseDatos() {

        constructorPets = new ConstructorPets(context);
//        pets = constructorPets.getData();
        constructorPets.setData();

        showPetsRV();
    }

    @Override
    public void obtenUsuariosSandbox() {
        ResApiAdapter resApiAdapter = new ResApiAdapter();
        Gson gsonMediaRecent = resApiAdapter.construyeGsonDeserializadorUser();
        EndpointsApi endpointApi =resApiAdapter.establecerConexionResApiInstagram(gsonMediaRecent);
        Call<UserResponse> userResponseCall = endpointApi.getUserSandboxID();
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                ArrayList<User> usersResponse = userResponse.getUsers();
                Iterator<User> iuser = usersResponse.iterator();
                while (iuser.hasNext()){
                    User user = iuser.next();
                    obtenerMediosRecientes(user.getId());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(context, "Algo pasó en la conexión! Intenta nuevamente", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXION",t.toString());
            }
        });
    }

    @Override
    public void showPetsRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(pets));
        iRecyclerViewFragmentView.generarLineaLayoutVertical();
    }

    @Override
    public void obtenerMediosRecientes() {

    }

    @Override
    public void obtenerMediosRecientes(String userID) {
        ResApiAdapter resApiAdapter = new ResApiAdapter();
        Gson gsonMediaRecent = resApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointApi =resApiAdapter.establecerConexionResApiInstagram(gsonMediaRecent);
        Call<PetResponse> petResponseCall = endpointApi.getRecentMedia(userID);
        petResponseCall.enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {
                PetResponse petResponse = response.body();
                //almaceno en variables valores devueltos del webservice
                if(petResponse!=null){
                Iterator<PetPerfil> petsResponse = petResponse.getPets().iterator();

                while (petsResponse.hasNext()){
                    PetPerfil petResp = petsResponse.next();
                    Pet petActual = new Pet(petResp.getId(),petResp.getNombre(),petResp.getUrlFoto(),petResp.getLikes());
                    pets.add(petActual);

                }}

                showPetsRV();
            }

            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
                Toast.makeText(context, "Algo pasó en la conexión! Intenta nuevamente", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXION",t.toString());
            }
        });
    }
}
