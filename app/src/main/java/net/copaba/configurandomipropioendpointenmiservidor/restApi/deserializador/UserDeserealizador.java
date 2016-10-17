package net.copaba.configurandomipropioendpointenmiservidor.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.copaba.configurandomipropioendpointenmiservidor.pojo.User;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.JsonKeys;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.model.UserResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Polo on 15/09/16.
 */
public class UserDeserealizador implements JsonDeserializer<UserResponse> {
    @Override
    public UserResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        UserResponse userResponse = gson.fromJson(json, UserResponse.class);
        JsonArray userResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        userResponse.setUsers(deserealizarUserDeJson(userResponseData));
        return userResponse;
    }
    private ArrayList<User> deserealizarUserDeJson(JsonArray userResponseData){
        ArrayList<User> users = new ArrayList<>();
        String user = "3895389452";
        for(int i = 0; i < userResponseData.size(); i++){
            JsonObject userResponseDataObject = userResponseData.get(i).getAsJsonObject();

            //JsonObject userJson   = userResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id = userResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            //en caso de que el usuario no exista, inicializo con mi perfil
            if(id == null) {
                id = user;
            }
            User usuarioActual = new User(id);
            users.add(usuarioActual);
        }
        return users;
    }
}
