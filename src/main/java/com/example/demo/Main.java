package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage window) throws Exception {
        // грузим окошко
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/demo/ConverterView.fxml"));

        VBox root = loader.load();

        Scene scene = new Scene(root, 400, 280);

        window.setTitle("Конвертер");
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}