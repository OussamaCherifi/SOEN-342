package controller;

import registry.*;

import java.util.Scanner;
import java.util.UUID;

public class Console {

    static final RegistryManager registryManager = RegistryManager.getInstance();
    static final Scanner scanner = new Scanner(System.in);

    public static void initializeSensors(){
        for (int i = 65; i < 75; i++) {
            UUID sensorUUID = registryManager.getSensorRegistry().createItem();
            if(i < 70){
                // Create a location
                UUID locationUUID = registryManager.getLocationRegistry().createItem();
                try {
                    registryManager.getLocationRegistry().updateLocationName(locationUUID, "Loc(" + (char)i + ")");
                    registryManager.getSensorRegistry().deploySensor(sensorUUID, locationUUID);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void start() {
        // Hardcode 5 deployed sensors and 5 undeployed sensors
        initializeSensors();
        // Create a menu for the administrator to interact with
        String menu = "\nWelcome to the Console\n" +
                "Please select an option:\n" +
                "1. Add a sensor\n" +
                "2. Deploy a sensor\n" +
                "3. Remove a sensor\n" +
                "4. Read a temperature\n" +
                "5. Display all sensors\n" +
                "6. Display all locations\n" +
                "7. Exit";
        while(true) {
            System.out.println(menu);
            // Get the user's input and catch errors
            try {
                int option = scanner.nextInt();
                // Switch statement to handle the user's input
                switch (option) {
                    case 1:
                        addSensor();
                        break;
                    case 2:
                        deploySensor();
                        break;
                    case 3:
                        removeSensor();
                        break;
                    case 4:
                        readTemperature();
                        break;
                    case 5:
                        displaySensors();
                        break;
                    case 6:
                        displayLocations();
                        break;
                    case 7:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Option out of range");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void readTemperature() {
        // Retrieve Sensor UUID from user
        scanner.nextLine();
        System.out.println("Enter the UUID of the sensor: ");
        String sensorUUID = scanner.nextLine();
        // Retrieve the temperature
        try {
            System.out.println("Temperature: " + registryManager.getLocationRegistry().getLocationTemperature(UUID.fromString(sensorUUID)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addSensor(){
        registryManager.getSensorRegistry().createItem();
    }

    public static void removeSensor() throws Exception {
        // Retrieve Sensor UUID from user
        scanner.nextLine();
        System.out.println("Enter the UUID of the sensor: ");
        String sensorUUID = scanner.nextLine();
        registryManager.getSensorRegistry().deleteItem(UUID.fromString(sensorUUID));
    }

    public static void deploySensor(){
        // Retrieve Sensor UUID from user
        scanner.nextLine();
        System.out.println("Enter the UUID of the sensor: ");
        String sensorUUID = scanner.nextLine();
        // Check if the sensor is already deployed
        try {
            if(registryManager.getSensorRegistry().isSensorDeployed(UUID.fromString(sensorUUID))){
                System.out.println("Sensor already deployed");
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Create a location
        System.out.println("Location name: ");
        String locationName = scanner.nextLine();
        UUID locationUUID = registryManager.getLocationRegistry().createItem();
        try {
            registryManager.getLocationRegistry().updateLocationName(locationUUID, locationName);
            registryManager.getSensorRegistry().deploySensor(UUID.fromString(sensorUUID), locationUUID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Deploy the sensor

    }

    public static void displayLocations(){
        System.out.println("Displaying all Locations: ");
        registryManager.getLocationRegistry().displayItems();

    }

    public static void displaySensors(){
        System.out.println("Displaying all Sensors: ");
        registryManager.getSensorRegistry().displayItems();
    }
}
