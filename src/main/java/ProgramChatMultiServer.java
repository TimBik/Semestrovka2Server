import Server.ChatMultiServer;

public class ProgramChatMultiServer {
    public static void main(String[] args) {
        ChatMultiServer server = new ChatMultiServer();
        server.start(9753);
    }
}
