package ru.testtask.validation;


import ru.testtask.commands.cli.CommandCLI;
import ru.testtask.commands.cli.HelpCLI;

import java.util.Scanner;
import java.util.Set;


/**
 * The type Input validator. Validating input values from the user in cli.
 */
public class InputValidator {

    private final Scanner scanner;

    /**
     * Instantiates a new Input validator.
     *
     * @param scanner the scanner used for user input
     */
    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Validate int input int. Checks if the value entered by the user is in the range from minValue to maxValue.
     * Checks if the value entered by the user is in the range of 1 to 10. Will prompt the user to enter numbers until the user enters the correct one.
     *
     * @param fieldName the field name
     * @param minValue  the min value
     * @param maxValue  the max value
     * @return the int - validating number.
     */
    public int validateIntInput(String fieldName, int minValue, int maxValue) {
        int input;
        do {
            System.out.print("Enter a " + fieldName + " value (from " + minValue + " to " + maxValue + "): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Please, enter a valid integer value " + fieldName + ".");
                scanner.next();
            }
            input = scanner.nextInt();
        } while (!isValidateInt(input, minValue, maxValue));
        scanner.nextLine(); // buffer dump
        return input;
    }

    /**
     * Is validate int boolean. If the input is in the range from minValue to maxValue return true, else false.
     *
     * @param input    the input
     * @param minValue the min value
     * @param maxValue the max value
     * @return the boolean - valid num or not.
     */
    public static boolean isValidateInt(int input, int minValue, int maxValue){
        return input >= minValue && input <= maxValue;
    }

    /**
     * Validate string command string. Checks if the value entered by the user is in availableValues.
     *
     * @param availableValues the available values
     * @param fieldName       the field name
     * @return the string - the valid string
     */
    public String validateStringCommand(Set<String> availableValues, String fieldName) {
        String input;
        do {
            System.out.println();
            System.out.println("Enter one of the possible " + fieldName);
            input = scanner.nextLine();
        } while (!availableValues.contains(input));
        return input;
    }
}
