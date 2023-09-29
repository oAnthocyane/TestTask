package ru.testtask;

import ru.testtask.commands.cli.CommandCLI;
import ru.testtask.commands.cli.CommandManagerCLI;
import ru.testtask.commands.cli.HelpCLI;
import ru.testtask.models.CollectionManager;
import ru.testtask.models.Entity;
import ru.testtask.models.Monster;
import ru.testtask.models.Player;
import ru.testtask.validation.InputValidator;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The type Main. The main class in application.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        initStartEntity();
        System.out.println("Welcome to the game players vs monsters. " +
                "In this game you will play as players and you will have to heal them at a certain point and " +
                "attack monsters (attacking is done automatically)");
        Map<String, List<? extends Entity>> entities = CollectionManager.getCollection();
        System.out.println("Right now there are " + entities.get("players").size() + " players and " +
                entities.get("monsters").size() + " monsters available");
        System.out.println("What do you want to do next?");
        Scanner scanner = new Scanner(System.in);
        InputValidator inputValidator = new InputValidator(scanner);
        CommandManagerCLI commandManager = new CommandManagerCLI(scanner);

        String commands = "commands:\n" + HelpCLI.getHelpAboutCommand();
        String request;
        do {
            request = inputValidator.validateStringCommand(commandManager.getCommands().keySet(),  commands);
            System.out.println();
            CommandCLI command = commandManager.getCommand(request);
            command.execute();
            System.out.println();
            System.out.println("The command \"" + request + "\" has completed it`s execution");
        } while (!request.equals("exit"));
    }

    private static void initStartEntity(){
        Player player = new Player(1, 1, 100, 10);
        Monster monster = new Monster(1, 1, 10, 2);
        ((List<Player>) CollectionManager.getCollection().get("players")).add(player);
        ((List<Monster>) CollectionManager.getCollection().get("monsters")).add(monster);

    }
}