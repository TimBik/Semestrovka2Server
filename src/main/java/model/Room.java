package model;

import ClientsHandler.ClientHandler;

public class Room {

    public Room() {
        users = new ClientHandler[4];
    }

    public int getSizeUsers() {
        return sizeUsers;
    }

    public ClientHandler[] getUsers() {
        return users;
    }

    int sizeUsers = 0;
    ClientHandler[] users;

    public void setUser(ClientHandler user) {
        users[sizeUsers] = user;
        sizeUsers++;
    }

}
