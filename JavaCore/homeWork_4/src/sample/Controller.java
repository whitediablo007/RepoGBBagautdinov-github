package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {

    @FXML
    private TextField textField;

    @FXML
    private TextArea textArea;

    @FXML
    public void clickSend() {
        if (!textField.getText().equals("")) {
            textImport();
        }
    }

    @FXML
    public void keyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER && !textField.getText().equals("")) {
            textImport();
        }
    }

    @FXML
    private void textImport() {
        textArea.appendText(textField.getText() + "\n");
        textField.clear();
        textField.requestFocus();
    }
}
