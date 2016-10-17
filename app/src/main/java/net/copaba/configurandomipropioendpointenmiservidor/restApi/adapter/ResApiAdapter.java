package net.copaba.configurandomipropioendpointenmiservidor.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.copaba.configurandomipropioendpointenmiservidor.restApi.ConstantesRestApi;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.Endpoints;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.EndpointsApi;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.deserializador.PerfilDeserealizador;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.deserializador.PetDeseralizador;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.deserializador.UserDeserealizador;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.model.PetResponse;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.model.UserResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Polo on 12/09/16.
 */
public class ResApiAdapter {
    public EndpointsApi establecerConexionResApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndpointsApi.class);
    }
    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PetResponse.class, new PetDeseralizador());
        return gsonBuilder.create();
    }
    public Gson construyeGsonDeserializadorUser(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UserResponse.class, new UserDeserealizador());
        return gsonBuilder.create();
    }
    public Gson construyeGsonDeserializadorPerfil(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UserResponse.class, new PerfilDeserealizador());
        return gsonBuilder.create();
    }
    public Endpoints establecerConexionRestAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL1)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;
        return retrofit.create(Endpoints.class);
    }
}
