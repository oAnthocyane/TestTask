package ru.testtask.commands.cli;

import ru.testtask.models.CollectionManager;
import ru.testtask.models.Entity;

import java.util.Scanner;

/**
 * The type Show cli.
 * It is a {@link CommandCLI} for show all {@link Entity} from specific collection using command line.
 */
public class ShowCLI extends CommandCLI {
    /**
     * Instantiates a new Show cli.
     *
     * @param scanner        the scanner
     * @param collectionName the collection name
     */
    public ShowCLI(Scanner scanner, String collectionName) {
        super(scanner, collectionName);
    }

    /**
     * Method for show all {@link Entity} from collection by collection name using command line.
     */
    @Override
    public void execute() {
        String ans = CollectionManager.getListString(collectionName);
        if (ans.equals(""))
            System.out.println(
                    "There are no " + collectionName + " in the list"
            );
        else {
            System.out.println("All " + collectionName + " in list:");
            System.out.println(ans);

        }
    }

    @Override
    public String description() {
        return "show list " + collectionName.substring(0, collectionName.length() - 1);
    }
}
