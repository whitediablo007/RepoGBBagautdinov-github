import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.WindowEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatController {
    private boolean readyOfRead = true;
    private final String SERVER_IP = "localhost";
    private final int SERVER_PORT = 8112;

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
            openConnection();
            addCloseListener();
        } catch (IOException exception) {
            connectionAlertWindow();
            exception.printStackTrace();
            throw exception;
        }
    }

    private void connectionAlertWindow() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка подключения");
        alert.setHeaderText("Сервер не найден!");
        alert.setContentText("Вначале запустите сервер");
        alert.showAndWait();
    }

    private void openConnection() throws IOException {
        socket = new Socket(SERVER_IP, SERVER_PORT);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());

        addNewThread();
    }

    private void addNewThread() {
        new Thread(() -> {
            try {
                while (readyOfRead) {
                    System.out.println("Готовы считывать");
                    String strFromServer = inputStream.readUTF();
                    System.out.println("Считал " + strFromServer);
                    if (exitCondition(strFromServer)) break;
                    textArea.setText(textArea.getText() + strFromServer + "\n");
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }).start();
    }

    private boolean exitCondition(String strFromServer) {
        if (strFromServer.equalsIgnoreCase("/end")) {
            System.out.println("Сервер остановлен");
            System.exit(0);
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
            readyOfRead = false;
            outputStream.writeUTF("/end");
            socket.close();
            outputStream.close();
            inputStream.close();
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
        textArea.appendText("Я: " + textField.getText().trim() + "\n");
        try {
            outputStream.writeUTF(textField.getText().trim());
            textField.clear();
            textField.requestFocus();
        } catch (IOException exception) {
            exception.printStackTrace();
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
