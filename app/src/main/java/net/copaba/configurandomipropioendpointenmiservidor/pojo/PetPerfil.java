package net.copaba.configurandomipropioendpointenmiservidor.pojo;

import java.io.Serializable;

/**
 * Created by Polo on 17/08/16.
 */
public class PetPerfil implements Serializable {
//    private int id;
    private String id;
    private String nombre;
//    private int foto;
    private int likes;
    private String urlFoto;
    private String urlFotoPerfil;

//    public Pet(int id, String nombre, int foto, int likes) {
public PetPerfil(String id, String nombre, String urlFoto, int likes, String urlFotoPerfil) {
        this.id = id;
        this.nombre = nombre;
//        this.foto = foto;
        this.urlFoto = urlFoto;
        this.likes = likes;
    this.urlFotoPerfil = urlFotoPerfil;
    }

    public PetPerfil() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
    //    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getUrlFotoPerfil() {
        return urlFotoPerfil;
    }

    public void setUrlFotoPerfil(String urlFotoPerfil) {
        this.urlFotoPerfil = urlFotoPerfil;
    }
}
