package ru.testtask.exceptions;

/**
 * The type Not found command exception.
 * Exception that thrown when command not found in all commands.
 *
 * @see ru.testtask.commands.cli.CommandManagerCLI#getCommand(String)
 */
public class NotFoundCommandException extends IllegalArgumentException {
    /**
     * Instantiates a new Not found command exception.
     *
     * @param s the s
     */
    public NotFoundCommandException(String s) {
        super(s);
    }
}
