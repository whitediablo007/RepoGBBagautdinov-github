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
            System.out.println("Клиент подключился");
            new ClientHandler(socket);
        }
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        broadcastClientList();
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
        broadcastClientList();
    }

    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler clientHandler : clients) {
            clientHandler.sendMsg(msg);
        }
    }

    public synchronized void privateMsg(ClientHandler sender, String receiver, String msg) {
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
        sender.sendMsg("Ползователь с ником [ " + receiver + " ] не найден.");
    }

    public synchronized void broadcastClientList() {
        StringBuilder sb = new StringBuilder("/clients");
        for (ClientHandler client : clients) {
            sb.append(" ").append(client.getName());
        }
        broadcastMsg(sb.toString());
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler clientHandler : clients) {
            if (clientHandler.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public synchronized boolean isNickUnAuth() {
        for (ClientHandler clientHandler : clients) {
            if (clientHandler.getName().equals("Инкогнито")) {
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
