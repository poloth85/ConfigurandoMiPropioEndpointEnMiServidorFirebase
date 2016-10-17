package net.copaba.configurandomipropioendpointenmiservidor.restApi;

import net.copaba.configurandomipropioendpointenmiservidor.restApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Polo on 09/10/16.
 */
public interface Endpoints {

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarUsuario(@Field("id_dispositivo") String id_dispositivo,
                                           @Field("id_usuario_instagram") String id_usuario_instagram);
}
