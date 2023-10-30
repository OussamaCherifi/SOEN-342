public class Temperature {
    SensorTemperaturePair sensorTemperaturePair;
    private float value;

    public Temperature(SensorTemperaturePair sensorTemperaturePair) {
        this.sensorTemperaturePair = sensorTemperaturePair;
        this.value = 0.0f;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }


}
