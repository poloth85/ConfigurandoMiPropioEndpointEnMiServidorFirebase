package net.copaba.configurandomipropioendpointenmiservidor.db;

import android.content.ContentValues;
import android.content.Context;

import net.copaba.configurandomipropioendpointenmiservidor.pojo.Pet;

import java.util.ArrayList;

/**
 * Created by Polo on 01/09/16.
 */
public class ConstructorPets {
    private int LIKE = 0;
    private Context context;
    public ConstructorPets(Context context) {
        this.context = context;
    }
    public ArrayList<Pet> getData(){
        BaseDatos db = new BaseDatos(context);
        if(db.countRow() == 0) {
            insertPets(db);
        }
        return db.getAllPets();
    }
    public void setData(){
        BaseDatos db = new BaseDatos(context);
        if(db.countUsers() == 0){
            insertPets(db);
        }
    }
//Inserta los datos de las mascotas a la base de datos cuando se crea.
    private void insertPets(BaseDatos db) {

        ContentValues contentValues = new ContentValues();
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NOMBRE, "Lola");
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_LIKES, 0);
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_FOTO, R.drawable.dog_6);
//        db.insertPet(contentValues);
//
//        contentValues = new ContentValues();
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NOMBRE, "Caris");
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_LIKES, 0);
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_FOTO, R.drawable.dog_5);
//        db.insertPet(contentValues);
//
//        contentValues = new ContentValues();
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NOMBRE, "Yordi");
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_LIKES, 0);
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_FOTO, R.drawable.dog_4);
//        db.insertPet(contentValues);
//
//        contentValues = new ContentValues();
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NOMBRE, "Mimi");
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_LIKES, 0);
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_FOTO, R.drawable.dog_3);
//        db.insertPet(contentValues);
//
//        contentValues = new ContentValues();
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NOMBRE, "Mike");
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_LIKES, 0);
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_FOTO, R.drawable.dog_2);
//        db.insertPet(contentValues);
//
//        contentValues = new ContentValues();
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NOMBRE, "Teo");
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_LIKES, 0);
//        contentValues.put(ConstantesBaseDatos.TABLE_PETS_FOTO, R.drawable.dog_1);
//        db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_USER_ID_USER, "3895389452");
        db.insertUserId(contentValues);
    }
    //ingresa cada like que se le da a la mascota en la base de datos
    public void likePet(Pet pet){
        BaseDatos db = new BaseDatos(context);
        LIKE = getPetLikes(pet) + 1;
        ContentValues contentValues = new ContentValues();
        ContentValues contentValuesUpdate = new ContentValues();
        pet.setLikes(LIKE);
        if(getPetLikes(pet) == 0 && getLikeCout()<5){
            contentValues.put(ConstantesBaseDatos.TABLE_LIKES_PET_ID_PET, pet.getId());
            contentValues.put(ConstantesBaseDatos.TABLE_LIKES_PET_NUMERO_LIKES, LIKE);
            contentValuesUpdate.put(ConstantesBaseDatos.TABLE_PETS_LIKES,LIKE);
            db.insertPetLike(contentValues,contentValuesUpdate,pet.getId());
        }else{
            db.updatePetLike(pet);
        }
    }
    //Consulta y regresa los likes de la mascota en la base de datos
    public int getPetLikes(Pet pet){
        BaseDatos db = new BaseDatos(context);
        return db.getPetLikes(pet);
    }
    //Consulta y regresa un arreglo con las ultimas 5 mascotas a las que se les dio like
    public ArrayList<Pet> getLastFiveLikePets() {
        BaseDatos db = new BaseDatos(context);
        return db.getLastFiveLikePets();
    }
    public int getLikeCout(){
        BaseDatos db = new BaseDatos(context);
        return db.countRowLike();
    }
    public String getUserID(){
        BaseDatos db = new BaseDatos(context);
        return db.getUserID();
    }
    public void setUserID(String userID){
        BaseDatos db = new BaseDatos(context);
        db.updateUserId(userID);
    }
}
