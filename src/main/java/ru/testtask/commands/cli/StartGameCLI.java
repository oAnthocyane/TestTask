package ru.testtask.commands.cli;

import ru.testtask.game.Battle;
import ru.testtask.models.CollectionManager;
import ru.testtask.models.FinalStatusBattle;
import ru.testtask.models.Monster;
import ru.testtask.models.Player;

import java.util.List;
import java.util.Scanner;

/**
 * The type Start game cli.
 * It is a {@link CommandCLI} for battle between list {@link Player} and list {@link Monster} for cli.
 * For start battle using {@link Battle} class.
 */
public class StartGameCLI extends CommandCLI {
    /**
     * Instantiates a new Start game cli.
     *
     * @param scanner the scanner used for user input in method {@linkplain Battle#start(Scanner)}
     */
    public StartGameCLI(Scanner scanner) {
        super(scanner);
    }

    /**
     * Method for start battle using {@link Battle} class and print a result battle. Print who won.
     */
    @Override
    public void execute() {
        Battle battle = new Battle(
                (List<Player>) CollectionManager.getCollection().get("players"),
                (List<Monster>) CollectionManager.getCollection().get("monsters")
        );
        FinalStatusBattle finalStatusBattle = battle.start(scanner);
        String ans;
        ans = switch (finalStatusBattle) {
            case DRAW -> "Nobody won, it`s draw";
            case MONSTER_WIN -> "You are lose. Monsters won";
            case PLAYER_WIN -> "You are won. Players won";
        };
        System.out.println();
        System.out.println("The game is ended. " + ans + ".");

    }

    @Override
    public String description() {
        return "start battle players vs monsters";
    }
}
