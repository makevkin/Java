import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите выражение типа: 2+3 или VII/II. Числа могут быть от 1 до 10 включительно");
        String input = scan.nextLine();

        String output = calc(input);
        outputResult(output);
    }
    public static String calc(String input) {
        Set<String> arab = Set.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        Set<String> rim = Set.of("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");
        String[] operands = new String[]{"+", "-", "*", "/"};
        String operation = "";

        // определяем математический знак в выражении
        for (int i = 0; i < 4; i++) {
            int index = input.indexOf(operands[i]);
            if (index != -1) {
                operation = operands[i];
                break;
            }
        }
        if (operation.isEmpty()) 
            exp(1);

        String[] result = parsing(input, "\\" + operation);  // разделение строки на элементы

        // Проверка на арабские цифры
        boolean cc = arab.contains(result[0]);
        boolean cc1 = arab.contains(result[1]);

        // Проверка на римские цифры
        boolean dd = rim.contains(result[0]);
        boolean dd1 = rim.contains(result[1]);

        // Проверка систем исчисления и выполнение математичеких операций
        Integer[] numeral = new Integer[2];
        int result1;
        String result2 = null;
        if (cc1 && cc) {  // Арабские цифры
            numeral = stringToInt(result);
            result1 = mathOperations(operation, numeral);
            result2=String.valueOf(result1);
        }
        else if (dd1 && dd) {   // Римские цифры
            numeral[0] = romanToNumber(result[0]);
            numeral[1] = romanToNumber(result[1]);
            if (numeral[0] <= numeral[1] && operation.equals("-") | operation.equals("/"))
                exp(4);
            else {
                result1 = mathOperations(operation, numeral);
                result2 = numberToRoman(result1);
            }
        }
        else
            exp(5);
        return result2;
    }


    // метод разделения строки на элементы
    public static String[] parsing(String world, String aa) {
        String input = world.replaceAll("\\s", "");
        String[] pars = input.split(aa);
        if (pars.length!=2)
            exp(1);
        return pars;
    }
    public static Integer[] stringToInt (String[] str) {
        int num = Integer.parseInt (str[0]);
        int num1 = Integer.parseInt (str[1]);
        return new Integer[]{num,num1};
    }
    // Метод проведения математических операций
    public static int mathOperations(String bb, Integer [] num) {
        int res=0;
        switch (bb){
            case "+":
                res = num[0]+num[1];
                break;
            case "-":
                res = num[0]-num[1];
                break;
            case "*":
                res = num[0]*num[1];
                break;
            case "/":
                if (num[1] !=0)
                    res = num[0]/num[1];
                else
                    exp(6);
                break;
        }
        return res;
    }
    // Перевод римских цифр в арабские
    public static int romanToNumber (String rom) {
        switch (rom) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
        }
        return 0;
    }
    // Преобразование арабских чисел в римские
    public static String numberToRoman (int number) {
        String[] roman = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C" };
        return roman[number-1];
    }
    public static void outputResult(String result){
        System.out.println("результат " + result);
    }
    public static void exp(int i){
        try{
            throw new IOException();
        } catch (IOException e) {
            switch (i) {
                case 1:
                    System.out.println("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                    break;
                case 2:
                    System.out.println("Строка не является математической операцией");
                    break;
                case 3:
                    System.out.println("Используются разные системы исчесления");
                    break;
                case 4:
                    System.out.println("В римской системе не может быть значений меньше I");
                    break;
                case 5:
                    System.out.println("Неверный формат данных");
                    break;
                case 6:
                    System.out.println("Деление на ноль не возможно");
                    break;
            }
            System.exit(0);
        }
    }
}