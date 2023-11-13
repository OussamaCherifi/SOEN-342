package registry;

// Singleton class that has all Registries in the system
public class RegistryManager {
    private static RegistryManager instance = null;

    private final SensorRegistry sensorRegistry;
    private final LocationRegistry locationRegistry;
    private final SensorLocationPairRegistry sensorLocationPairRegistry;
    private final SensorTemperaturePairRegistry sensorTemperaturePairRegistry;
    private final TemperatureRegistry temperatureRegistry;

    private RegistryManager(){
        // Initialize all registries using getInstance()
        this.sensorRegistry = SensorRegistry.getInstance();
        this.locationRegistry = LocationRegistry.getInstance();
        this.sensorLocationPairRegistry = SensorLocationPairRegistry.getInstance();
        this.sensorTemperaturePairRegistry = SensorTemperaturePairRegistry.getInstance();
        this.temperatureRegistry = TemperatureRegistry.getInstance();
    }

    public static RegistryManager getInstance() {
        if(instance == null) {
            instance = new RegistryManager();
        }
        return instance;
    }

    public SensorRegistry getSensorRegistry() {
        return sensorRegistry;
    }

    public LocationRegistry getLocationRegistry() {
        return locationRegistry;
    }

    public SensorLocationPairRegistry getSensorLocationPairRegistry() {
        return sensorLocationPairRegistry;
    }

    public TemperatureRegistry getTemperatureRegistry() {
        return temperatureRegistry;
    }

    public SensorTemperaturePairRegistry getSensorTemperaturePairRegistry() {
        return sensorTemperaturePairRegistry;
    }
}
