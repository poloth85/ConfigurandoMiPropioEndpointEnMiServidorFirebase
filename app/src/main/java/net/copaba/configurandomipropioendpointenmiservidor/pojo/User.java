package net.copaba.configurandomipropioendpointenmiservidor.pojo;

import java.io.Serializable;

/**
 * Created by Polo on 16/09/16.
 */
public class User implements Serializable {
    String id;
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User() {
    }

    public User(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
