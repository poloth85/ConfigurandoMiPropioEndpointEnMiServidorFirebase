package net.copaba.configurandomipropioendpointenmiservidor.restApi.model;

import net.copaba.configurandomipropioendpointenmiservidor.pojo.User;

import java.util.ArrayList;

/**
 * Created by Polo on 15/09/16.
 */
public class UserResponse {
    ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
