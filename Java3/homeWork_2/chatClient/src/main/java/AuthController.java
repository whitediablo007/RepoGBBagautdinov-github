import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AuthController {
    @FXML
    public TextField loginTF;
    @FXML
    public PasswordField passwordTF;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    Socket socket;


    @FXML
    private void initialize() throws IOException {
        socket = ServerConnection.getSocket();
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        addNewThread();
    }

    private void addNewThread() {
        new Thread(() -> {
            try {
                while (true) {
                    System.out.println("Ждем ответ от сервера");
                    String strFromServer = inputStream.readUTF();
                    if (strFromServer.startsWith("/authok")) {
                        Config.nick = strFromServer.split(" ")[1];
                        Platform.runLater(() -> {
                            Stage stage = (Stage) loginTF.getScene().getWindow();
                            stage.close();

                        });
                        break;

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }



    @FXML
    private void auth() throws IOException {
        String authString = "/auth " + loginTF.getText() + " " + passwordTF.getText();
        outputStream.writeUTF(authString);
        loginTF.clear();
        passwordTF.clear();

    }
}
