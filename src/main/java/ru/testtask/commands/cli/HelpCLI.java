package ru.testtask.commands.cli;

import java.util.Map;

/**
 * The type Help cli.
 * It is {@link CommandCLI} for help about all commands.
 */
public class HelpCLI extends CommandCLI {

    /**
     * Instantiates a new Help cli.
     */
    public HelpCLI() {
    }

    /**
     * Method for printing all description commands in cli.
     */
    @Override
    public void execute() {
        System.out.println(getHelpAboutCommand());
    }

    /**
     * Get help about command.
     *
     * @return the string. This string contains a description about all commands.
     */
    public static String getHelpAboutCommand(){
        Map<String, CommandCLI> commandCLIMap = CommandManagerCLI.getCommands();
        StringBuilder ans = new StringBuilder();
        for (String commandName : commandCLIMap.keySet()) {
            ans.append(commandName).append(": ").append(commandCLIMap.get(commandName).description())
                    .append("\n");
        }
        return ans.toString();
    }
    @Override
    public String description() {
        return "help about all commands";
    }
}
