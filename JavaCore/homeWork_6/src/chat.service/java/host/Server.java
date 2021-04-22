package host;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        Socket socket;
        try (ServerSocket serverSocket = new ServerSocket(8112)) {
            socket = serverSocket.accept();

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

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
            try {

                while (true) {
                    Scanner scanner = new Scanner(System.in);
                    String message = scanner.nextLine();
                    outputStream.writeUTF(message);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

