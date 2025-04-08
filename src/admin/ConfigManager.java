import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// @author valentin

public class ConfigManager {
    private static final String CONFIG_FILE = "src/admin/config.properties";
    private static Properties properties = new Properties();

    static {
        // Chargement du fichier de configuration
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            // En cas d'erreur, les propriétés resteront vides
        }
    }

    public static String getAdminPasswordHash() {
        // Récupère le hash stocké pour le mot de passe administrateur
        return properties.getProperty("admin.password.hash", "");
    }
}
