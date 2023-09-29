package ru.testtask.commands;

import ru.testtask.models.CollectionManager;
import ru.testtask.models.Entity;

import java.util.List;

/**
 * The type Add.
 * Command that add {@link Entity} to collection, which contains in {@link CollectionManager}.
 */
public class Add implements Command<Entity> {

    /**
     * Method that add {@link Entity} to collection, which contains in {@link CollectionManager}.
     *
     * @param collectionName the collection name used for add in specific collection in {@link CollectionManager}.
     * @param entity {@link Entity} for add in collection in {@link CollectionManager}.
     * @return list {@link Entity} returned all List specific collection.
     */
    @Override
    public List<Entity> execute(String collectionName, Entity entity) {
        List<Entity> entities = (List<Entity>) CollectionManager.getCollection().get(collectionName);
        entities.add(entity);
        return entities;
    }
}
