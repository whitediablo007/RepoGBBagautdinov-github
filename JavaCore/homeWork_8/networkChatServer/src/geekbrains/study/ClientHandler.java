package geekbrains.study;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private long lastMsgTime;
    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name = "";

    private final String DELIMITER = " ";
    private final int SIZE_OF_STRING_ARRAY = 3;
    private final int RECEIVER_INDEX = 1;
    private final int LOGIN_INDEX = 1;
    private final int MESSAGE_INDEX = 2;
    private final int PASS_INDEX = 2;


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
                    if (!server.isNickBusy(nick)) {
                        sendMsg("/authok " + nick);
                        name = "Инкогнито";
                        server.broadcastMsg(name + " зашел в чат");
                        server.subscribe(this);
                        return;
                    } else {
                        sendMsg("Учетная запись уже используется");
                    }
                    sendMsg("Неверные логин/пароль");
                }
            } else {
                sendMsg("Перед тем как отправлять сообщения авторизуйтесь через команду </auth login1 pass1>");
            }
        }
    }

    private String getAuthData(String str) {
        String[] parts = str.split(DELIMITER);
        String login = parts[LOGIN_INDEX];
        String password = parts[PASS_INDEX];
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

            if (lastMsgTime != 0 && System.currentTimeMillis() - lastMsgTime < 10000) {
                server.privateMsg(this, getName(), "Незарегистрированным пользователям нельзя отправлять сообщение чаще, чем раз в 10 секунд");
                continue;
            }
            lastMsgTime = System.currentTimeMillis();

            System.out.println("от " + name + ": " + strFromClient);
            if (strFromClient.startsWith("/")) {
                if (strFromClient.equals("/end")) {
                    return;
                }
                if (strFromClient.startsWith("/w")) {
                    String[] elements = strFromClient.split("\\s+", SIZE_OF_STRING_ARRAY);
                    server.privateMsg(this, elements[RECEIVER_INDEX], elements[MESSAGE_INDEX]);
                }
                continue;
            }
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
