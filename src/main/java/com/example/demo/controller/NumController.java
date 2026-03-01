package com.example.demo.controller;

import com.example.demo.model.Numeric;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NumController {
    @FXML
    private TextField numInput;
    @FXML
    private Label numInt;
    @FXML
    private Label numPercent;
    @FXML
    private TextField numDescription1;

    private Numeric num = new Numeric();

    @FXML
    protected void onConvertButtonClick() {
        String str = numInput.getText();
        num.readStr(str);
        numInt.setText(String.valueOf(num.getInt()));
        numPercent.setText(String.valueOf(num.getPersent()) + '%');
    }
}