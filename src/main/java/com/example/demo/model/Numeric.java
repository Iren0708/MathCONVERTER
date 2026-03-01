package com.example.demo.model;

public class Numeric {
    private int rhs;
    private int lhs;

    public Numeric(){}

    public Numeric(String str) {
        int index = str.indexOf('/');
        lhs = Integer.parseInt(str.substring(0, index));
        rhs = Integer.parseInt(str.substring(index + 1));
    }

    public void readStr(String str) {
        int index = str.indexOf('/');
        lhs = Integer.parseInt(str.substring(0, index));
        rhs = Integer.parseInt(str.substring(index + 1));
    }

    public double getInt() {
        double res = (double) lhs / rhs;
        return res;
    }

    public int getPersent() {
        double res = (double) lhs / rhs;
        int percent = (int) (res * 100);
        return percent;
    }
}
