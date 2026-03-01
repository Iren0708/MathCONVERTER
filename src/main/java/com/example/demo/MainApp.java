package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends javafx.application.Application {  // ПОЛНОЕ ИМЯ

    @Override
    public void start(Stage window) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/demo/hello-view.fxml"));

        VBox root = loader.load();

        Scene scene = new Scene(root, 450, 350);

        window.setTitle("Конвертер чисел");
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}