package ru.testtask.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Collection manager.
 * The main class, which store list {@link Player} and list {@link Monster} in Map.
 * For get list {@link Player} used key "players", for get list {@link Monster} used key "monsters".
 */
public class CollectionManager {
    private static final List<Player> players = new ArrayList<>();
    private static final List<Monster> monsters = new ArrayList<>();
    @Getter
    private static final Map<String, List<? extends Entity>> collection = new HashMap<>();

    static {
        collection.put("players", players);
        collection.put("monsters", monsters);
    }

    /**
     * Gets list string. Returned list all {@link Entity} in specific collection as String.
     *
     * @param collectionName the collection name used for add in specific collection.
     * @return the string - list all {@link Entity} as String.
     */
    public static String getListString(String collectionName) {
        List<? extends Entity> entities = collection.get(collectionName);
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < entities.size(); i++) {
            ans.append(i + 1).append(" ").append(entities.get(i)).append("\n");
        }
        return ans.toString();
    }
}
