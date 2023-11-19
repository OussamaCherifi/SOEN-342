package registry;

import association.SensorLocationPair;
import association.SensorTemperaturePair;
import model.Location;
import model.Sensor;
import model.Temperature;

import java.util.UUID;

public class SensorRegistry extends Registry<Sensor> {
    private static SensorRegistry instance;
    private SensorRegistry() {
        super();
    }

    @Override
    public UUID createItem() {
        // Create a new Sensor
        Sensor sensor = new Sensor();
        // Register the sensor
        addItem(sensor);
        // Return the UUID of the sensor
        return sensor.getUUID();
    }

    // Check if the sensor is already deployed
    public boolean isSensorDeployed(UUID sensorUUID) throws Exception {
        Sensor sensor = getItem(sensorUUID);
        return sensor.getIsDeployed();
    }


    public void deploySensor(UUID sensorUUID, UUID locationUUID) throws Exception {
        Sensor sensor = getItem(sensorUUID);
        // Check if the sensor is already deployed
        if(isSensorDeployed(sensorUUID)) {
            System.out.println("Sensor already deployed");
            return;
        }
        // Create associations between Sensor and Location
        Location location = RegistryManager.getInstance().getLocationRegistry().getItem(locationUUID);
        SensorLocationPair sensorLocationPair = RegistryManager.getInstance().getSensorLocationPairRegistry().createPair(sensor, location);
        if(sensorLocationPair == null) return;
        location.setSensorLocationPair(sensorLocationPair);

        // Create associations between Sensor and Location
        UUID temperatureUUID = RegistryManager.getInstance().getTemperatureRegistry().createItem();
        Temperature temperature = RegistryManager.getInstance().getTemperatureRegistry().getItem(temperatureUUID);
        SensorTemperaturePair sensorTemperaturePair = RegistryManager.getInstance().getSensorTemperaturePairRegistry().createPair(sensor, temperature);
        if(sensorTemperaturePair == null) return;
        temperature.setSensorTemperaturePair(sensorTemperaturePair);

        // Set the SensorLocationPair of the Sensor
        sensor.deploy(sensorLocationPair, sensorTemperaturePair);
    }

    @Override
    public void deleteItem(UUID itemUUID) throws Exception {
        // To delete a sensor, we need to delete its associations
        Sensor sensor = getItem(itemUUID);
        if(isSensorDeployed(sensor.getUUID())) {
            // Delete the associations between Sensor->Location  and Sensor->Temperature
            RegistryManager.getInstance().getSensorLocationPairRegistry().deleteItem(sensor.getLocationPair().getUUID());
            RegistryManager.getInstance().getSensorTemperaturePairRegistry().deleteItem(sensor.getTemperaturePair().getUUID());
            // Delete the temperature and location
            RegistryManager.getInstance().getTemperatureRegistry().deleteItem(sensor.getTemperaturePair().getTemperature().getUUID());
            RegistryManager.getInstance().getLocationRegistry().deleteItem(sensor.getLocationPair().getLocation().getUUID());
        }
        // Remove the sensor from the registry
        removeItem(itemUUID);
    }


    public static SensorRegistry getInstance() {
        if (instance == null) {
            instance = new SensorRegistry();
        }
        return instance;
    }
}