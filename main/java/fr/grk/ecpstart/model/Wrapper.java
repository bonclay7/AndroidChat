package fr.grk.ecpstart.model;

import java.util.List;

/**
 * Created by grk on 20/01/15.
 */
public class Wrapper {
    private String server;
    private List<User> users;


    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
