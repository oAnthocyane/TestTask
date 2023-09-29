package ru.testtask.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.testtask.properties.Config;
import ru.testtask.utils.Random;
import ru.testtask.validation.InputValidator;

import java.util.Scanner;

/**
 * The type Entity, which is described in the condition.
 * Has a minimum attack {@linkplain Entity#MIN_ATTACK} (conditionally 1) and maximum attack {@linkplain Entity#MAX_ATTACK} (conditionally 30).
 * Has a minimum defense {@linkplain Entity#MIN_DEFENSE} (conditionally 1) and maximum defense {@linkplain Entity#MAX_DEFENSE} (conditionally 30).
 * Has a minimum health {@linkplain Entity#MIN_HEALTH} (conditionally 0) and maximum health {@linkplain Entity#MAX_HEALTH} (conditionally N - obtained from the application.properties file).
 * Has a minimum damage {@linkplain Entity#MIN_DAMAGE} (conditionally M - obtained from the application.properties file) and maximum damage {@linkplain Entity#MAX_DAMAGE} (conditionally N - obtained from the application.properties file).
 * Has field a attack, health, defense and damage, which must fall within the range of the corresponding constants.
 *
 */
@Data
@NoArgsConstructor
@ToString(of = {"attack", "health", "defense", "damage"})
public class Entity {

    /**
     * The Min attack. Conditionally 1.
     */
    protected final int MIN_ATTACK = 1;
    /**
     * The Max attack. Conditionally 30.
     */
    protected final int MAX_ATTACK = 30;
    /**
     * The Min defense. Conditionally 1.
     */
    protected final int MIN_DEFENSE = 1;
    /**
     * The Max defense. Conditionally 30.
     */
    protected final int MAX_DEFENSE = 30;
    /**
     * The Min health. Conditionally 0.
     */
    protected final int MIN_HEALTH = 0;
    /**
     * The Max health. Conditionally N - obtained from the application.properties file as parameter entity.max_health
     */
    protected final int MAX_HEALTH = Config.getMaxHealthEntity();
    /**
     * The Min damage. conditionally N - obtained from the application.properties file as parameter entity.min_damage
     */
    protected final int MIN_DAMAGE = Config.getMinDamageEntity();
    /**
     * The Max damage. conditionally M - obtained from the application.properties file as parameter entity.max_damage
     */
    protected final int MAX_DAMAGE = Config.getMaxDamageEntity();

    /**
     * The possible numbers on dice. Conditionally 5 or 6.
     */
    private final int[] possibleNumbersOnDice = new int[]{5, 6};

    /**
     * The Attack.
     */
    protected int attack;

    /**
     * The Defense.
     */
    protected int defense;

    /**
     * The Health.
     */
    protected int health;

    /**
     * The Damage.
     */
    protected int damage;

    /**
     * Instantiates a new Entity and thrown IllegalArgumentException if the fields are not within the range of the corresponding constants.
     *
     * @param attack  the attack must be in the range from {@linkplain Entity#MIN_ATTACK} to {@linkplain Entity#MAX_ATTACK}.
     * @param defense the defense must be in the range from {@linkplain Entity#MIN_DEFENSE} to {@linkplain Entity#MAX_DEFENSE}.
     * @param health  the health must be in the range from {@linkplain Entity#MIN_HEALTH} to {@linkplain Entity#MAX_HEALTH}.
     * @param damage  the damage must be in the range from {@linkplain Entity#MIN_DAMAGE} to {@linkplain Entity#MAX_DAMAGE}.
     */
    public Entity(int attack, int defense, int health, int damage) {
        if(!(InputValidator.isValidateInt(attack, MIN_ATTACK, MAX_ATTACK) &&
                InputValidator.isValidateInt(defense, MIN_DEFENSE, MAX_DEFENSE) &&
                InputValidator.isValidateInt(health, MIN_HEALTH, MAX_HEALTH) &&
                InputValidator.isValidateInt(damage, MIN_DAMAGE, MAX_DAMAGE)))
            throw new IllegalArgumentException("It is impossible to create an entity with these fields");
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.damage = damage;
    }


    /**
     * Set field from console. Used for creating a new {@link Entity} for user from console.
     * @see ru.testtask.commands.Add
     *
     * @param scanner the scanner
     */
    public void setFieldFromConsole(Scanner scanner) {
        InputValidator inputValidator = new InputValidator(scanner);
        this.attack = inputValidator.validateIntInput("attack", MIN_ATTACK, MAX_ATTACK);
        this.defense = inputValidator.validateIntInput("defense", MIN_DEFENSE, MAX_DEFENSE);
        this.health = inputValidator.validateIntInput("health", MIN_HEALTH, MAX_HEALTH);
        this.damage = inputValidator.validateIntInput("damage", MIN_DAMAGE, MAX_DAMAGE);
    }

    /**
     * Is alive boolean. If {@linkplain Entity#health} bigger than {@link Entity#MIN_HEALTH} return true, else false.
     *
     * @return the boolean
     */
    public boolean isAlive() {
        return health > MIN_HEALTH;
    }

    /**
     * Calculate modification attack how difference {@link Entity#attack} and {@link Entity#defense} + 1.
     * Since by convention only the number of die rolls depends on the attack modifier and the die is rolled at least once, the maximum of 1 and the calculated attack modifier is returned.
     * @param defender {@link Entity} being protected.
     * @return int - max of modifier attack and 1.
     */
    private int calculateModifAttack(Entity defender) {
        int dif = this.attack - defender.defense + 1;
        return Math.max(dif, 1);
    }

    /**
     * Check if attack possible, count the attack modifier and roll the dice (using {@link Random#generateRandomNum(int, int)}) that number of times, if at least once the die shows a number that is in the {@link Entity#possibleNumbersOnDice}, it returns true, otherwise false.
     * @param defender the {@link Entity} being protected.
     * @return boolean - can attack defender {@link Entity} or not.
     */
    private boolean isAttackPossible(Entity defender) {
        int modificatorAttack = calculateModifAttack(defender);
        for (int i = 0; i < modificatorAttack; i++) {
            int number = Random.generateRandomNum(Dice.MIN_NUMBER, Dice.MAX_NUMBER);
            if (isPossibleNumberOnDice(number)) return true;
        }
        return false;
    }

    private boolean isPossibleNumberOnDice(int number) {
        for (int possibleNumber : possibleNumbersOnDice) {
            if (number == possibleNumber) return true;
        }
        return false;
    }

    /**
     * Attack {@link Entity}.
     * The attack occurs according to the following algorithm:
     * - Calculate the Attack Modifier. It is equal to the difference between the attacker's Attack and the defender's Defense plus 1.
     * @see Entity#calculateModifAttack(Entity)
     * - Success is determined by rolling N dice with numbers from 1 to 6, where N is the Attack Modifier. At least one die is always rolled.
     * - A hit is considered successful if at least one of the dice rolls a 5 or 6.
     * @see Entity#isAttackPossible(Entity)
     * - If the hit is successful, it takes an arbitrary value from the attacker's Damage and subtracts it from the defender's Health.
     *
     * @param defender the defensive {@link Entity}.
     * @return the int - damage to the defending {@link Entity}.
     */
    public int attack(Entity defender) {
        int damage = 0;
        if (isAttackPossible(defender)) {
            damage = Random.generateRandomNum(0, this.damage);
            defender.setHealth(Math.max(defender.health - damage, MIN_HEALTH));
        }
        return damage;
    }

    /**
     * Copy the object {@link Entity} with same attack, defense, health and damage.
     *
     * @return the {@link Entity}
     */
    public Entity copy(){
        return new Entity(attack, defense, health, damage);
    }

}
