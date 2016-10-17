package net.copaba.configurandomipropioendpointenmiservidor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import net.copaba.configurandomipropioendpointenmiservidor.db.ConstructorPets;
import net.copaba.configurandomipropioendpointenmiservidor.pojo.User;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.ConstantesRestApi;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.EndpointsApi;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.adapter.ResApiAdapter;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.model.UserResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Polo on 11/09/16.
 */
public class ActivityConfiguraCuenta extends AppCompatActivity implements ISerchUser {
    private TextInputEditText txtCuenta;
    private ArrayList<User> users;
    private Context context;
    private String userID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configura_cuenta);

        context = this;

        Toolbar miActionBar = (Toolbar) findViewById(R.id.MiActionBar);
        setSupportActionBar(miActionBar);
        findViewById(R.id.btnFav).setVisibility(View.INVISIBLE);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        Button button = (Button) findViewById(R.id.button_guardar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCuenta = (TextInputEditText) findViewById(R.id.txtCuenta);
                if(txtCuenta.length() == 0){
                    txtCuenta.setError("Este campo no puese ir vacio");
                    txtCuenta.requestFocus();
                    return;
                }
                String cuenta = txtCuenta.getText().toString();
                buscarUsuario(cuenta);
//                guardarCuenta(userID);
                txtCuenta.setText("");
            }
        });

    }
    private void guardarCuenta(String userID){
        Toast.makeText(ActivityConfiguraCuenta.this, "Usuario Configurado Correctamente", Toast.LENGTH_SHORT).show();
        ConstructorPets constructorPets = new ConstructorPets(context);
        constructorPets.setUserID(userID);
    }

    @Override
    public void buscarUsuario(final String user) {
        ResApiAdapter resApiAdapter = new ResApiAdapter();
        Gson gsonMediaRecent = resApiAdapter.construyeGsonDeserializadorUser();
        EndpointsApi endpointApi =resApiAdapter.establecerConexionResApiInstagram(gsonMediaRecent);
        //Doy de alta los parametros que mando a la url en este caso el usuario a buscar y accestoken
        Map<String, String> params = new HashMap<>();
        params.put("q", user);
        params.put("access_token", ConstantesRestApi.ACCESS_TOKEN);

        Call<UserResponse> userResponseCall = endpointApi.getUserID(params);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                if(userResponse!=null) {
                    //almaceno en las variables los valores que regresa
                    //el webservice
                    users = userResponse.getUsers();
                    if(users.size()!=0) {
                        userID = users.get(0).getId();
                        //mando guardar el usuario a la base de datos local para que
                        //no se pierdan los datos de la configuración
                        guardarCuenta(userID);

                    }else{
                        Toast.makeText(ActivityConfiguraCuenta.this, "Usuario no se puede configurar", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(context, "Algo pasó en la conexión! Intenta nuevamente", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXION",t.toString());
            }
        });
    }
}
