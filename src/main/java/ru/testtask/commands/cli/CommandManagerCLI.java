package ru.testtask.commands.cli;

import lombok.Getter;
import ru.testtask.exceptions.NotFoundCommandException;
import ru.testtask.models.factory.MonsterFactory;
import ru.testtask.models.factory.PlayerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The type Command manager cli.
 * Main class for commands that store all {@link CommandCLI} and outputs them by a specific key.
 */
public class CommandManagerCLI {

    @Getter
    private static final Map<String, CommandCLI> commands = new HashMap<>();


    /**
     * Instantiates a new Command manager cli.
     *
     * @param scanner the scanner used for user input
     */
    public CommandManagerCLI(Scanner scanner) {
        init(scanner);
    }



    private void init(Scanner scanner) {
        commands.put("add_monster", new AddCLI(scanner, "monsters", new MonsterFactory()));
        commands.put("add_player", new AddCLI(scanner, "players", new PlayerFactory()));
        commands.put("remove_monster", new RemoveByIdCLI(scanner, "monsters"));
        commands.put("remove_player", new RemoveByIdCLI(scanner, "players"));
        commands.put("show_monsters", new ShowCLI(scanner, "monsters"));
        commands.put("show_players", new ShowCLI(scanner, "players"));
        commands.put("exit", new ExitCLI());
        commands.put("start", new StartGameCLI(scanner));
        commands.put("help", new HelpCLI());
    }

    /**
     * Gets command.
     *
     * @param commandName the command name used as key for gets command.
     * @return the {@link CommandCLI}.
     * @throws NotFoundCommandException the not found command exception, throws when the key does not belong in any of the commands in the collection
     */
    public CommandCLI getCommand(String commandName) throws NotFoundCommandException {
        CommandCLI commandCLI = commands.get(commandName);
        if (commandCLI == null) throw new NotFoundCommandException("This command is not exist");
        return commandCLI;
    }

}
