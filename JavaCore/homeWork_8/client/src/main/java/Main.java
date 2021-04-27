import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        primaryStage.setTitle("Сетевой чат 1.0.1");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("chatGUI.fxml")));
        primaryStage.setScene(new Scene(root, 350, 700));
        primaryStage.show();
    }
}
