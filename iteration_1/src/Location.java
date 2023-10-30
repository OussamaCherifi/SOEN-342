import java.util.UUID;

public class Location implements HasUUID {

        private UUID locationID;
        private String locationName;
        private SensorLocationPair sensorLocationPair;

        public Location(String name, SensorLocationPair sensorLocationPair) {
            this.locationID = UUID.randomUUID();
            this.locationName = name;
            this.sensorLocationPair = sensorLocationPair;
        }

        public SensorLocationPair getSensorLocationPair() {
            return this.sensorLocationPair;
        }

        public void setSensorLocationPair(SensorLocationPair sensorLocationPair) {
            this.sensorLocationPair = sensorLocationPair;
        }

        public UUID getUUID() {
            return this.locationID;
        }

        public String getLocationName() {
            return this.locationName;
        }

        public String toString() {
            return this.locationName;
        }

}
