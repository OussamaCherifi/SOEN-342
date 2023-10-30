import java.util.Scanner;
import java.util.UUID;

public class Console {
    static LocationRegistry locationRegistry = new LocationRegistry();
    static SensorRegistry sensorRegistry = new SensorRegistry();
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        // Hardcode 5 deployed sensors and 5 undeployed sensors
        for (int i = 65; i < 75; i++) {
            Sensor sensor = new Sensor();
            if(i < 70){
                // Create a location pair
                SensorLocationPair sensorLocationPair = new SensorLocationPair(sensor);
                Location location = new Location("Loc(" + (char)i + ")", sensorLocationPair);
                sensorLocationPair.setLocation(location);
                // Create a temperature pair
                SensorTemperaturePair sensorTemperaturePair = new SensorTemperaturePair(sensor);
                Temperature temperature = new Temperature(sensorTemperaturePair);
                sensorTemperaturePair.setTemperature(temperature);
                // Deploy the sensor
                sensor.deploy(sensorLocationPair, sensorTemperaturePair);
                // Register the location
                locationRegistry.addItem(location);
            }
            // Register the sensor
            sensorRegistry.addItem(sensor);
        }


        // Create a menu for the administrator to interact with
        // Create a string from the menu
        String menu = "\nWelcome to the Sensor Registry\n" +
                "Please select an option:\n" +
                "1. Add a sensor\n" +
                "2. Deploy a sensor\n" +
                "3. Remove a sensor\n" +
                "4. Display all sensors\n" +
                "5. Display all locations\n" +
                "6. Exit";
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
                        displaySensors();
                        break;
                    case 5:
                        displayLocations();
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("option out of range");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid option");
                scanner.nextLine();
            }

        }

    }

    public static void addSensor(){
        // Create a new Sensor
        Sensor sensor = new Sensor();
        // Register the sensor
        sensorRegistry.addItem(sensor);
        // Asks the user if they wish to deploy it
        System.out.println("Do you wish to deploy the sensor? (y/n)");

        scanner.nextLine();
        String deploy = scanner.nextLine();
        // If the user wishes to deploy the sensor
        if (deploy.equals("y")){
            // Create a new SensorTemperaturePair
            SensorLocationPair locationPair = addLocation(sensor);
            // Create a new SensorTemperaturePair
            SensorTemperaturePair temperaturePair = addTemperature(sensor);
            // Register the pair
            sensor.deploy(locationPair, temperaturePair);
        }
    }

    public static void removeSensor(){
        // Ask for the UUID of the sensor
        System.out.println("Enter the UUID of the sensor: ");
        scanner.nextLine();
        String sensorUUID = scanner.nextLine();
        // Retrieve the sensor
        Sensor sensor = sensorRegistry.getItem(UUID.fromString(sensorUUID));
        // Remove the sensor
        sensorRegistry.removeItem(sensor.getUUID());
        // Remove its location
        locationRegistry.removeItem(sensor.getLocationPair().getLocation().getUUID());
        sensor.getLocationPair().setLocation(null);
        sensor.getLocationPair().setSensor(null);
        // Remove its temperature
        sensor.getTemperaturePair().setTemperature(null);
        sensor.getTemperaturePair().setSensor(null);
        // Print confirmation message
        System.out.println("Sensor removed: " + sensor.getUUID());
    }

    // Method that retrieves a sensor and creates a new SensorLocationPair
    public static void deploySensor(){
        scanner.nextLine();
        // Ask for the UUID of the sensor
        System.out.println("Enter the UUID of the sensor: ");
        String sensorUUID = scanner.nextLine();
        // Retrieve the sensor
        Sensor sensor;
        try{
            sensor = sensorRegistry.getItem(UUID.fromString(sensorUUID));
            if(sensor.getIsDeployed() == true){
                System.out.println("Sensor already deployed");
                return;
            }
        } catch (Exception e){
            System.out.println("Sensor not found");
            return;
        }
        // Create a new SensorTemperaturePair
        SensorLocationPair locationPair = addLocation(sensor);
        // Create a new SensorTemperaturePair
        SensorTemperaturePair temperaturePair = addTemperature(sensor);
        // Register the pair
        sensor.deploy(locationPair, temperaturePair);
    }

    public static void displayLocations(){
        // Display all locations for registry
        System.out.println("Displaying all Locations: ");
        for (Location location : locationRegistry.getItems()){
            System.out.println("- " + location);
        }

    }

    public static void displaySensors(){
        // Display all sensors for registry
        System.out.println("Displaying all Sensors: ");
        for (Sensor sensor : sensorRegistry.getItems()){
            System.out.println("- " + sensor);
        }

    }

    public static SensorLocationPair addLocation(Sensor sensor){
        //Create SensorLocationPair
        SensorLocationPair sensorLocationPair = new SensorLocationPair(sensor);
        // Ask for the name of the location
        System.out.println("Enter the name of the location: ");
        String locationName = scanner.nextLine();
        // Create a new Location
        Location location = new Location(locationName, sensorLocationPair);
        // Associate the location to the pair
        sensorLocationPair.setLocation(location);
        // Register the location
        locationRegistry.addItem(location);
        return sensorLocationPair;
    }

    public static SensorTemperaturePair addTemperature(Sensor sensor){
        // Create a new SensorTemperaturePair
        SensorTemperaturePair sensorTemperaturePair = new SensorTemperaturePair(sensor);
        // Create a new Temperature
        Temperature temperature = new Temperature(sensorTemperaturePair);
        // Associate the temperature to the pair
        sensorTemperaturePair.setTemperature(temperature);
        return sensorTemperaturePair;
    }
}
