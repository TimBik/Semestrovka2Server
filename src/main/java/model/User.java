package model;

public class User {
    int id;
    int roomId;

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
