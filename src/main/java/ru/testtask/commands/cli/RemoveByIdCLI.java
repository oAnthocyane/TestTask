package ru.testtask.commands.cli;

import ru.testtask.commands.Add;
import ru.testtask.commands.Command;
import ru.testtask.commands.RemoveById;
import ru.testtask.models.CollectionManager;
import ru.testtask.models.Entity;
import ru.testtask.validation.InputValidator;

import java.util.List;
import java.util.Scanner;

/**
 * The type Remove by id cli.
 * It is a {@link CommandCLI} for removing {@link Entity} using id {@link Entity} from command line.
 * For removing {@link Entity} command using {@link RemoveById} command.
 */
public class RemoveByIdCLI extends CommandCLI {

    private final Command<Integer> removeCommand = new RemoveById();

    /**
     * Instantiates a new Remove by id cli.
     *
     * @param scanner        the scanner used for user input
     * @param collectionName the collection name used for using specific collection in {@link ru.testtask.models.CollectionManager}
     */
    public RemoveByIdCLI(Scanner scanner, String collectionName) {
        super(scanner, collectionName);
    }
    /**
     * Method for remove Entity from a collection for command line.
     */
    @Override
    public void execute() {
        List<? extends Entity> entities = CollectionManager.getCollection().get(collectionName);
        if (entities.size() < 1) {
            System.out.println(
                    "There are no " + collectionName.substring(0, collectionName.length() - 1) + " in the list"
            );
        } else {
            String shortCollectionName = collectionName.substring(0, collectionName.length() - 1);
            System.out.println("List " + collectionName + ":");
            System.out.println(CollectionManager.getListString(collectionName));
            InputValidator inputValidator = new InputValidator(scanner);
            int id = inputValidator.validateIntInput("id " + shortCollectionName, 1, entities.size());
            id--;
            removeCommand.execute(collectionName, id);
            System.out.println(shortCollectionName + " with id " + ++id + " was deleted");
        }
    }

    @Override
    public String description() {
        return "remove a " + collectionName.substring(0, collectionName.length() - 1);
    }


}
