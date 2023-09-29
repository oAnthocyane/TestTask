package ru.testtask.commands.cli;

import java.util.Scanner;

/**
 * The type Command cli.
 */
abstract public class CommandCLI {

    /**
     * The Scanner.
     */
    protected Scanner scanner;

    /**
     * The Collection name for {@link ru.testtask.models.CollectionManager}.
     */
    protected String collectionName;

    /**
     * Instantiates a new Command cli for simple commands that don`t require working with user input or collections.
     */
    public CommandCLI() {
    }

    /**
     * Instantiates a new Command cli for working with user input.
     *
     * @param scanner the scanner used for user input
     */
    public CommandCLI(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Instantiates a new Command cli for working with user input and specific collection.
     *
     * @param scanner        the scanner used for user input
     * @param collectionName the collection name for {@link ru.testtask.models.CollectionManager}
     */
    public CommandCLI(Scanner scanner, String collectionName) {
        this.scanner = scanner;
        this.collectionName = collectionName;
    }

    /**
     * Execute. Action to execute the main method of the class
     */
    abstract public void execute();

    /**
     * Command action description.
     *
     * @return the string
     */
    abstract public String description();
}
