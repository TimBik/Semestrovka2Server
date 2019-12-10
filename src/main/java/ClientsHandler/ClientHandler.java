package ClientsHandler;


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
            while (true) {
                String inputLine = in.readLine();
                String command[] = inputLine.split(" ");
                 if (command[0].equals("bye")) {
                    break;
                }
            }
            in.close();
            clientSocket.close();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


}
