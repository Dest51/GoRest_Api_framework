package res;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class AppConfig {
    private Properties properties;

    public AppConfig() {
        properties = new Properties();
        try (InputStream inputStream = AppConfig.class.getClassLoader().getResourceAsStream("api-config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
        }
    }

    public String getBearerToken() {
        return properties.getProperty("bearer.token");
    }
}
