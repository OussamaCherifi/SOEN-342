package registry;

import model.HasUUID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public abstract class Registry <T extends HasUUID> {
    private final HashMap<UUID,T> registryItems = new HashMap<>();

    protected void addItem(T item) {
        registryItems.put(item.getUUID(), item);
    }

    protected void removeItem(UUID uuid) { registryItems.remove(uuid); }

    protected T getItem(UUID uuid) { return registryItems.get(uuid); }

    public List<T> getItems() { return new ArrayList<>(registryItems.values()); }

    public abstract UUID createItem();

    public abstract void deleteItem(UUID itemUUID);

    public void displayItems(){
        System.out.println("Items in " + this.getClass().getSimpleName() + ":");
        for (T item : registryItems.values()) {
            System.out.println(" - " + item);
        }
    }
}