package calculator_package;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationService {
    public ResourceBundle messages;
    
    public LocalizationService() {
    	setLocale(Locale.getDefault());
    }

    public void setLocale(Locale locale) {
        messages = ResourceBundle.getBundle("messages", locale);
    }

    public String getString(String key) {
        return messages.getString(key);
    }

    public String numberToText(int number) {
        String[] numbers = getNumbers();
        if (number >= 0 && number < numbers.length) {
            return numbers[number];
        }
        return Integer.toString(number);
    }

    public int textToNumber(String text) {
        String[] numbers = getNumbers();
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i].equalsIgnoreCase(text)) {
                return i;
            }
        }

        try {
            NumberFormat numberFormat = NumberFormat.getInstance(messages.getLocale());
            return numberFormat.parse(text).intValue();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid number format: " + text);
        }
    }

    public String[] getNumbers() {
        return messages.getString("numbers").split(",");
    }
    
    public int parseNumber(String numberText) {
        String[] words = numberText.split("\\s+");
        int number = 0;
        int partialSum = 0;

        for (String word : words) {
            int value = getValueForWord(word.toLowerCase());

            if (value >= 1000) {
                number += partialSum;
                number *= value;
                partialSum = 0;
            } else if (value >= 100) {
                partialSum *= value;
            } else {
                partialSum += value;
            }
        }

        return number + partialSum;
    }



    public int getValueForWord(String word) {
        String[] numbers = messages.getString("numbers").split(",");
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i].equalsIgnoreCase(word)) {
                return i;
            }
            if (numbers[i].contains(" ")) {
                String[] words = numbers[i].split(" ");
                if (words.length > 1 && words[1].equalsIgnoreCase(word)) {
                    return Integer.parseInt(words[0]) + getValueForWord(words[1]);
                }
            }
        }
        return -1;
    }

}