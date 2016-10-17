package net.copaba.configurandomipropioendpointenmiservidor.db;

/**
 * Created by Polo on 01/09/16.
 */
public class ConstantesBaseDatos {

    public static final String DATABASE_NAME = "pets";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_PETS           = "pet";
    public static final String TABLE_PETS_ID        = "id";
    public static final String TABLE_PETS_NOMBRE    = "nombre";
    public static final String TABLE_PETS_LIKES     = "pet_likes";
    public static final String TABLE_PETS_FOTO      = "foto";

    public static final String TABLE_LIKES_PET              = "pet_likes";
    public static final String TABLE_LIKES_PET_ID           = "id";
    public static final String TABLE_LIKES_PET_ID_PET       = "id_pet";
    public static final String TABLE_LIKES_PET_NUMERO_LIKES = "numero_likes";

    public static final String TABLE_USER = "user";
    public static final String TABLE_USER_ID = "id";
    public static final String TABLE_USER_ID_USER = "id_user";


    public static final int RATER = 0;

    public static final int SCORE = 1;
}
