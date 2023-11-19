package model;

import association.SensorLocationPair;
import java.util.UUID;

public class Location implements HasUUID {

        private final UUID locationID;
        private String locationName;
        private SensorLocationPair sensorLocationPair;

        public Location(){
            this.locationID = UUID.randomUUID();
        }
        public Location(String name) {
            this.locationID = UUID.randomUUID();
            this.locationName = name;
        }

        public SensorLocationPair getSensorLocationPair() {
            return this.sensorLocationPair;
        }

        public void setSensorLocationPair(SensorLocationPair sensorLocationPair) {
            this.sensorLocationPair = sensorLocationPair;
        }

        public void setLocationName(String name) {
            this.locationName = name;
        }
        public UUID getUUID() {
            return this.locationID;
        }

        public String getLocationName() {
            return this.locationName;
        }

        public String toString() {
            // Return Location: {Location name: <locationName>, LocationUUID: <locationID>, SensorUUID: <sensorID>}
            return String.format("Location: {Location name: %s, LocationUUID: %s, SensorUUID: %s}",
                    this.locationName, this.locationID, this.sensorLocationPair.getSensor().getUUID());
        }

}
