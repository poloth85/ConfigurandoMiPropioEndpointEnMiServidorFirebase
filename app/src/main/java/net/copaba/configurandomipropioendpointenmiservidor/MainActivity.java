package net.copaba.configurandomipropioendpointenmiservidor;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import net.copaba.configurandomipropioendpointenmiservidor.adapter.PageAdapter;

import net.copaba.configurandomipropioendpointenmiservidor.db.ConstructorPets;
import net.copaba.configurandomipropioendpointenmiservidor.pojo.User;
import net.copaba.configurandomipropioendpointenmiservidor.presentador.RecyclerViewFragmentPresenterPerfil;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.Endpoints;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.EndpointsApi;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.adapter.ResApiAdapter;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.model.PetResponse;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.model.UserResponse;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.model.UsuarioResponse;
import net.copaba.configurandomipropioendpointenmiservidor.view.fragment.PerfilViewFragment;
import net.copaba.configurandomipropioendpointenmiservidor.view.fragment.RecyclerViewFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ISerchUser {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String userID;
    private String username;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        PerfilViewFragment perfilViewFragment = new PerfilViewFragment();
        toolbar = (Toolbar) findViewById(R.id.MiActionBar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setUpViewPager();
        userID = getUserID();
        ImageButton imageButton = (ImageButton) toolbar.findViewById(R.id.btnFav);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PageAdapter pageAdapter = (PageAdapter) viewPager.getAdapter();
                Fragment fragment = pageAdapter.getItem(0);
                RecyclerViewFragment recyclerViewFragment = (RecyclerViewFragment) fragment;
                recyclerViewFragment.irActivity2(view);
            }
        });
        /*

*/
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }

    }
    public String getUserID() {
        ConstructorPets constructorPets = new ConstructorPets(this.getBaseContext());
        //Consulto la configuración de usuario
        //almacenada en base de datos local
        return constructorPets.getUserID();
    }
    public void lanzarNotificacion(View view){
        String id_dispositivo = FirebaseInstanceId.getInstance().getToken();
        buscarUsuario(userID);
        String id_usuario_instagram = username;
//        Log.d("TOKEN", token);
        sendTokenRegistration(id_dispositivo, id_usuario_instagram);
    }
    private void sendTokenRegistration(String id_dispositivo, String id_usuario_instagram){
        Log.d("TOKEN", id_dispositivo);
        ResApiAdapter resApiAdapter = new ResApiAdapter();
        Endpoints endpoints = resApiAdapter.establecerConexionRestAPI();
        Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarUsuario(id_dispositivo,id_usuario_instagram);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("ID_FIREBASE", usuarioResponse.getId());
                Log.d("ID_DISPOSITIVO_FIREBASE", usuarioResponse.getId_dispositivo());
                Log.d("ID_USUARIO_INSTAGRAM", usuarioResponse.getId_usuario_instagram());

            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }


    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilViewFragment());

        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_doghose);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dogface);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mContacto:
                Intent intentContacto = new Intent(this,ActivityContacto.class);
                startActivity(intentContacto);
                break;
            case R.id.mAbaut:
                Intent intentAbout = new Intent(this, ActivityAbout.class);
                startActivity(intentAbout);
                break;
            case R.id.mConfig:
                Intent intentConfig = new Intent(this, ActivityConfiguraCuenta.class);
                startActivity(intentConfig);
                break;
            case R.id.mNotification:
                lanzarNotificacion(getWindow().getDecorView().getRootView());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void buscarUsuario(String user) {
        ResApiAdapter resApiAdapter = new ResApiAdapter();
        Gson gsonPerfil = resApiAdapter.construyeGsonDeserializadorPerfil();
        EndpointsApi endpointApi =resApiAdapter.establecerConexionResApiInstagram(gsonPerfil);
        Call<UserResponse> userResponseCall = endpointApi.getSelfInfo(userID);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                //almaceno en variables valores devueltos del webservice
                ArrayList<User> users = userResponse.getUsers();
                username = users.get(0).getId();

                System.out.println("nombre para retrofit "+username);

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(context, "Algo pasó en la conexión! Intenta nuevamente", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXION",t.toString());
            }
        });
    }
}

