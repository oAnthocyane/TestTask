package ru.testtask.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.testtask.exceptions.DeficientCountHealthException;

/**
 * The type Player.
 */
public class Player extends Entity {
    /**
     * The coefficient health, conditionally 30%
     */
    private final float COEFFICIENT_HEALTH = 0.3f;

    /**
     * The count possible cures, conditionally 4.
     */
    @Getter
    private int countHealth = 4;

    /**
     * Instantiates a new Player.
     */
    public Player() {
        super();
    }

    /**
     * Instantiates a new Player.
     *
     * @param attack  the attack
     * @param defense the defense
     * @param health  the health
     * @param damage  the damage
     * calls constructor {@link Entity#Entity(int, int, int, int)}  with validate args)}
     * @see Entity#Entity(int, int, int, int)
     */
    public Player(int attack, int defense, int health, int damage) {
        super(attack, defense, health, damage);
    }

    /**
     * Healing method up to {@link Player#countHealth} times. Restores up to 1 + {@link Player#COEFFICIENT_HEALTH} of health.
     * @throws DeficientCountHealthException the deficient count health exception. Thrown when {@link Player#countHealth} less or equals zero.
     */
    public void heal() throws DeficientCountHealthException {
        if (countHealth <= 0) throw new DeficientCountHealthException("The player can no longer heal");

        this.health = calculateHeal();
        countHealth--;

    }

    /**
     * Calculate heal how {@link Player#health} + {@link Player#MAX_HEALTH} * {@link Player#COEFFICIENT_HEALTH}.
     *
     * @return the int - min of calculating heal of {@link Player#MAX_HEALTH}
     */
    public int calculateHeal(){
        int newHealth = (int) (health + MAX_HEALTH * COEFFICIENT_HEALTH);
        return Math.min(newHealth, MAX_HEALTH);
    }

    @Override
    public String toString() {
        return "Player(" +
                "countHealth=" + countHealth +
                ", attack=" + attack +
                ", defense=" + defense +
                ", health=" + health +
                ", damage=" + damage +
                ')';
    }

    @Override
    public Entity copy(){
        return new Player(attack, defense, health, damage);
    }
}
