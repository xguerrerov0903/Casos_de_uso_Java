package Utils;

import java.util.Optional;

import javax.swing.JOptionPane;
public class Inputs {
    private final static String DEFAULT_INVALID_INPUT_MESSAGE = "Invalid input. Please try again.";
    private final static String DEFAULT_INVALID_INPUT_PANE_TITLE = "Invalid Input";

    // String
    public static String requestString(String prompt) {
        return requestString(prompt, DEFAULT_INVALID_INPUT_MESSAGE, false);
    }

    public static String requestString(String prompt, boolean allowEmpty) {
        return requestString(prompt, DEFAULT_INVALID_INPUT_MESSAGE, allowEmpty);
    }

    public static String requestString(String prompt, String invalidInputMessage, boolean allowEmpty) {
        while (true) {
            String inputString = JOptionPane.showInputDialog(null, prompt);
            String trimmedInput = inputString == null ? "" : inputString.trim();
            if (allowEmpty)
                return trimmedInput;

            if (!trimmedInput.isEmpty())
                return trimmedInput;

            JOptionPane.showMessageDialog(null, invalidInputMessage, DEFAULT_INVALID_INPUT_PANE_TITLE,
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // Integer
    public static int requestInteger(String prompt, String invalidInputMessage, boolean allowEmpty, int defaultValue) {
        while (true) {
            String inputInteger = requestString(prompt, allowEmpty);
            if (allowEmpty && (inputInteger == null || inputInteger.isEmpty())) {
                return defaultValue; // Devuelve el valor por defecto si se permite vacío
            }

            try {
                if (Integer.parseInt(inputInteger)<0){
                    throw new NumberFormatException();
                }
                return Integer.parseInt(inputInteger); // Convierte y retorna el valor ingresado
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, invalidInputMessage, "Invalid Input",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static int requestInteger(String prompt, String invalidInputMessage, int defaultValue) {
        return requestInteger(prompt, invalidInputMessage, false, defaultValue);
    }

    public static int requestInteger(String prompt, int defaultValue) {
        return requestInteger(prompt, "The input is not a valid integer. Please try again.", false, defaultValue);
    }

    public static int requestInteger(String prompt) {
        return requestInteger(prompt, "The input is not a valid integer. Please try again.", false, 0);
    }

    // Double
    public static Optional<Double> requestDouble(String prompt, String invalidInputMessage) {
        return requestDouble(prompt, invalidInputMessage, false);
    }

    public static Optional<Double> requestDouble(String prompt, boolean allowEmpty) {
        return requestDouble(prompt, "The input is not a valid number. Please try again.", allowEmpty);
    }

    public static Optional<Double> requestDouble(String prompt) {
        return requestDouble(prompt, "The input is not a valid number. Please try again.", false);
    }

    public static Optional<Double> requestDouble(String prompt, String invalidInputMessage, boolean allowEmpty) {
        while (true) {
            String inputDouble = requestString(prompt, allowEmpty);
            if (allowEmpty && inputDouble.isEmpty())
                return Optional.empty();

            try {
                if (Double.parseDouble(inputDouble)<0){
                    throw new NumberFormatException();
                }
                return Optional.of(Double.parseDouble(inputDouble));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, invalidInputMessage, DEFAULT_INVALID_INPUT_PANE_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

}
