package host;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        Socket socket;
        try (ServerSocket serverSocket = new ServerSocket(8112)) {
            socket = serverSocket.accept();

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String str = in.readUTF();
                out.writeUTF(str);

                if (str.equals("/end")) {
                    System.exit(0);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
