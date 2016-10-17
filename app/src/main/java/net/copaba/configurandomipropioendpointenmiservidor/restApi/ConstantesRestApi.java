package net.copaba.configurandomipropioendpointenmiservidor.restApi;

/**
 * Created by Polo on 12/09/16.
 */
public final  class ConstantesRestApi {
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "3895389452.7301f24.b978ca0b16fa4b058b011e9c3ec4a13d";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_RECENTE_MEDIA_USERID = "users/{userID}/media/recent/";
    public static final String URL_GET_RECENT_MEDIAUSER_ID= KEY_GET_RECENTE_MEDIA_USERID + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    //url con la que mando obtener el id del usuario a registrar en la configuracion de la app
//    public static final String URL_SERCH = "https://api.instagram.com/v1/users/search";
    public static final String URL_SERCH = "users/search";
    public static final String URL_SANDBOX_USERS = "users/self/follows?access_token="+ACCESS_TOKEN;
    public static final String URL_SELF_INFO = "users/{user-id}/"+KEY_ACCESS_TOKEN+ACCESS_TOKEN;


    //https://api.instagram.com/v1/users/self/media/recent/?access_token=3895389452.7301f24.b978ca0b16fa4b058b011e9c3ec4a13d
    ////https://api.instagram.com/v1/users/3895389452/?access_token=3895389452.7301f24.b978ca0b16fa4b058b011e9c3ec4a13d
    //https://api.instagram.com/v1/users/{user-id}/?access_token=ACCESS-TOKEN
    //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN
    //https://api.instagram.com/v1/users/3895389452/media/recent/?access_token=3895389452.7301f24.b978ca0b16fa4b058b011e9c3ec4a13d
    //https://api.instagram.com/v1/users/search?q=poloth85&access_token=3895389452.7301f24.b978ca0b16fa4b058b011e9c3ec4a13d
    //https://api.instagram.com/v1/users/self/followed-by?access_token=ACCESS-TOKEN
    //https://api.instagram.com/v1/users/self/followed-by?access_token=3895389452.7301f24.b978ca0b16fa4b058b011e9c3ec4a13d


    public static final String ROOT_URL1 = "https://blooming-bastion-14490.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "registrar-usuario/";
}
