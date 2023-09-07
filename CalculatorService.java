package calculator_package;

import java.util.ResourceBundle;
import java.util.HashMap;
import java.util.Map;

public class CalculatorService {
	
	private LocalizationService localizationService;
    private Map<String, Integer> textToNumberMap;

    public CalculatorService(LocalizationService localizationService) {
        this.localizationService = localizationService;
        initializeTextToNumberMap();
    }
    
    private int parseTextNumber(String numberText) {
        // Convert the text-based number to lowercase for easier matching
        String text = numberText.toLowerCase();

        int result = 0;

        // Split the text into individual words
        String[] words = text.split(" ");

        // Create a mapping of text-based numbers to their corresponding numeric values
        Map<String, Integer> numberMap = new HashMap<>();
        numberMap.put("sıfır", 0);
        numberMap.put("bir", 1);
        numberMap.put("iki", 2);
        numberMap.put("üç", 3);
        numberMap.put("dört", 4);
        numberMap.put("beş", 5);
        numberMap.put("altı", 6);
        numberMap.put("yedi", 7);
        numberMap.put("sekiz", 8);
        numberMap.put("dokuz", 9);
        numberMap.put("on", 10);
        numberMap.put("on bir", 11);
        numberMap.put("on iki", 12);
        numberMap.put("on üç", 13);
        numberMap.put("on dört", 14);
        numberMap.put("on beş", 15);
        numberMap.put("on altı", 16);
        numberMap.put("on yedi", 17);
        numberMap.put("on sekiz", 18);
        numberMap.put("on dokuz", 19);
        numberMap.put("yirmi", 20);
        numberMap.put("otuz", 30);
        numberMap.put("kırk", 40);
        numberMap.put("elli", 50);
        numberMap.put("altmış", 60);
        numberMap.put("yetmiş", 70);
        numberMap.put("seksen", 80);
        numberMap.put("doksan", 90);
        numberMap.put("yüz", 100);
        numberMap.put("bin", 1000);
        numberMap.put("milyon", 1000000);
        numberMap.put("milyar", 1000000000);

        // Iterate through the words and calculate the numeric value
        int multiplier = 1; // Tracks the current multiplier (e.g., thousand, million)
        for (int i = words.length - 1; i >= 0; i--) {
            String word = words[i];

            if (numberMap.containsKey(word)) {
                int value = numberMap.get(word);

                if (value >= 1000) {
                    // Handle multipliers (e.g., thousand, million)
                    multiplier *= value;
                    result += multiplier;
                    multiplier = 1;
                } else if (value >= 100) {
                    // Handle hundreds
                    multiplier *= value;
                } else {
                    // Handle tens and ones
                    result += value * multiplier;
                    multiplier = 1;
                }
            } else {
                throw new IllegalArgumentException("Invalid number format: " + numberText);
            }
        }

        return result;
    }

//    
//    private int parseTextNumber(String numberText) {
//        // Convert the text-based number to lowercase for easier matching
//        String text = numberText.toLowerCase();
//
//        int result = 0;
//
//        // Split the text into individual words
//        String[] words = text.split(" ");
//
//        // Create a mapping of text-based numbers to their corresponding numeric values
//        Map<String, Integer> numberMap = new HashMap<>();
//        numberMap.put("zero", 0);
//        numberMap.put("one", 1);
//        numberMap.put("two", 2);
//        numberMap.put("three", 3);
//        numberMap.put("four", 4);
//        numberMap.put("five", 5);
//        numberMap.put("six", 6);
//        numberMap.put("seven", 7);
//        numberMap.put("eight", 8);
//        numberMap.put("nine", 9);
//        numberMap.put("ten", 10);
//        numberMap.put("eleven", 11);
//        numberMap.put("twelve", 12);
//        numberMap.put("thirteen", 13);
//        numberMap.put("fourteen", 14);
//        numberMap.put("fifteen", 15);
//        numberMap.put("sixteen", 16);
//        numberMap.put("seventeen", 17);
//        numberMap.put("eighteen", 18);
//        numberMap.put("nineteen", 19);
//        numberMap.put("twenty", 20);
//        numberMap.put("thirty", 30);
//        numberMap.put("forty", 40);
//        numberMap.put("fifty", 50);
//        numberMap.put("sixty", 60);
//        numberMap.put("seventy", 70);
//        numberMap.put("eighty", 80);
//        numberMap.put("ninety", 90);
//        numberMap.put("hundred", 100);
//        numberMap.put("thousand", 1000);
//        numberMap.put("million", 1000000);
//        numberMap.put("billion", 1000000000);
//
//        // Iterate through the words and calculate the numeric value
//        int multiplier = 1; // Tracks the current multiplier (e.g., thousand, million)
//        for (int i = words.length - 1; i >= 0; i--) {
//            String word = words[i];
//
//            if (numberMap.containsKey(word)) {
//                int value = numberMap.get(word);
//
//                if (value >= 1000) {
//                    // Handle multipliers (e.g., thousand, million)
//                    multiplier *= value;
//                    result += multiplier;
//                    multiplier = 1;
//                } else if (value >= 100) {
//                    // Handle hundreds
//                    multiplier *= value;
//                } else {
//                    // Handle tens and ones
//                    result += value * multiplier;
//                    multiplier = 1;
//                }
//            } else {
//                throw new IllegalArgumentException("Invalid number format: " + numberText);
//            }
//        }
//
//        return result;
//    }

    
    private void initializeTextToNumberMap() {
        textToNumberMap = new HashMap<>();
        textToNumberMap.put("zero", 0);
        textToNumberMap.put("one", 1);
        textToNumberMap.put("two", 2);
        textToNumberMap.put("three", 3);
        textToNumberMap.put("four", 4);
        textToNumberMap.put("five", 5);
        textToNumberMap.put("six", 6);
        textToNumberMap.put("seven", 7);
        textToNumberMap.put("eight", 8);
        textToNumberMap.put("nine", 9);
        // Add more mappings for numbers up to 99 if needed
    }
    
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }
    
    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Divisor cannot be zero!");
        }
        return a / b;
    }
    
    public String performOperation(String operation, String firstNumber, String secondNumber) {
        int num1 = 0;
        int num2 = 0;
        
        try {
            num1 = Integer.parseInt(firstNumber);
            num2 = Integer.parseInt(secondNumber);
        } catch (NumberFormatException e) {
            num1 = parseTextNumber(firstNumber);
            num2 = parseTextNumber(secondNumber);
        }

        // Perform the actual calculation based on the operation
        int result = 0;
        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }

        return String.valueOf(result);
    }


}