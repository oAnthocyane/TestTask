package ru.testtask.models.factory;

import ru.testtask.models.Player;

/**
 * The type Player factory.
 */
public class PlayerFactory implements EntityFactory<Player> {
    /**
     * Created a new {@link Player}.
     * @return the new {@link Player}.
     */
    @Override
    public Player createEntity() {
        return new Player();
    }
}
