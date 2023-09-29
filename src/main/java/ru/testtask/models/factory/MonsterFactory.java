package ru.testtask.models.factory;

import ru.testtask.models.Monster;

/**
 * The type Monster factory.
 */
public class MonsterFactory implements EntityFactory<Monster> {
    /**
     * Created a new {@link Monster}.
     * @return the new {@link Monster}
     */
    @Override
    public Monster createEntity() {
        return new Monster();
    }
}
