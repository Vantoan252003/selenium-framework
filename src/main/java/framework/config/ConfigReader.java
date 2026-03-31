package framework.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static ConfigReader instance;
    private Properties properties;

    private ConfigReader() {
        String env = System.getProperty("env", "dev");
        System.out.println("[ConfigReader] Đang dùng môi trường: " + env);
        String configFilePath = "src/test/resources/config-" + env + ".properties";

        properties = new Properties();
        try {
            properties.load(new FileInputStream(configFilePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load config properties from: " + configFilePath);
        }
    }

    public static synchronized ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicit.wait"));
    }

    public int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait", "5"));
    }

    public int getRetryCount() {
        return Integer.parseInt(properties.getProperty("retry.count"));
    }

    public String getScreenshotPath() {
        return properties.getProperty("screenshot.path");
    }

    public String getUsername() {
        String username = System.getenv("APP_USERNAME");
        if (username == null || username.isBlank()) {
            username = properties.getProperty("app.username");
        }
        return username;
    }

    public String getPassword() {
        String password = System.getenv("APP_PASSWORD");
        if (password == null || password.isBlank()) {
            password = properties.getProperty("app.password");
        }
        return password;
    }
}
