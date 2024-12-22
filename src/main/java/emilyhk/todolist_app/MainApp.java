package emilyhk.todolist_app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        MainUI mainUI = new MainUI();
        Scene scene = new Scene(mainUI);
        stage.setTitle("TODO List");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}