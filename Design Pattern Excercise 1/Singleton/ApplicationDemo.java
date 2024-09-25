import java.util.HashMap;
import java.util.Map;

class ConfigurationManager {
    private static ConfigurationManager instance;
    private Map<String, String> configSettings;

    private ConfigurationManager() {
        configSettings = new HashMap<>();
        loadDefaultSettings();
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    private void loadDefaultSettings() {
        configSettings.put("dbUrl", "jdbc:mysql://localhost:3306/mydb");
        configSettings.put("dbUser", "admin");
        configSettings.put("maxConnections", "100");
        configSettings.put("timeout", "30000");
    }

    public void setSetting(String key, String value) {
        configSettings.put(key, value);
    }

    public String getSetting(String key) {
        return configSettings.get(key);
    }

    public void removeSetting(String key) {
        configSettings.remove(key);
    }

    public void displayConfig() {
        System.out.println("Current Configuration:");
        for (Map.Entry<String, String> entry : configSettings.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

class DatabaseConnection {
    private String url;
    private String username;

    public DatabaseConnection() {
        ConfigurationManager config = ConfigurationManager.getInstance();
        this.url = config.getSetting("dbUrl");
        this.username = config.getSetting("dbUser");
    }

    public void connect() {
        System.out.println("Connecting to database at " + url + " with username " + username);
    }
}

public class ApplicationDemo {
    public static void main(String[] args) {
        ConfigurationManager config = ConfigurationManager.getInstance();
        
        // Display default configuration
        config.displayConfig();
        
        // Modify a setting
        config.setSetting("maxConnections", "150");
        
        // Add a new setting
        config.setSetting("appName", "MyApp");
        
        System.out.println("\nAfter modifications:");
        config.displayConfig();
        
        // Use the configuration in another class
        DatabaseConnection dbConn = new DatabaseConnection();
        dbConn.connect();
        
        // Demonstrate that it's the same instance
        ConfigurationManager anotherConfig = ConfigurationManager.getInstance();
        System.out.println("\nIs it the same instance? " + (config == anotherConfig));
    }
}