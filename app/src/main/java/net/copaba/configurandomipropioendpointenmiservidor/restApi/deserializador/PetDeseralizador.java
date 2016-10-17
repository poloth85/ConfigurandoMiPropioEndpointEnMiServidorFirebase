package net.copaba.configurandomipropioendpointenmiservidor.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.copaba.configurandomipropioendpointenmiservidor.pojo.PetPerfil;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.JsonKeys;
import net.copaba.configurandomipropioendpointenmiservidor.restApi.model.PetResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Polo on 12/09/16.
 */
public class PetDeseralizador implements JsonDeserializer<PetResponse> {
    @Override
    public PetResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        PetResponse petResponse = gson.fromJson(json, PetResponse.class);
        JsonArray petResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        petResponse.setPets(deserealizarPetDeJson(petResponseData));
        return petResponse;

    }
    private ArrayList<PetPerfil> deserealizarPetDeJson(JsonArray petResponseData){
        ArrayList<PetPerfil> pets = new ArrayList<>();
        for(int i = 0; i < petResponseData.size(); i++){

            JsonObject petResponseDataObject = petResponseData.get(i).getAsJsonObject();

            JsonObject userJson   = petResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id             = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = userJson.get(JsonKeys.USER_FULLNAME).getAsString();
            String urlFotoPefil   = userJson.get(JsonKeys.MEDIA_IMAGE_PROFILE_URL).getAsString();

            JsonObject imageJson     = petResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolution = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto           = stdResolution.get(JsonKeys.MEDIA_URL).getAsString();





            JsonObject likesJson = petResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            PetPerfil petActual = new PetPerfil(id, nombreCompleto, urlFoto, likes, urlFotoPefil);
            pets.add(petActual);

        }
        return pets;
    }
}
