package geekbrains.study;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class MyServer {
    private static MyServer server;

    private final int PORT = 8112;
    private List<ClientHandler> clients;
    private AuthService authService;

    public MyServer() {
        server = this;
        try (ServerSocket server = new ServerSocket(PORT)) {
            runAuthService();
            connectionCycle(server);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    private void runAuthService() {
        authService = new BaseAuthService();
        authService.start();
        clients = new ArrayList<>();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void connectionCycle(ServerSocket server) throws IOException {
        while (true) {
            System.out.println("Ждем подключения клиента");
            Socket socket = server.accept();
            System.out.println("Клиент подключился" + clients);
            new ClientHandler(socket);
        }
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler clientHandler : clients) {
            clientHandler.sendMsg(msg);
        }
    }

    public synchronized void privateMsg(ClientHandler sender, String receiver, String msg) {
//        String[] elements = msg.split("\\s+", 3);
//        String key = elements[1];
//        String nickName = elements[2];
        String message = String.format("[ %s ] в личку [ %s ] : %s", sender.getName(), receiver, msg);

        for (ClientHandler clientHandler : clients) {
            if (clientHandler.getName().equals(receiver)) {
                clientHandler.sendMsg(message);
                if (sender.equals(clientHandler)) {
                    return;
                }
                sender.sendMsg(message);
                return;
            }
        }
        sender.sendMsg("Ползователь " + receiver + " не найден.");
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler clientHandler : clients) {
            if (clientHandler.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public static MyServer getServer() {
        return server;
    }

    public AuthService getAuthService() {
        return authService;
    }
}
