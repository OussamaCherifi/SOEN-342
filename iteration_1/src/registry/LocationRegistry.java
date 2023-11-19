package registry;

import model.Location;
import model.Temperature;

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

    public void updateLocationName(UUID locationUUID, String name) throws Exception {
        getItem(locationUUID).setLocationName(name);
    }

    public float getLocationTemperature(UUID locationUUID) throws Exception {
        // Retrieve the temperature
        Temperature temperature = getItem(locationUUID).getSensorLocationPair().getSensor().getTemperaturePair().getTemperature();
        return temperature.getValue();
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