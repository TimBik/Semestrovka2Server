package ClientsHandler;


import model.Room;
import model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class ClientHandler extends Thread {
    // связь с одним клиентом
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        // добавляем текущее подключение в список
        AllClientsHandler.getAllClientsHandler().getClients().add(this);
        System.out.println("New client");
    }

    public void run() {
        try {
            // получем входной поток для конкретного клиента
            System.out.println("sad");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            //занимается входом/регитрацией
            int roomId = 0;
            ClientHandler[] playersInRoom = null;
            user = new User(Math.abs(new Random().nextInt()) % (int) 1e6);
            while (true) {
                String inputLine = in.readLine();
                String command[] = inputLine.split(" ");
                if (command[0].equals("room")) {
                    roomId = Integer.parseInt(inputLine.split(" ")[1]);
                    AllClientsHandler.addUserInRoom(this, roomId);
                    out.println("map " + 1);
                    playersInRoom = AllClientsHandler.getRoomsI(roomId).getUsers();
                    int nowPlayers = AllClientsHandler.getCountUserInRoom(roomId);
                    user.setRoomId(nowPlayers);
                    out.println("you_user " + nowPlayers);
                    for (ClientHandler player : playersInRoom) {
                        if (player == null) continue;
                        PrintWriter pw = new PrintWriter(player.clientSocket.getOutputStream(), true);
                        pw.println("new_user " + nowPlayers);
                    }
                } else if (command[0].equals("new_coordinates")) {
                    for (ClientHandler player : playersInRoom) {
                        if (player == null) continue;
                        PrintWriter pw = new PrintWriter(player.clientSocket.getOutputStream(), true);
                        pw.println("new_coordinates user " + user.getRoomId() + " coordinates " + command[1] + " " + command[2]);
                    }
                } else if (command[0].equals("bomb")) {
                    if (roomId == 0) throw new IllegalArgumentException();
                    playersInRoom = AllClientsHandler.getRoomsI(roomId).getUsers();
                    for (ClientHandler player : playersInRoom) {
                        if (player == null) continue;
                        PrintWriter pw = new PrintWriter(player.clientSocket.getOutputStream(), true);
                        pw.println("bomb " + command[1] + " " + command[2]);
                    }
                } else if (command[0].equals("bye")) {
                    break;
                }
            }
            in.close();
            clientSocket.close();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private User user;

}
