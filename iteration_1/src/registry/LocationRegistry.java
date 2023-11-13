package registry;

import model.Location;

import java.util.UUID;

public class LocationRegistry extends Registry<Location> {
    private static LocationRegistry instance;
    private LocationRegistry() {
        super();
    }

    @Override
    public UUID createItem() {
        // Create a new Temperature
        Location location = new Location();
        // Register the temperature
        addItem(location);
        return location.getUUID();
    }

    public void updateLocationName(UUID locationUUID, String name) {
        getItem(locationUUID).setLocationName(name);
    }

    public static LocationRegistry getInstance() {
        if (instance == null) {
            instance = new LocationRegistry();
        }
        return instance;
    }

    @Override
    public void deleteItem(UUID itemUUID) {
        // Remove the temperature from the registry
        removeItem(itemUUID);
    }

}