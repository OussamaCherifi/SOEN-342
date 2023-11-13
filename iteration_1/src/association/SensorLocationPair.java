package association;

import model.HasUUID;
import model.Location;
import model.Sensor;
import java.util.UUID;

public class SensorLocationPair implements HasUUID {
    private final UUID sensorLocationPairID;
    private Sensor sensor;
    private Location location;

    private SensorLocationPair(){
        // To circumvent the error final field might not be initialized
        this.sensorLocationPairID = UUID.randomUUID();
    }

    public SensorLocationPair(Sensor sensor, Location location) {
        this.sensorLocationPairID = UUID.randomUUID();
        this.sensor = sensor;
        this.location = location;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return String.format("SensorLocationPair: {%s, %s}", this.sensor, this.location);
    }

    @Override
    public UUID getUUID() {
        return sensorLocationPairID;
    }
}
