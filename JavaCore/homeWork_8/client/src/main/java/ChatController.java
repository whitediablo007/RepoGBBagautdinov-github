import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatController {

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    @FXML
    private TextField textField;
    @FXML
    private TextArea textArea;

    @FXML
    private void initialize() throws IOException {
        try {
            openLoginWindow();
            Main.mainStage.setTitle(Main.mainStage.getTitle() + "(" + Config.nick + ")");
            openConnection();
            addCloseListener();
        } catch (IOException exception) {
            connectionAlertWindow();
            exception.printStackTrace();
            throw exception;
        }
    }

    private void openLoginWindow() throws IOException {
        Parent root = FXMLLoader.load(ClassLoader.getSystemResource("auth.fxml"));
        Stage loginStage = new Stage();
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setScene(new Scene(root));
        loginStage.setTitle("Вход");
        loginStage.showAndWait();
    }

    private void connectionAlertWindow() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка подключения");
        alert.setHeaderText("Сервер не найден!");
        alert.setContentText("Вначале запустите сервер");
        alert.showAndWait();
    }

    private void openConnection() throws IOException {
        socket = ServerConnection.getSocket();
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        addNewThread();
    }

    private void addNewThread() {
        new Thread(() -> {
            try {
                while (socket.isConnected()) {
                    System.out.println("Готовы считывать");
                    String strFromServer = inputStream.readUTF();
                    System.out.println("Считал " + strFromServer);
                    if (exitCondition(strFromServer)) {
                        return;
                    }
                    textArea.setText(textArea.getText() + strFromServer + "\n");
                }
            } catch (Exception exception) {
                //exception.printStackTrace();
            } finally {
                closeConnection();
            }
        }).start();
    }

    private boolean exitCondition(String strFromServer) {
        if (strFromServer.equalsIgnoreCase("/end")) {
            System.out.println("Клиент отключен от сервера");
            return true;
        }
        return false;
    }


    private void addCloseListener() {
        EventHandler<WindowEvent> onCloseRequest = Main.mainStage.getOnCloseRequest();
        Main.mainStage.setOnCloseRequest(event -> {
            closeConnection();
            if (onCloseRequest != null) {
                onCloseRequest.handle(event);
            }
        });
    }

    private void closeConnection() {
        try {
            inputStream.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    public void clickSend() {
        if (!textField.getText().trim().equals("")) {
            textImport();
        }
    }

    @FXML
    public void keyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER && !textField.getText().trim().equals("")) {
            textImport();
        }
    }

    @FXML
    private void textImport() {
        //textArea.appendText("Я: " + textField.getText().trim() + "\n");
        try {
            outputStream.writeUTF(textField.getText().trim());
            textField.clear();
            textField.requestFocus();
        } catch (IOException exception) {
            //exception.printStackTrace();
            sendMessageAlertWindow(exception);
        }
    }

    private void sendMessageAlertWindow(IOException exception) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка отправки сообщения");
        alert.setHeaderText("Ошибка отправки сообщения");
        alert.setContentText("При отправке сообщения возникла ошибка: " + exception.getMessage());
        alert.show();
    }
}
