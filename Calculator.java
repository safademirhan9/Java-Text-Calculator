package calculator_package;

import java.util.Locale;

public class Calculator {
    public static void main(String[] args) {
        LocalizationService localizationService = new LocalizationService();
        localizationService.setLocale(new Locale("tr", "TR"));
        
        CalculatorService calculatorService = new CalculatorService(localizationService);
        CalculatorUI calculatorUI = new CalculatorUI(calculatorService, localizationService);
    }
}
