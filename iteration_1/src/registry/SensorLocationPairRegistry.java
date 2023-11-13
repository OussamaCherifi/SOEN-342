package registry;

import association.SensorLocationPair;
import model.Location;
import model.Sensor;

import java.util.UUID;

public class SensorLocationPairRegistry extends Registry<SensorLocationPair>
        implements IPairRegistry<Sensor, Location, SensorLocationPair> {
    private static SensorLocationPairRegistry instance;
    private SensorLocationPairRegistry() {
        super();
    }

    public SensorLocationPair createPair(Sensor sensor, Location location) {
        if(!isPairMultiplicityValid(sensor, location)) return null;
        // Create a new SensorLocationPair
        SensorLocationPair sensorLocationPair = new SensorLocationPair(sensor, location);
        // Set the location of the SensorLocationPair
        addItem(sensorLocationPair);
        // Return the UUID of the sensorLocationPair
        return sensorLocationPair;
    }

    @Override
    public boolean isPairMultiplicityValid(Sensor sensor, Location location) {
        // Sensor can have 0-1 location, and a location has exactly 1 sensor
        // Check if the sensor is used in another SensorLocationPair
        for (SensorLocationPair sensorLocationPair : getItems()) {
            if (sensorLocationPair.getSensor().getUUID().equals(sensor.getUUID())) {
                System.out.println("Sensor already deployed");
                return false;
            }
        }
        // Check if the location is used in another SensorLocationPair
        for (SensorLocationPair sensorLocationPair : getItems()) {
            if (sensorLocationPair.getLocation().getUUID().equals(location.getUUID())) {
                System.out.println("Location already covered");
                return false;
            }
        }
        return true;
    }

    @Override
    public UUID createItem() {
        return null;
    }

    public static SensorLocationPairRegistry getInstance() {
        if (instance == null) {
            instance = new SensorLocationPairRegistry();
        }
        return instance;
    }

    @Override
    public void deleteItem(UUID itemUUID) {
        // Remove the sensorLocationPair from the registry
        removeItem(itemUUID);
    }
}
