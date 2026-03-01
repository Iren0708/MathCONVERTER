package com.example.demo.controller;

import com.example.demo.model.ConverterModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ConverterController {

    // Связываем с элементами из FXML
    @FXML
    private TextField inputField;      // поле ввода

    @FXML
    private ComboBox<String> fromComboBox;  // список "Из"

    @FXML
    private ComboBox<String> toComboBox;    // список "В"

    @FXML
    private Label resultLabel;         // результат

    @FXML
    private Label errorLabel;          // ошибки

    @FXML
    private Button convertButton;      // кнопка конвертации

    @FXML
    private Button swapButton;         // кнопка поменять местами

    private ConverterModel model;      // наша модель

    // Этот метод вызывается автоматически при запуске
    @FXML
    private void initialize() {
        // Создаем модель
        model = new ConverterModel();

        // Добавляем элементы в списки
        fromComboBox.getItems().addAll("Дробь", "Десятичная", "Процент");
        toComboBox.getItems().addAll("Дробь", "Десятичная", "Процент");

        // Выбираем значения по умолчанию
        fromComboBox.setValue("Десятичная");
        toComboBox.setValue("Процент");

        // Подсказка в поле ввода
        inputField.setPromptText("Введите число");
    }

    // Обработчик кнопки "Конвертировать"
    @FXML
    private void handleConvert() {
        // Очищаем сообщение об ошибке
        errorLabel.setText("");

        // Получаем данные из полей
        String value = inputField.getText();
        String from = fromComboBox.getValue();
        String to = toComboBox.getValue();

        // Проверяем, выбраны ли единицы измерения
        if (from == null || to == null) {
            errorLabel.setText("Выберите единицы измерения");
            return;
        }

        // Вызываем модель и получаем результат
        String result = model.convert(value, from, to);

        // Если результат начинается с "Ошибка", показываем ошибку
        if (result.startsWith("Ошибка")) {
            errorLabel.setText(result);
            resultLabel.setText("");
        } else {
            resultLabel.setText(result);
        }
    }

    // Обработчик кнопки "Поменять местами"
    @FXML
    private void handleSwap() {
        String temp = fromComboBox.getValue();
        fromComboBox.setValue(toComboBox.getValue());
        toComboBox.setValue(temp);
    }

    // Обработчик кнопки "Очистить" (добавим потом в интерфейс)
    @FXML
    private void handleClear() {
        inputField.clear();
        resultLabel.setText("");
        errorLabel.setText("");
    }
}