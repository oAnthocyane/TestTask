package ru.testtask.commands.cli;

/**
 * The type Exit cli.
 * It is {@link CommandCLI} to exit the application.
 */
public class ExitCLI extends CommandCLI {
    /**
     * Instantiates a new Exit cli.
     */
    public ExitCLI() {
        super();
    }

    /**
     * Method to exit the application.
     */
    @Override
    public void execute() {
        System.out.println("Good Bye!");
        System.exit(0);
    }

    @Override
    public String description() {
        return "exit from application";
    }
}
