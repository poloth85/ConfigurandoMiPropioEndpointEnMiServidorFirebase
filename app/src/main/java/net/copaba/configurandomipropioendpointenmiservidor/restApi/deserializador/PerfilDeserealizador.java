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
 * Created by Polo on 13/10/16.
 */
public class PerfilDeserealizador implements JsonDeserializer<UserResponse> {
    @Override
    public UserResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        UserResponse userResponse = gson.fromJson(json, UserResponse.class);
        JsonArray userResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        userResponse.setUsers(deserealizarUserDeJson(userResponseData));
        return null;
    }
    private ArrayList<User> deserealizarUserDeJson(JsonArray userResponseData){
        ArrayList<User> users = new ArrayList<>();
        String user = "";
        for(int i = 0; i < userResponseData.size(); i++){
            JsonObject userResponseDataObject = userResponseData.get(i).getAsJsonObject();

            //JsonObject userJson   = userResponseDataObject.getAsJsonObject(JsonKeys.USER);
            user = userResponseDataObject.get(JsonKeys.USER_FULLNAME).getAsString();
            //en caso de que el usuario no exista, inicializo con mi perfil
            if(user == null) {
                user = "";
            }
            User usuarioActual = new User(user);
            users.add(usuarioActual);
        }
        return users;
    }
}
