package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Klasse som blir brukt til å kjøre appen
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("2048");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("2048.fxml"))));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
           Application.launch(args);
    }
    
}
