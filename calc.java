package Test;


import java.util.*;
import java.util.stream.Collectors;

public class Calc2 {
    static String[] array;
    static int result = 0;
    static String answer;


    public static void main(String[] args) throws Exception {

        while (true) {
            Scanner scanner = new Scanner(System.in);


            String expression = scanner.nextLine();

            String readyExpression = expression.trim().replaceAll("\\s", "");
            if (readyExpression.contains(",") || readyExpression.contains(".")) {
                throw new Exception("ОШИБКА ВВОДА: калькуоятор не работает с десятичными лробями!");
            } else {
                System.out.println(showResult(readyExpression));
            }
        }
    }

    static String operation(String firstnum, String secondnum, char sign) throws Exception {
        firstnum = array[0];
        secondnum = array[1];
        if (isDigit(firstnum) && isDigit(secondnum)) {
            int pervoeChislo = Integer.parseInt(firstnum);
            int vtoroeChislo = Integer.parseInt(secondnum);

            if (pervoeChislo > 0 && pervoeChislo <= 10 && vtoroeChislo > 0 && vtoroeChislo <= 10) {
                switch (sign) {
                    case '+' -> {
                        result = pervoeChislo + vtoroeChislo;
                        return String.valueOf(result);
                    }
                    case '-' -> {
                        result = pervoeChislo - vtoroeChislo;
                        return String.valueOf(result);
                    }
                    case '*' -> {
                        result = vtoroeChislo * pervoeChislo;
                        return String.valueOf(result);
                    }
                    case '/' -> {
                        result = pervoeChislo / vtoroeChislo;
                        return String.valueOf(result);
                    }
                    default -> throw new Exception("ОШИБКА ВВОДА: используйте только операнды <+>, <->, <*>");
                }

            } else {
                throw new Exception("ОШИБКА ВВОДА: используйте цифры в диапазоне от 0 до 10 включительно!");
            }
        } else {

            if (isDigit(String.valueOf(romanToArabic(firstnum))) && isDigit(String.valueOf(romanToArabic(secondnum)))) {
                int a = romanToArabic(firstnum);
                int b = romanToArabic(secondnum);

                if ((a > 0 && a <= 10) && (b > 0 && b <= 10)) {
                    switch (sign) {
                        case '+' -> {
                            result = a + b;
                            answer = arabicToRoman(result);
                        }
                        case '-' -> {
                            result = a - b;
                            if (result == 0) {
                                throw new Exception("ОШИБКА: в римсокй системе не существует нуля!");
                            } else if (result < 0) {
                                throw new Exception("ОШИБКА: в римсокй системе не существует отрицательных чисел!");
                            } else {
                                answer = arabicToRoman(result);
                            }
                        }
                        case '*' -> {
                            result = a * b;
                            answer = arabicToRoman(result);
                        }
                        case '/' -> {
                            result = a / b;
                            answer = arabicToRoman(result);
                        }
                        default -> throw new Exception("ОШИБКА ВВОДА: используйте только операнды <+>, <->, <*>");
                    }

                } else {
                    throw new Exception("ОШИБКА ВВОДА: использовано недопустимое значение! Калькулятор работает ТОЛЬКО с римскими и арабскими" +
                            " цифрами от 1 до 10 включительно!");
                }
            }

        }
        return answer;
    }


    public static String showResult(String line) throws Exception {
        String end;

        if (line.contains("+") && line.indexOf('+') > 0) {
            array = line.split("[+]");
            if (array.length > 2) {
                throw new Exception("ОШИБКА ВВОДА: калькулятор принимает ТОЛЬКО 2 операнды и 1 оператор!");
            } else {
                if ((isDigit(array[0]) && !isDigit(array[1])) || (!isDigit(array[0]) && isDigit(array[1]))) {
                    throw new Exception("ОШИБКА ВВОДА: использованы разные сисетмы чисел!");
                } else {

                  end = operation(array[0], array[1], '+');
                }
            }
        } else if (line.contains("-") && line.indexOf('-') > 0) {
            array = line.split("[-]");
            if (array.length > 2) {
                throw new Exception("ERROR");
            } else {

                if ((isDigit(array[0]) && !isDigit(array[1])) || (!isDigit(array[0]) && isDigit(array[1]))) {
                    throw new Exception("ОШИБКА ВВОДА: использованы разные сисетмы чисел!");
                } else {
                    end = operation(array[0], array[1], '-');
                }
            }
        } else if (line.contains("*") && line.indexOf('*') > 0) {
            array = line.split("[*]");
            if (array.length > 2) {
                throw new Exception("ERROR");
            } else {

                if ((isDigit(array[0]) && !isDigit(array[1])) || (!isDigit(array[0]) && isDigit(array[1]))) {
                    throw new Exception("ОШИБКА ВВОДА: использованы разные сисетмы чисел!");
                } else {
                    end = operation(array[0], array[1], '*');
                }
            }
        } else if (line.contains("/") && line.indexOf('/') > 0) {
            array = line.split("[/]");
            if (array.length > 2) {
                throw new Exception("ERROR");
            } else {
                if ((isDigit(array[0]) && !isDigit(array[1])) || (!isDigit(array[0]) && isDigit(array[1]))) {
                    throw new Exception("ОШИБКА ВВОДА: использованы разные сисетмы чисел!");
                } else {
                    end = operation(array[0], array[1], '/');
                }
            }
        } else {
            throw new Exception("ОШИБКА ВВОДА: неверный оператор! Используйте только < + >, < - >, < * >, < / >");
        }

        return end;
    }


    public static int romanToArabic(String input) throws Exception {
        if (input.equals("IIII") || input.equals("IIIII") || input.equals("IIIIII") || input.equals("IIIIIII")
                || input.equals("IIIIIIII") || input.equals("IIIIIIIII") || input.equals("IIIIIIIIII") || input.equals("VIIII")
                || input.equals("VIIIII") || input.equals("iiii") || input.equals("iiiii") || input.equals("iiiiii")
                || input.equals("iiiiiii") || input.equals("iiiiiiii") || input.equals("iiiiiiiii") || input.equals("iiiiiiiiii")
                || input.equals("viiii") || input.equals("viiiii")) {
            throw new Exception("ОШИБКА ВВОДА: неверная римская цифра");
        } else {
            String romanNumeral = input.toUpperCase();
            int result = 0;

            List<RomanNum> romanNumerals = RomanNum.getReverseSortedValues();

            int i = 0;

            while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
                RomanNum symbol = romanNumerals.get(i);
                if (romanNumeral.startsWith(symbol.name())) {
                    result += symbol.getValue();
                    romanNumeral = romanNumeral.substring(symbol.name().length());
                } else {
                    i++;
                }
            }

            if (romanNumeral.length() > 0) {
                throw new Exception("ОШИБКА: Введенного значение не сущствует как в римской, так и в арабской системе цифр!");
            }

            return result;
        }
    }


    public static String arabicToRoman(int number) {
        List<RomanNum> romanNumerals = RomanNum.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNum currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }


    enum RomanNum {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100);

        private final int value;

        RomanNum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNum> getReverseSortedValues() {
            return Arrays.stream(values()).sorted(Comparator.comparing((RomanNum e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }
}

