import java.util.*;

// Observer Pattern - Observer Interface
interface Observer {
    void update(String status);
}

// Abstract Smart Device class (parent for all devices)
abstract class SmartDevice implements Observer {
    protected String id;
    protected String type;
    protected String status;

    public SmartDevice(String id, String type) {
        this.id = id;
        this.type = type;
        this.status = "off";
    }

    public abstract void turnOn();

    public abstract void turnOff();

    public String getStatus() {
        return this.status;
    }

    @Override
    public void update(String status) {
        this.status = status;
        System.out.println(type + " " + id + " is " + this.status);
    }
}

// Concrete SmartDevice classes
class Light extends SmartDevice {
    public Light(String id) {
        super(id, "Light");
    }

    @Override
    public void turnOn() {
        this.status = "on";
        System.out.println("Light " + id + " is turned on.");
    }

    @Override
    public void turnOff() {
        this.status = "off";
        System.out.println("Light " + id + " is turned off.");
    }
}

class Thermostat extends SmartDevice {
    private int temperature;

    public Thermostat(String id, int temperature) {
        super(id, "Thermostat");
        this.temperature = temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Thermostat " + id + " is set to " + temperature + " degrees.");
    }

    @Override
    public void turnOn() {
        System.out.println("Thermostat " + id + " is now active.");
    }

    @Override
    public void turnOff() {
        System.out.println("Thermostat " + id + " is turned off.");
    }

    public int getTemperature() {
        return this.temperature;
    }
}

class DoorLock extends SmartDevice {
    public DoorLock(String id) {
        super(id, "Door");
    }

    @Override
    public void turnOn() {
        this.status = "locked";
        System.out.println("Door " + id + " is locked.");
    }

    @Override
    public void turnOff() {
        this.status = "unlocked";
        System.out.println("Door " + id + " is unlocked.");
    }
}

// Factory Method for creating devices
class SmartDeviceFactory {
    public static SmartDevice createDevice(String type, String id, int... args) {
        switch (type.toLowerCase()) {
            case "light":
                return new Light(id);
            case "thermostat":
                return new Thermostat(id, args[0]); // args[0] is for initial temperature
            case "door":
                return new DoorLock(id);
            default:
                throw new IllegalArgumentException("Unknown device type.");
        }
    }
}

// Proxy Pattern to control access to devices
class DeviceProxy {
    private SmartDevice device;

    public DeviceProxy(SmartDevice device) {
        this.device = device;
    }

    public void accessDevice(String action) {
        if (action.equalsIgnoreCase("on")) {
            device.turnOn();
        } else if (action.equalsIgnoreCase("off")) {
            device.turnOff();
        } else {
            System.out.println("Invalid action.");
        }
    }

    public String getStatus() {
        return device.getStatus();
    }
}

// Smart Home System Class (Hub for all devices)
class SmartHomeSystem {
    private List<SmartDevice> devices = new ArrayList<>();
    private Map<String, DeviceProxy> deviceProxies = new HashMap<>();

    public void addDevice(SmartDevice device) {
        devices.add(device);
        deviceProxies.put(device.id, new DeviceProxy(device));
    }

    public void controlDevice(String id, String action) {
        if (deviceProxies.containsKey(id)) {
            deviceProxies.get(id).accessDevice(action);
        } else {
            System.out.println("Device not found.");
        }
    }

    public void showStatus() {
        for (SmartDevice device : devices) {
            System.out.println(device.type + " " + device.id + " is " + device.getStatus());
        }
    }
}

// Main Application for user input
public class SmartHomeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SmartHomeSystem homeSystem = new SmartHomeSystem();

        // Adding some devices initially
        SmartDevice light1 = SmartDeviceFactory.createDevice("light", "1");
        SmartDevice thermostat1 = SmartDeviceFactory.createDevice("thermostat", "2", 70);
        SmartDevice door1 = SmartDeviceFactory.createDevice("door", "3");

        homeSystem.addDevice(light1);
        homeSystem.addDevice(thermostat1);
        homeSystem.addDevice(door1);

        while (true) {
            System.out.println("Enter command (addDevice, controlDevice, showStatus, exit): ");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("exit")) {
                break;
            } else if (command.equalsIgnoreCase("addDevice")) {
                System.out.println("Enter device type (light, thermostat, door): ");
                String type = scanner.nextLine();
                System.out.println("Enter device ID: ");
                String id = scanner.nextLine();
                SmartDevice newDevice = SmartDeviceFactory.createDevice(type, id, 70); // Default temp for thermostat
                homeSystem.addDevice(newDevice);
            } else if (command.equalsIgnoreCase("controlDevice")) {
                System.out.println("Enter device ID: ");
                String id = scanner.nextLine();
                System.out.println("Enter action (on/off): ");
                String action = scanner.nextLine();
                homeSystem.controlDevice(id, action);
            } else if (command.equalsIgnoreCase("showStatus")) {
                homeSystem.showStatus();
            }
        }
        scanner.close();
    }
}
