package ru.testtask.models;

/**
 * The type Monster.
 */
public class Monster extends Entity {
    /**
     * Instantiates a new Monster.
     */
    public Monster() {
        super();
    }

    /**
     * Instantiates a new Monster.
     *
     * @param attack  the attack
     * @param defense the defense
     * @param health  the health
     * @param damage  the damage
     * calls constructor {@link Entity#Entity(int, int, int, int)}  with validate args)}
     * @see Entity#Entity(int, int, int, int)
     */
    public Monster(int attack, int defense, int health, int damage) {
        super(attack, defense, health, damage);
    }

    @Override
    public String toString() {
        return "Monster(" +
                "attack=" + attack +
                ", defense=" + defense +
                ", health=" + health +
                ", damage=" + damage +
                ')';
    }
    @Override
    public Entity copy(){
        return new Monster(attack, defense, health, damage);
    }
}
