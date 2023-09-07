package calculator_package;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorUI {
    private final CalculatorService calculatorService;
    private final LocalizationService localizationService;

    private JFrame frame;
    private JTextField firstNumberField;
    private JTextField secondNumberField;
    private JComboBox<String> operationComboBox;
    private JButton calculateButton;
    private JLabel resultLabel;
    
    public CalculatorUI(CalculatorService calculatorService, LocalizationService localizationService) {
        this.calculatorService = calculatorService;
        this.localizationService = localizationService;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle(localizationService.getString("calculator_title"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel firstNumberLabel = new JLabel(localizationService.getString("first_number_label"));
        panel.add(firstNumberLabel);

        firstNumberField = new JTextField();
        panel.add(firstNumberField);

        JLabel secondNumberLabel = new JLabel(localizationService.getString("second_number_label"));
        panel.add(secondNumberLabel);

        secondNumberField = new JTextField();
        panel.add(secondNumberField);

        JLabel operationLabel = new JLabel(localizationService.getString("operation_label"));
        panel.add(operationLabel);

        operationComboBox = new JComboBox<>(new String[]{"+", "-", "*", "/"});
        panel.add(operationComboBox);

        calculateButton = new JButton(localizationService.getString("calculate_button"));
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstNumber = firstNumberField.getText();
                String secondNumber = secondNumberField.getText();
                String operation = (String) operationComboBox.getSelectedItem();
                String result = calculatorService.performOperation(operation, firstNumber, secondNumber);
                resultLabel.setText(result);
            }
        });
        panel.add(calculateButton);

        resultLabel = new JLabel();
        panel.add(resultLabel);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void run() {
        JFrame frame = new JFrame("Hesap Makinesi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2));

        JLabel firstNumberLabel = new JLabel(localizationService.getString("first_number"));
        JComboBox<String> firstNumberComboBox = new JComboBox<>(localizationService.getNumbers());
        JLabel secondNumberLabel = new JLabel(localizationService.getString("second_number"));
        JComboBox<String> secondNumberComboBox = new JComboBox<>(localizationService.getNumbers());
        JLabel resultLabel = new JLabel(localizationService.getString("result"));
        JTextField resultField = new JTextField();
        resultField.setEditable(false);

        String[] operations = { "add", "subtract", "multiply", "divide" };

        JButton addButton = new JButton(localizationService.getString("add_button"));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstNumber = (String) firstNumberComboBox.getSelectedItem();
                String secondNumber = (String) secondNumberComboBox.getSelectedItem();
                String operation = operations[0]; // add
                String result = calculatorService.performOperation(operation, firstNumber, secondNumber);
                resultField.setText(result);
            }
        });

        JButton subtractButton = new JButton(localizationService.getString("subtract_button"));
        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstNumber = (String) firstNumberComboBox.getSelectedItem();
                String secondNumber = (String) secondNumberComboBox.getSelectedItem();
                String operation = operations[1]; // subtract
                String result = calculatorService.performOperation(operation, firstNumber, secondNumber);
                resultField.setText(result);
            }
        });

        JButton multiplyButton = new JButton(localizationService.getString("multiply_button"));
        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstNumber = (String) firstNumberComboBox.getSelectedItem();
                String secondNumber = (String) secondNumberComboBox.getSelectedItem();
                String operation = operations[2]; // multiply
                String result = calculatorService.performOperation(operation, firstNumber, secondNumber);
                resultField.setText(result);
            }
        });

        JButton divideButton = new JButton(localizationService.getString("divide_button"));
        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstNumber = (String) firstNumberComboBox.getSelectedItem();
                String secondNumber = (String) secondNumberComboBox.getSelectedItem();
                String operation = operations[3]; // divide
                String result = calculatorService.performOperation(operation, firstNumber, secondNumber);
                resultField.setText(result);
            }
        });

        frame.add(firstNumberLabel);
        frame.add(firstNumberComboBox);
        frame.add(secondNumberLabel);
        frame.add(secondNumberComboBox);
        frame.add(resultLabel);
        frame.add(resultField);
        frame.add(addButton);
        frame.add(subtractButton);
        frame.add(multiplyButton);
        frame.add(divideButton);

        frame.pack();
        frame.setVisible(true);
    }
    
}
