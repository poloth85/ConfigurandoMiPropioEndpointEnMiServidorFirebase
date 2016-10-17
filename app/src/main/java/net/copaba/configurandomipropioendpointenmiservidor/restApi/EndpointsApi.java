package net.copaba.configurandomipropioendpointenmiservidor.restApi;

import net.copaba.configurandomipropioendpointenmiservidor.pojo.PetPerfil;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.model.PetResponse;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.model.UserResponse;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.model.UsuarioResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Polo on 12/09/16.
 */
public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIAUSER_ID)
    Call<PetResponse> getRecentMedia(@Path("userID") String userID);

    @GET(ConstantesRestApi.URL_SELF_INFO)
    Call<UserResponse> getSelfInfo(@Path("userID") String userID);

    @GET(ConstantesRestApi.URL_SERCH)
    Call<UserResponse> getUserID(@QueryMap Map<String, String> params);

    @GET(ConstantesRestApi.URL_SANDBOX_USERS)
    Call<UserResponse> getUserSandboxID();

}
