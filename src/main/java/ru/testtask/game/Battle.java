package ru.testtask.game;

import ru.testtask.models.Entity;
import ru.testtask.models.FinalStatusBattle;
import ru.testtask.models.Monster;
import ru.testtask.models.Player;
import ru.testtask.utils.Random;
import ru.testtask.validation.InputValidator;

import java.util.*;

/**
 * The type Battle.
 * The main class for battle between players and monsters.
 */
public class Battle {

    private final List<Player> availablePlayers;

    private final List<Monster> availableMonsters;

    /**
     * Instantiates a new Battle.
     *
     * @param availablePlayers  the available players in battle
     * @param availableMonsters the available monsters in battle
     */
    public Battle(List<Player> availablePlayers, List<Monster> availableMonsters) {
        this.availablePlayers = new ArrayList<>(availablePlayers);
        this.availableMonsters = new ArrayList<>(availableMonsters);
    }

    /**
     * Start battle.
     * The logic of the game is as follows: it is a turn-based battle, first goes one of the players, which is selected randomly, attacks a monster, which is also selected randomly, then the user playing for the players are given the option to either heal the player just attacked, or not to heal.
     * A random monster is then selected and attacks a random player. Any entity lives as long as its health is greater than 0. One turn lasts until all players or monsters have gone down.
     * After that, the next turn begins and it starts all over again.
     * The game lasts until all monsters or players on the available list are removed.
     *
     * @param scanner the scanner used for user input.
     * @return the final status battle {@link FinalStatusBattle}.
     */
    public FinalStatusBattle start(Scanner scanner){
        deleteDiedEntity(availablePlayers);
        deleteDiedEntity(availableMonsters);
        while (!availablePlayers.isEmpty() && !availableMonsters.isEmpty()) {
            List<Player> hasTurnPlayers = new ArrayList<>(availablePlayers);
            List<Monster> hasTurnMonsters = new ArrayList<>(availableMonsters);


            while (!availablePlayers.isEmpty() && !availableMonsters.isEmpty() &&
                    (!hasTurnPlayers.isEmpty() || !hasTurnMonsters.isEmpty())) {
                Player playerAttack = attackEntityOnEntity(hasTurnPlayers, "Player", hasTurnMonsters,
                        availableMonsters);
                if(playerAttack != null && !availableMonsters.isEmpty()) healPlayer(playerAttack, scanner);

                attackEntityOnEntity(hasTurnMonsters, "Monster", hasTurnPlayers, availablePlayers);
            }
        }
        if(availableMonsters.isEmpty() && availablePlayers.isEmpty()) return FinalStatusBattle.DRAW;
        else return availableMonsters.isEmpty() ? FinalStatusBattle.PLAYER_WIN : FinalStatusBattle.MONSTER_WIN;
        }

    /**
     * The method for attack {@link Entity} on {@link Entity} using method {@linkplain Entity#attack(Entity)}.
     * In this method the attacking entity is chosen randomly from attackEntities, it attacks another entity, which is also chosen randomly from defensiveEntities.
     * @param attackEntities used as list for chosen random {@link Entity} for attack.
     * @param nameAttackEntity used for printing information about whose move.
     * @param defensiveTurnEntities used for deleting {@link Entity} in hasTurnEntities.
     * @param defensiveEntities used as list for chosen random {@link Entity} for protection.
     * @return T extends {@link Entity} the {@link Entity}. Used to get the selected player, to heal him in the {@linkplain #healPlayer(Player, Scanner)} method.
     * @param <T> - generic for get particular class ({@link Player}.
     */

        private <T extends Entity> T attackEntityOnEntity(List<T> attackEntities, String nameAttackEntity,
                                          List<? extends Entity> defensiveTurnEntities, List<? extends Entity> defensiveEntities){
            if(!attackEntities.isEmpty() && !defensiveEntities.isEmpty()){
                System.out.println(nameAttackEntity + "`s turn.");
                int attackEntityNum = Random.generateRandomNum(0, attackEntities.size());
                T attackEntity = attackEntities.get(attackEntityNum);
                attackEntities.remove(attackEntityNum);

                int defensiveEntityNum = Random.generateRandomNum(0, defensiveEntities.size());
                Entity defensiveEntity = defensiveEntities.get(defensiveEntityNum);
                Entity defensiveBeforeAttackEntity = defensiveEntity.copy();
                int damage = attackEntity.attack(defensiveEntity);
                printAttackInfo(attackEntity,defensiveBeforeAttackEntity, defensiveEntity, damage);

                if (!defensiveEntity.isAlive()) {
                    printEntityDeath(defensiveEntity);
                    defensiveTurnEntities.remove(defensiveEntity);
                    defensiveEntities.remove(defensiveEntity);
                }
                return attackEntity;
            }
            return null;
        }

    private void printAttackInfo(Entity attacker, Entity defenderBeforeAttack, Entity defenderAfterAttack,
                                 int damage) {
        System.out.println(attacker + " attacks " + defenderBeforeAttack + " with damage " + damage +
                ". " + defenderAfterAttack + " has " + defenderAfterAttack.getHealth() + " health remaining.");
    }

    private void printEntityDeath(Entity entity) {
        System.out.println("The " + entity + " has died.");
    }

    /**
     * Method for heal {@link Player} using method {@linkplain Player#heal()}.
     * @param player the {@link Player} who will be healed.
     * @param scanner used for user input, for get answer heal or not heal {@link Player}.
     */
    private void healPlayer(Player player, Scanner scanner){
        if(player.getCountHealth() > 0){
            String ans = getAnswerFromUser(scanner, player);
            if (ans.equals("y") || ans.equals("yes")) {
                player.heal();
                System.out.println(player + " is healed");
            }else {
                System.out.println(player + " is not healed");
            }
        }
    }

    private String getAnswerFromUser(Scanner scanner, Player player){
        InputValidator inputValidator = new InputValidator(scanner);
        Set<String> availableStrings = new HashSet<>();
        availableStrings.add("y");
        availableStrings.add("n");
        availableStrings.add("yes");
        availableStrings.add("no");
        System.out.println("Do you want to heal a " +   player + " to health with a value of " +
                player.calculateHeal() + "?");
        return inputValidator.validateStringCommand(availableStrings, "answer(y/n)");

    }

    private void deleteDiedEntity(List<? extends Entity> entities){
        for (Entity entity : entities) {
            if(!entity.isAlive()) entities.remove(entity);
        }
    }
    }

