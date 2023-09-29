package ru.testtask.commands;

import ru.testtask.models.CollectionManager;
import ru.testtask.models.Entity;

import java.util.List;

/**
 * The type Remove by id.
 * Command that remove {@link Entity} from a collection, which contains in {@link CollectionManager}.
 */
public class RemoveById implements Command<Integer> {
    /**
     * Method that remove {@link Entity} from a collection, which contains in {@link CollectionManager}.
     *
     * @param collectionName the collection name used for remove from a specific collection in {@link CollectionManager}.
     * @param id           the id that require delete from collection in {@link CollectionManager} {@link Entity}.
     * @return list {@link Entity} returned all List specific collection.
     */
    @Override
    public List<Entity> execute(String collectionName, Integer id) {
        List<Entity> entities = (List<Entity>) CollectionManager.getCollection().get(collectionName);
        entities.remove((int) id);
        return entities;
    }
}
