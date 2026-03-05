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

        fromComboBox.getItems().addAll("Дробь", "Десятичная", "Процент");
        toComboBox.getItems().addAll("Дробь", "Десятичная", "Процент");

        fromComboBox.setValue("Десятичная");
        toComboBox.setValue("Процент");

        // фильтр на ввод - только цифры, точка, запятая, минус, слеш, процент
        inputField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                return;
            }

            // разрешаем только нужные символы
            if (!newValue.matches("[0-9\\.,\\-/%]*")) {
                inputField.setText(oldValue);
            }

            // проверка на несколько минусов
            if (newValue.chars().filter(ch -> ch == '-').count() > 1) {
                inputField.setText(oldValue);
            }

            // проверка на несколько слешей
            if (newValue.chars().filter(ch -> ch == '/').count() > 1) {
                inputField.setText(oldValue);
            }

            // проверка на несколько процентов
            if (newValue.chars().filter(ch -> ch == '%').count() > 1) {
                inputField.setText(oldValue);
            }
        });
    }

    @FXML
    private void handleConvert() {
        errorLabel.setText("");

        String value = inputField.getText();
        String from = fromComboBox.getValue();
        String to = toComboBox.getValue();

        if (from == null || to == null) {
            errorLabel.setText("Выбери откуда и куда");
            return;
        }

        String result = model.convert(value, from, to);

        if (result.startsWith("Ошибка")) {
            errorLabel.setText(result);
            resultLabel.setText("");
        } else {
            resultLabel.setText(result);
        }
    }
}