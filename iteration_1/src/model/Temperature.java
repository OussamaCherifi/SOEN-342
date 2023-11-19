package model;

import association.SensorTemperaturePair;
import java.util.UUID;

public class Temperature implements HasUUID {
    private final UUID temperatureID;
    SensorTemperaturePair sensorTemperaturePair;
    private float value;

    public Temperature() {
        this.temperatureID = UUID.randomUUID();
        // Random number between 17 and 32 with 3 significant figures
        this.value = (float) (Math.round((Math.random() * 15 + 17) * 100.0) / 100.0);
    }

    public void setSensorTemperaturePair(SensorTemperaturePair sensorTemperaturePair) {
        this.sensorTemperaturePair = sensorTemperaturePair;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }


    @Override
    public UUID getUUID() {
        return temperatureID;
    }
}
