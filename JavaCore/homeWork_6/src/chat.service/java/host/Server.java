package host;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        Socket socket;
        try (ServerSocket serverSocket = new ServerSocket(8112)) {
            socket = serverSocket.accept();

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            addNewThread(inputStream);
            sendFromServerConsole(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addNewThread(DataInputStream inputStream) {
        new Thread(() -> {
            try {
                while (true) {
                    String string = inputStream.readUTF();
                    System.out.println(string);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }).start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private static void sendFromServerConsole(DataOutputStream outputStream) {
        try {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String message = scanner.nextLine();
                outputStream.writeUTF(message);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

