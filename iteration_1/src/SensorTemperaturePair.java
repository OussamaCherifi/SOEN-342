public class SensorTemperaturePair {
    private Sensor sensor;
    private Temperature temperature;

    private SensorTemperaturePair(){
    }

    public SensorTemperaturePair(Sensor sensor) {
        this.sensor = sensor;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "SensorTemperaturePair: " + this.sensor + " " + this.temperature;
    }
}