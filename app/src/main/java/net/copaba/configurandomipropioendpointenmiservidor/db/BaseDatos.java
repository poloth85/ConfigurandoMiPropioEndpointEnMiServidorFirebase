package net.copaba.configurandomipropioendpointenmiservidor.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.copaba.configurandomipropioendpointenmiservidor.pojo.Pet;

import java.util.ArrayList;

/**
 * Created by Polo on 01/09/16.
 */
public class BaseDatos extends SQLiteOpenHelper {
    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaPet = "CREATE TABLE " + ConstantesBaseDatos.TABLE_PETS + "(" +
                ConstantesBaseDatos.TABLE_PETS_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ConstantesBaseDatos.TABLE_PETS_NOMBRE   + " TEXT, "      +
                ConstantesBaseDatos.TABLE_PETS_LIKES   + " INTEGER, "   +
                ConstantesBaseDatos.TABLE_PETS_FOTO     + " INTEGER"    +
                ")";
        String queryCrearTablaLikesPet = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_PET + "(" +
                ConstantesBaseDatos.TABLE_LIKES_PET_ID              + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ConstantesBaseDatos.TABLE_LIKES_PET_ID_PET     + " INTEGER, "    +
                ConstantesBaseDatos.TABLE_LIKES_PET_NUMERO_LIKES    + " INTEGER, "    +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_PET_ID_PET + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_PETS + "(" + ConstantesBaseDatos.TABLE_PETS_ID  + ")" +
                ")";
        String queryCrearTablaUser = "CREATE TABLE " + ConstantesBaseDatos.TABLE_USER + "(" +
                ConstantesBaseDatos.TABLE_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ConstantesBaseDatos.TABLE_USER_ID_USER + " TEXT" +
                ")";
        db.execSQL(queryCrearTablaPet);
        db.execSQL(queryCrearTablaLikesPet);
        db.execSQL(queryCrearTablaUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_PETS);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_LIKES_PET);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_USER);
        onCreate(db);
    }
    //Reviso si no hay datos inicializados en la app para que nos e dupliquen cada vez que inicia.
    public int countRow(){
        int result = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT *,COUNT(*) FROM "+ConstantesBaseDatos.TABLE_PETS;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToNext()){

            result = cursor.getInt(0);
        }
        db.close();
        return result;
    }
    public int countRowLike(){
        int result = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT *,COUNT(*) FROM "+ConstantesBaseDatos.TABLE_LIKES_PET;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToNext()){

            result = cursor.getInt(0);
        }
        db.close();
        return result;
    }
    public String getUserID(){
        String userID = "";
        String query = "SELECT " + ConstantesBaseDatos.TABLE_USER_ID_USER +
                " FROM " + ConstantesBaseDatos.TABLE_USER +
                " WHERE " + ConstantesBaseDatos.TABLE_USER_ID +
                " = 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        while (registros.moveToNext()){
            userID = registros.getString(0);
        }
        db.close();
        return userID;
    }
    public String getUserIDName(){
        String userID = "";
        String query = "SELECT " + ConstantesBaseDatos.TABLE_USER_ID_USER +
                " FROM " + ConstantesBaseDatos.TABLE_USER +
                " WHERE " + ConstantesBaseDatos.TABLE_USER_ID +
                " = 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        while (registros.moveToNext()){
            userID = registros.getString(0);
        }
        db.close();
        return userID;
    }
    public void insertUserId(ContentValues contentValues ){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_USER, null, contentValues);
        db.close();
    }
    public void updateUserId(String userID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValuesUpdate = new ContentValues();
        contentValuesUpdate.put(ConstantesBaseDatos.TABLE_USER_ID_USER, userID);
        db.update(ConstantesBaseDatos.TABLE_USER, contentValuesUpdate, ConstantesBaseDatos.TABLE_USER_ID + "= ?", new String[]{String.valueOf(1)});
        db.close();
    }
    public int countUsers(){
        int result = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT *,COUNT(*) FROM "+ConstantesBaseDatos.TABLE_USER;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToNext()){

            result = cursor.getInt(0);
        }
        db.close();
        return result;
    }

    //Regresa el arreglo con todas las mascotas de la app
    public ArrayList<Pet> getAllPets(){
        ArrayList<Pet> pets = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_PETS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        while (registros.moveToNext()){
            Pet currentPet = new Pet();
            currentPet.setId(registros.getInt(0));
            currentPet.setNombre(registros.getString(1));
            currentPet.setLikes(registros.getInt(2));
            currentPet.setFoto(registros.getInt(3));
            pets.add(currentPet);
        }

        db.close();

        return pets;
    }

    //inserta mascotas
    public void insertPet(ContentValues contentValues ){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_PETS, null, contentValues);
        db.close();
    }
    //inserta like a cada mascota
    public void insertPetLike(ContentValues contentValues, ContentValues contentValuesUpdate, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_PET, null, contentValues);
        db.update(ConstantesBaseDatos.TABLE_PETS, contentValuesUpdate, ConstantesBaseDatos.TABLE_PETS_ID + "= ?", new String[]{String.valueOf(id)});
        db.close();
    }
    //Actualizo la base de datos con los likes que el usario da.
    public void updatePetLike(Pet pet){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_LIKES_PET +
                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_PET_ID_PET + " != " + pet.getId() +
                " ORDER BY " + ConstantesBaseDatos.TABLE_LIKES_PET_ID + " DESC";
        Cursor cursor = db.rawQuery(query,null);

        int cont = 0;
        //Verifico que el cursor tiene por lo menos un elemento
        if(cursor.moveToLast()) {
            //Si la tabla de likes llego a 5 elementos, recorro el cursor un lugar para recorrer los elementos
            //que teniamos de modo que dejamos libre el ultimo lugar para el nuevo elemento que no se encuentra
            //dentro de la tabla.
            if(cursor.getCount()==5) {
                cursor.moveToPrevious();
            }
            do {//recorremos el cursor para rehubicar los elementos en su nueva posición
                if(cursor.getInt(1)==pet.getId()) {
                    cursor.moveToPrevious();
                }
                cont++;
                ContentValues contentValuesUpdate = new ContentValues();
                contentValuesUpdate.put(ConstantesBaseDatos.TABLE_LIKES_PET_ID_PET, cursor.getInt(1));
                contentValuesUpdate.put(ConstantesBaseDatos.TABLE_LIKES_PET_NUMERO_LIKES, cursor.getInt(2));
                db.update(ConstantesBaseDatos.TABLE_LIKES_PET, contentValuesUpdate, ConstantesBaseDatos.TABLE_LIKES_PET_ID + "=?", new String[]{String.valueOf(cont)});
            }while(cursor.moveToPrevious());
            //Colocamos el nuevo elemento al final de la tabla de likes.
            cont++;
            ContentValues x = new ContentValues();
            x.put(ConstantesBaseDatos.TABLE_LIKES_PET_ID_PET, pet.getId());
            x.put(ConstantesBaseDatos.TABLE_LIKES_PET_NUMERO_LIKES, pet.getLikes());
            db.update(ConstantesBaseDatos.TABLE_LIKES_PET, x, ConstantesBaseDatos.TABLE_LIKES_PET_ID + "= ?", new String[]{String.valueOf(cont)});
            //Actualizamos los likes de la mascota en la tabla de mascotas.
            x = new ContentValues();
            x.put(ConstantesBaseDatos.TABLE_PETS_LIKES, pet.getLikes());
            db.update(ConstantesBaseDatos.TABLE_PETS, x, ConstantesBaseDatos.TABLE_PETS_ID + "= ?", new String[]{String.valueOf(pet.getId())});
        }else {
            //Si no hay mas de un elemento en la tabla de likes simplemente se actualiza el elemento con el nuevo valor
            //de like
            cont++;
            ContentValues x = new ContentValues();
            x.put(ConstantesBaseDatos.TABLE_LIKES_PET_ID_PET, pet.getId());
            x.put(ConstantesBaseDatos.TABLE_LIKES_PET_NUMERO_LIKES, pet.getLikes());
            db.update(ConstantesBaseDatos.TABLE_LIKES_PET, x, ConstantesBaseDatos.TABLE_LIKES_PET_ID + "= ?", new String[]{String.valueOf(cont)});
            x = new ContentValues();
            //Se actualizan los likes dados al elemento en la tabla de mascotas
            x.put(ConstantesBaseDatos.TABLE_PETS_LIKES, pet.getLikes());
            db.update(ConstantesBaseDatos.TABLE_PETS, x, ConstantesBaseDatos.TABLE_PETS_ID + "= ?", new String[]{String.valueOf(pet.getId())});
        }
        db.close();


    }

    //Regresa los likes que tiene la mascota
    public int getPetLikes(Pet pet){
        int likes = 0;
        String query = "SELECT " + ConstantesBaseDatos.TABLE_PETS_LIKES +
                " FROM " + ConstantesBaseDatos.TABLE_PETS +
                " WHERE " + ConstantesBaseDatos.TABLE_PETS_ID + " = " + pet.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);
        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }
        db.close();

        return likes;
    }
    //Regresa un arreglo con las 5 ultimas mascotas que se les dio like
    public ArrayList<Pet> getLastFiveLikePets() {
        ArrayList<Pet> pets = new ArrayList<>();

        String query = "SELECT " + ConstantesBaseDatos.TABLE_LIKES_PET_ID_PET + " FROM " + ConstantesBaseDatos.TABLE_LIKES_PET +
                " ORDER BY " + ConstantesBaseDatos.TABLE_LIKES_PET_ID + " DESC";;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        while (registros.moveToNext()){
            int id = registros.getInt(0);
            ArrayList<Pet> allPets = getAllPets();
            for(int i = 0; i < allPets.size(); i++){
                if(allPets.get(i).getId() == id){
                    pets.add(allPets.get(i));
                }
            }

        }
        db.close();

        return pets;
    }
}
