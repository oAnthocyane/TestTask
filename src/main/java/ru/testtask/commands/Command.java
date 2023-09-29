package ru.testtask.commands;

import ru.testtask.models.Entity;

import java.util.List;

/**
 * The interface Command for abstract command don`t using cli or another data from user.
 *
 * @param <T> the type parameter data for act on this data in collection.
 */
public interface Command<T> {

    /**
     * Execute list.
     *
     * @param collectionName the collection name for using specific collection in {@link ru.testtask.models.CollectionManager}
     * @param data           the data that works
     * @return the list {@link Entity} modified specific collection.
     */
    List<Entity> execute(String collectionName, T data);
}
