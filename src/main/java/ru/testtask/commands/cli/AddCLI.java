package ru.testtask.commands.cli;


import ru.testtask.commands.Add;
import ru.testtask.commands.Command;
import ru.testtask.models.Entity;
import ru.testtask.models.factory.EntityFactory;

import java.util.Scanner;

/**
 * The type Add cli.
 * It is a {@link CommandCLI} for add {@link Entity} using values from command line.
 * For adding {@link Entity} command using {@link Add} command and factory pattern to create a particular {@link Entity} {@link EntityFactory}.
 */
public class AddCLI extends CommandCLI {

    private final Command<Entity> addCommand = new Add();

    private final EntityFactory<? extends Entity> factory;

    /**
     * Instantiates a new Add cli.
     *
     * @param scanner        the scanner used for user input
     * @param collectionName the collection name used for using specific collection in {@link ru.testtask.models.CollectionManager}.
     * @param factory        the factory used for create a particular Entity {@link EntityFactory}.
     */
    public AddCLI(Scanner scanner, String collectionName,
                  EntityFactory<? extends Entity> factory) {
        super(scanner, collectionName);
        this.factory = factory;
    }

    /**
     * Method for add {@link Entity} to collection for command line.
     */
    @Override
    public void execute() {
        String shortName = collectionName.substring(0, collectionName.length() - 1);
        System.out.println("Adding a " + shortName + ":");
        Entity entity = factory.createEntity();
        entity.setFieldFromConsole(scanner);
        addCommand.execute(collectionName, entity);
        System.out.println("Successfully add a " + entity);
    }

    @Override
    public String description() {
        return "add a new " + collectionName.substring(0, collectionName.length() - 1);
    }
}
