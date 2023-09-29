package ru.testtask.exceptions;

/**
 * The type Deficient count health exception.
 * Exception that thrown when count health less than the acceptable value
 * For example @see ru.testtask.models.Player#heal
 */
public class DeficientCountHealthException extends IllegalArgumentException {
    /**
     * Instantiates a new Deficient count health exception.
     *
     * @param s the string - message for thrown exception.
     */
    public DeficientCountHealthException(String s) {
        super(s);
    }
}
