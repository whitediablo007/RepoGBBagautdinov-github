package geekbrains.study;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name = "";
    private final String DELIMITER = " ";


    public ClientHandler(Socket socket) throws IOException {
        this.server = MyServer.getServer();
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        addNewThread();
    }

    private void addNewThread() {
        new Thread(() -> {
            try {
                auth();
                readMsg();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }).start();
    }

    private void auth() throws IOException {
        while (true) {
            String str = in.readUTF();
            //  /auth login1 pass1
            if (str.startsWith("/auth")) {
                String nick = getAuthData(str);
                if (nick != null) {
                    if (confirmAuth(nick)) return;
                } else {
                    sendMsg("Неверные логин/пароль");
                }
            } else {
                sendMsg("Перед тем как отправлять сообщения авторизуйтесь через команду </auth login1 pass1>");
            }
        }
    }

    private String getAuthData(String str) {
        String[] parts = str.split(DELIMITER);
        String login = parts[1];
        String password = parts[2];
        return server.getAuthService().getNickByLoginPass(login, password);
    }

    private boolean confirmAuth(String nick) {
        if (!server.isNickBusy(nick)) {
            sendMsg("/authok " + nick);
            name = nick;
            server.broadcastMsg(name + " зашел в чат");
            server.subscribe(this);
            return true;
        } else {
            sendMsg("Учетная запись уже используется");
        }
        return false;
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMsg() throws IOException {
        while (true) {
            String strFromClient = in.readUTF();
            System.out.println("от " + name + ": " + strFromClient);
            if (strFromClient.equals("/end")) {
                return;
            }
            if (strFromClient.startsWith("/w")) {
                server.privateMsg(name + ": " + strFromClient);
            } else
                server.broadcastMsg(name + ": " + strFromClient);
        }
    }

    public void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMsg(name + " вышел из чата");
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
}
