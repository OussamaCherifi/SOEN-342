package model;

import association.SensorLocationPair;
import association.SensorTemperaturePair;
import java.util.UUID;

public class Sensor implements HasUUID {
    private final UUID sensorID;
    private boolean isDeployed;
    private SensorLocationPair locationPair;
    private SensorTemperaturePair temperaturePair;

    public Sensor() {
        this.sensorID = UUID.randomUUID();
        this.isDeployed = false;
        this.locationPair = null;
        this.temperaturePair = null;
        // Print confirmation message
        System.out.println("Sensor added: UUID: " + this.sensorID);
    }

    public UUID getUUID() {
        return this.sensorID;
    }

    public void deploy(SensorLocationPair locationPair, SensorTemperaturePair temperaturePair) {
        setIsDeployed(true);
        this.locationPair = locationPair;
        this.temperaturePair = temperaturePair;
        // Print deployment confirmation message
        System.out.println("Sensor deployed: UUID: " + this.sensorID +", at Location: "
                + this.locationPair.getLocation().getLocationName());
    }

    public boolean getIsDeployed() {
        return this.isDeployed;
    }

    public void updateTemperature(float temperature) {
        this.temperaturePair.getTemperature().setValue(temperature);
    }

    public void setIsDeployed(boolean isDeployed) {
        this.isDeployed = isDeployed;
    }

    public SensorLocationPair getLocationPair() {
        return this.locationPair;
    }

    public void setLocationPair(SensorLocationPair locationPair) {
        this.locationPair = locationPair;
    }

    public SensorTemperaturePair getTemperaturePair() {
        return this.temperaturePair;
    }

    public String toString() {
        // Conditionally display location and temperature information if sensor is deployed
        // Display sensor ID and deployment status using string formatting
        if (this.isDeployed) {
            return String.format("Sensor: {UUID: %s, isDeployed: True: Location: %s, Reading: %s}",
                    this.sensorID,
                    this.locationPair.getLocation(),
                    this.temperaturePair.getTemperature().getValue());
        } else {
            return String.format("Sensor: {UUID: %s, isDeployed: False}", this.sensorID);
        }
    }

}
