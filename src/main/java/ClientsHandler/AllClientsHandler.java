package ClientsHandler;

import model.Room;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AllClientsHandler {
    //не лучше ли сделать сет? добавление будет за log(), но удаление по ссылке тоже за log();
    // с list же добавление за O(1) а даление за линию
    //выяснил, CopyOnWriteArraySet написан на CopyOnWriteArrayList, ха-ха смешно(нет);
    private List<ClientHandler> clients;
    static private AllClientsHandler me;
    static private Room[] rooms;

    private AllClientsHandler() {
        //не очень понимаю как работает copyAndWriteList
        //при модификации он создает копию старого + апгрейд,
        //что позволяет потокам которые использовали его до мод
        //например все еще перебирают
        //продолжать работать с не измененным
        // в потоко безопасном режиме?
        //Верно ли что тогда мы храним квадрат ссылок?
        //для оптимизаций, он наверняка должен удалять некоторые не нужные
        //состояния, это так?
        rooms = new Room[30];

        clients = new CopyOnWriteArrayList<>();
    }

    static public AllClientsHandler getAllClientsHandler() {
        if (me == null) {
            me = new AllClientsHandler();
        }
        return me;
    }

    public static void addUserInRoom(ClientHandler user, int room) {
        if (rooms[room] == null) rooms[room] = new Room();
        rooms[room].setUser(user);
    }

    public static int getCountUserInRoom(int room) {
        return rooms[room].getSizeUsers();
    }

    public static Room getRoomsI(int i) {
        return rooms[i];
    }







    public List<ClientHandler> getClients() {
        return clients;
    }
}
