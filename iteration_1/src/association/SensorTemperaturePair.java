package association;

import model.HasUUID;
import model.Sensor;
import model.Temperature;
import java.util.UUID;

public class SensorTemperaturePair implements HasUUID {
    private final UUID sensorTemperaturePairID;
    private Sensor sensor;
    private Temperature temperature;

    private SensorTemperaturePair(){
        // To circumvent the error final field might not be initialized
        this.sensorTemperaturePairID = UUID.randomUUID();
    }

    public SensorTemperaturePair(Sensor sensor, Temperature temperature) {
        this.sensorTemperaturePairID = UUID.randomUUID();
        this.sensor = sensor;
        this.temperature = temperature;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public Temperature getTemperature() { return temperature; }

    @Override
    public String toString() {
        return String.format("SensorTemperaturePair: {%s, %s}", this.sensor, this.temperature);
    }

    @Override
    public UUID getUUID() {
        return sensorTemperaturePairID;
    }
}