public class SensorLocationPair {
    private Sensor sensor;
    private Location location;

    private SensorLocationPair(){
    }

    public SensorLocationPair(Sensor sensor) {
        this.sensor = sensor;
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
}
