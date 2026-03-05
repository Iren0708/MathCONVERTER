package com.example.demo.controller;

import com.example.demo.model.ConverterModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ConverterController {

    @FXML
    private TextField inputField;      // куда пишем

    @FXML
    private ComboBox<String> fromComboBox;  // откуда

    @FXML
    private ComboBox<String> toComboBox;    // куда

    @FXML
    private Label resultLabel;         // ответ

    @FXML
    private Label errorLabel;          // ошибка

    private ConverterModel model;

    @FXML
    private void initialize() {
        model = new ConverterModel();

        // заполняем списки
        fromComboBox.getItems().addAll("Дробь", "Десятичная", "Процент");
        toComboBox.getItems().addAll("Дробь", "Десятичная", "Процент");

        // что выбрано сразу
        fromComboBox.setValue("Десятичная");
        toComboBox.setValue("Процент");
    }

    @FXML
    private void handleConvert() {
        // убираем старую ошибку
        errorLabel.setText("");

        String value = inputField.getText();
        String from = fromComboBox.getValue();
        String to = toComboBox.getValue();

        // проверим что выбрано
        if (from == null || to == null) {
            errorLabel.setText("Выбери откуда и куда");
            return;
        }

        // считаем
        String result = model.convert(value, from, to);

        // показываем
        if (result.startsWith("Ошибка")) {
            errorLabel.setText(result);
            resultLabel.setText("");
        } else {
            resultLabel.setText(result);
        }
    }
}