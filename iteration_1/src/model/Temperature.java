package model;

import association.SensorTemperaturePair;
import java.util.UUID;

public class Temperature implements HasUUID {
    private final UUID temperatureID;
    SensorTemperaturePair sensorTemperaturePair;
    private float value;

    public Temperature() {
        this.temperatureID = UUID.randomUUID();
        this.value = 0.0f;
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
