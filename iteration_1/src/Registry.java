import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Registry <T extends HasUUID> {
    private HashMap<UUID,T> registryItems = new HashMap<UUID,T>();

    public void addItem(T item) {
        registryItems.put(item.getUUID(), item);
    }

    public T getItem(UUID uuid) {
        return registryItems.get(uuid);
    }

    public List<T> getItems() {
        return new ArrayList<T>(registryItems.values());
    }


    public void removeItem(UUID uuid) {
        registryItems.remove(uuid);
    }

}