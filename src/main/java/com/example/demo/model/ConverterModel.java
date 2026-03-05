package com.example.demo.model;

public class ConverterModel {

    public String convert(String value, String from, String to) {

        // проверка на пустое значение
        if (value == null || value.trim().equals("")) {
            return "Ошибка: пустое поле";
        }

        try {
            // переводим всё в десятичное число
            double number = 0;

            if (from.equals("Дробь")) {
                // парсим дробь вида 3/4
                if (!value.contains("/")) {
                    return "Ошибка: нужен слеш /";
                }
                String[] parts = value.split("/");
                double a = Double.parseDouble(parts[0].trim());
                double b = Double.parseDouble(parts[1].trim());

                if (b == 0) {
                    return "Ошибка: деление на ноль";
                }
                number = a / b;
            }

            else if (from.equals("Процент")) {
                // убираем значок %
                String s = value.replace("%", "").trim();
                number = Double.parseDouble(s) / 100;
            }

            else if (from.equals("Десятичная")) {
                number = Double.parseDouble(value);
            }

            // переводим в нужный формат
            String result = "";

            if (to.equals("Дробь")) {
                result = makeFraction(number);
            }

            else if (to.equals("Процент")) {
                double p = number * 100;
                p = Math.round(p * 100) / 100.0;
                result = p + "%";
            }

            else if (to.equals("Десятичная")) {
                number = Math.round(number * 10000) / 10000.0;
                result = String.valueOf(number);
            }

            return result;

        } catch (NumberFormatException e) {
            return "Ошибка: неправильное число";
        } catch (Exception e) {
            return "Ошибка: что-то не так";
        }
    }

    // делает из числа дробь
    private String makeFraction(double num) {
        if (num == (int) num) {
            return (int) num + "/1";
        }

        // считаем знаки после запятой
        String s = String.valueOf(num);
        int dots = s.length() - s.indexOf('.') - 1;
        if (dots < 0) dots = 0;

        int bottom = (int) Math.pow(10, dots);
        int top = (int) Math.round(num * bottom);

        // сокращаем
        int gcd = findGcd(top, bottom);
        top = top / gcd;
        bottom = bottom / gcd;

        return top + "/" + bottom;
    }

    // ищем общий делитель
    private int findGcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}