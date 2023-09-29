package ru.testtask.models.factory;

import ru.testtask.models.Entity;

/**
 * The interface Entity factory.
 * Used as factory pattern.
 * @param <E> extends {@link Entity} the type parameter.
 */
public interface EntityFactory<E extends Entity> {
    /**
     * Create {@link Entity} e.
     *
     * @return the e extends {@link Entity}.
     */
    E createEntity();
}
