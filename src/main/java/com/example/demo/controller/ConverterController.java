package com.example.demo.controller;

import com.example.demo.model.ConverterModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConverterController {

    @FXML
    private TextField inputField;

    @FXML
    private ComboBox<String> fromComboBox;

    @FXML
    private ComboBox<String> toComboBox;

    @FXML
    private Label resultLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private ListView<String> historyList;  // список истории

    private ConverterModel model;
    private ObservableList<String> historyItems;

    @FXML
    private void initialize() {
        model = new ConverterModel();

        fromComboBox.getItems().addAll("Дробь", "Десятичная", "Процент");
        toComboBox.getItems().addAll("Дробь", "Десятичная", "Процент");

        fromComboBox.setValue("Десятичная");
        toComboBox.setValue("Процент");

        // создаем список для истории
        historyItems = FXCollections.observableArrayList();
        historyList.setItems(historyItems);

        // фильтр на ввод
        inputField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                return;
            }
            if (!newValue.matches("[0-9\\.,\\-/%]*")) {
                inputField.setText(oldValue);
            }
            if (newValue.chars().filter(ch -> ch == '-').count() > 1) {
                inputField.setText(oldValue);
            }
            if (newValue.chars().filter(ch -> ch == '/').count() > 1) {
                inputField.setText(oldValue);
            }
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

            // добавляем в историю
            String historyEntry = value + " " + from + " → " + result + " " + to;
            historyItems.add(0, historyEntry);

            // храним только последние 10 записей
            if (historyItems.size() > 10) {
                historyItems.remove(historyItems.size() - 1);
            }
        }
    }
}