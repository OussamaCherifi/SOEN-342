package registry;

import model.Temperature;

import java.util.UUID;

public class TemperatureRegistry extends Registry<Temperature> {
    private static TemperatureRegistry instance;
    private TemperatureRegistry() {
        super();
    }
    @Override
    public UUID createItem() {
        // Create a new Temperature
        Temperature temperature = new Temperature();
        // Register the temperature
        addItem(temperature);
        // Return the UUID of the temperature
        return temperature.getUUID();
    }

    @Override
    public void deleteItem(UUID itemUUID) {
        // Remove the temperature from the registry
        removeItem(itemUUID);
    }

    public static TemperatureRegistry getInstance() {
        if (instance == null) {
            instance = new TemperatureRegistry();
        }
        return instance;
    }
}
