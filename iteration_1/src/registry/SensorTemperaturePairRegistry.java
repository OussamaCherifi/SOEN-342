package registry;

import association.SensorTemperaturePair;
import model.Sensor;
import model.Temperature;

import java.util.UUID;

public class SensorTemperaturePairRegistry extends Registry<SensorTemperaturePair>
        implements IPairRegistry<Sensor, Temperature, SensorTemperaturePair> {

    private static SensorTemperaturePairRegistry instance;
    private SensorTemperaturePairRegistry() {
        super();
    }

    @Override
    public SensorTemperaturePair createPair(Sensor sensor, Temperature temperature) {
        // Check if the sensor-temperature pair is valid
        if (!isPairMultiplicityValid(sensor, temperature)) return null;
        // Create a new SensorTemperaturePair
        SensorTemperaturePair sensorTemperaturePair = new SensorTemperaturePair(sensor, temperature);
        // Register the sensorTemperaturePair
        addItem(sensorTemperaturePair);
        // Return the UUID of the sensorTemperaturePair
        return sensorTemperaturePair;
    }

    @Override
    public boolean isPairMultiplicityValid(Sensor sensor, Temperature temperature) {
        // Sensor-Temperature is a one-to-one relationship
        // Check if the sensor is used in another SensorTemperaturePair
        for (SensorTemperaturePair sensorTemperaturePair : getItems()) {
            if (sensorTemperaturePair.getSensor().getUUID().equals(sensor.getUUID())) {
                System.out.println("Sensor already deployed");
                return false;
            }
        }
        // Check if the temperature is used in another SensorTemperaturePair
        for (SensorTemperaturePair sensorTemperaturePair : getItems()) {
            if (sensorTemperaturePair.getTemperature().equals(temperature)) {
                System.out.println("Temperature already read by a sensor");
                return false;
            }
        }
        return true;
    }


    @Override
    public UUID createItem() {
        return null;
    }

    public static SensorTemperaturePairRegistry getInstance() {
        if (instance == null) {
            instance = new SensorTemperaturePairRegistry();
        }
        return instance;
    }

    @Override
    public void deleteItem(UUID itemUUID) {
        // Remove the sensorTemperaturePair from the registry
        removeItem(itemUUID);
    }

}
