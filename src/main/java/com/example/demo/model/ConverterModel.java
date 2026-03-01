package com.example.demo.model;

public class ConverterModel {

    // Метод для конвертации
    public String convert(String value, String from, String to) {

        // Проверка на пустой ввод
        if (value == null || value.trim().equals("")) {
            return "Ошибка: введите число";
        }

        try {
            // Сначала переводим всё в десятичное число
            double number = 0;

            // ЕСЛИ выбрали ДРОБЬ (например: 3/4)
            if (from.equals("Дробь")) {
                if (!value.contains("/")) {
                    return "Ошибка: дробь должна быть вида 3/4";
                }
                String[] parts = value.split("/");
                double chislitel = Double.parseDouble(parts[0].trim());
                double znamenatel = Double.parseDouble(parts[1].trim());

                if (znamenatel == 0) {
                    return "Ошибка: нельзя делить на ноль";
                }
                number = chislitel / znamenatel;
            }

            // ЕСЛИ выбрали ПРОЦЕНТ (например: 75%)
            else if (from.equals("Процент")) {
                String s = value.replace("%", "").trim();
                number = Double.parseDouble(s) / 100;
            }

            // ЕСЛИ выбрали ДЕСЯТИЧНУЮ (например: 0.75 или 10.2)
            else if (from.equals("Десятичная")) {
                number = Double.parseDouble(value);
            }

            // Теперь переводим число в нужный формат
            String result = "";

            // ЕСЛИ надо в ДРОБЬ
            if (to.equals("Дробь")) {
                result = decimalToFraction(number);
            }

            // ЕСЛИ надо в ПРОЦЕНТ
            else if (to.equals("Процент")) {
                double percent = number * 100;
                // Округляем до 2 знаков
                percent = Math.round(percent * 100) / 100.0;
                result = percent + "%";
            }

            // ЕСЛИ надо в ДЕСЯТИЧНУЮ
            else if (to.equals("Десятичная")) {
                // Округляем до 4 знаков
                number = Math.round(number * 10000) / 10000.0;
                result = String.valueOf(number);
            }

            return result;

        } catch (NumberFormatException e) {
            return "Ошибка: неправильный формат числа";
        } catch (Exception e) {
            return "Ошибка: что-то пошло не так";
        }
    }

    // Метод для перевода десятичного числа в дробь
    private String decimalToFraction(double number) {
        // Проверка на целое число
        if (number == (int) number) {
            return (int) number + "/1";
        }

        // Для чисел с десятичной частью
        // Убираем знак минус если есть
        boolean isNegative = false;
        if (number < 0) {
            isNegative = true;
            number = -number;
        }

        // Получаем количество знаков после запятой
        String numStr = String.valueOf(number);
        int decimalPlaces = numStr.length() - numStr.indexOf('.') - 1;

        // Если точка не найдена
        if (decimalPlaces < 0) {
            decimalPlaces = 0;
        }

        // Создаем дробь: умножаем на 10^decimalPlaces
        int znamenatel = (int) Math.pow(10, decimalPlaces);
        int chislitel = (int) Math.round(number * znamenatel);

        // Сокращаем дробь
        int gcd = findGCD(chislitel, znamenatel);
        chislitel = chislitel / gcd;
        znamenatel = znamenatel / gcd;

        // Добавляем знак минус если нужно
        if (isNegative) {
            chislitel = -chislitel;
        }

        return chislitel + "/" + znamenatel;
    }

    // Находим наибольший общий делитель (НОД)
    private int findGCD(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}