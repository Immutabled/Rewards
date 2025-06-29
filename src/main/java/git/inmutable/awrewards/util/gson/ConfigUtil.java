package git.inmutable.awrewards.util.gson;

import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for managing JSON configuration files with caching support.
 *
 * <p>Provides methods to register, retrieve, and save JSON configurations
 * associated with plugins. Configurations are cached for efficient access.</p>
 *
 * @organization Arcania Projects
 * @author Inmutable
 * @since May 02, 01:30
 */
public final class ConfigUtil {

    /**
     * Cache of configurations by plugin name and config name.
     */
    private static final Map<String, Map<String, JsonConfig>> cache = new HashMap<>();

    /**
     * Cache of configurations by their class type.
     */
    private static final Map<Class<?>, JsonConfig> byClass = new HashMap<>();

    private ConfigUtil() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    /**
     * Retrieves the first configuration associated with a plugin.
     *
     * @param plugin the name of the plugin
     * @return the first configuration found, or null if none exists
     */
    public static JsonConfig getByPlugin(String plugin) {
        Map<String, JsonConfig> pluginConfigs = cache.get(plugin.toLowerCase());
        return pluginConfigs != null ? pluginConfigs.values().stream().findFirst().orElse(null) : null;
    }

    /**
     * Retrieves a specific configuration by plugin and configuration name.
     *
     * @param plugin the name of the plugin
     * @param name the name of the configuration
     * @return the requested configuration, or null if not found
     */
    public static JsonConfig getByPluginAndName(String plugin, String name) {
        Map<String, JsonConfig> pluginConfigs = cache.get(plugin.toLowerCase());
        return pluginConfigs != null ? pluginConfigs.get(name.toLowerCase()) : null;
    }

    /**
     * Registers a new configuration or loads an existing one from file.
     *
     * @param <T> the type of configuration
     * @param name the name of the configuration
     * @param plugin the name of the plugin
     * @param dataFolder the directory where config files are stored
     * @param config the default configuration instance
     * @return the loaded or created configuration
     * @throws IllegalStateException if the config class is invalid or data folder is not a directory
     * @throws RuntimeException if there's an error reading the config file
     */
    public static <T extends JsonConfig> T register(String name, String plugin, File dataFolder,
                                                    T config) {
        if (!JsonConfig.class.isAssignableFrom(config.getClass())) {
            throw new IllegalStateException(config.getClass().getSimpleName() + " is not a JsonConfig!");
        }

        if (dataFolder.exists() && !dataFolder.isDirectory()) {
            throw new IllegalStateException("Failed to load " + name + ".json: " +
                    dataFolder.getName() + " is not a directory!");
        }

        if (!dataFolder.exists() && !dataFolder.mkdirs()) {
            throw new IllegalStateException("Failed to create directory: " + dataFolder.getAbsolutePath());
        }

        File file = new File(dataFolder, name + ".json");
        T toReturn;

        try {
            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    toReturn = (T) config.getAdapter().fromJson(JsonParser.parseReader(
                                    new InputStreamReader(fis, StandardCharsets.UTF_8))
                            .getAsJsonObject());
                }
            } else {
                toReturn = config;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + file.getAbsolutePath(), e);
        }

        if (toReturn == null) {
            throw new IllegalStateException("Failed to load config file: " + file.getAbsolutePath());
        }

        toReturn.setFile(file);

        cache.computeIfAbsent(plugin.toLowerCase(), k -> new HashMap<>())
                .put(name.toLowerCase(), toReturn);
        byClass.put(config.getClass(), toReturn);

        return toReturn;
    }

    /**
     * Saves a configuration to its associated file.
     *
     * @param <T> the type of configuration
     * @param config the configuration to save
     * @param adapter the JSON adapter for serialization
     * @throws RuntimeException if there's an error writing the file
     */
    public static <T extends JsonConfig> void save(T config) {
        try {
            if (config.getFile().exists() && !config.getFile().delete()) {
                throw new IOException("Failed to delete existing config file");
            }

            try (FileWriter writer = new FileWriter(config.getFile())) {
                writer.write(config.getAdapter().toJson(config).toString());
            }
        } catch (Exception ex) {
            throw new RuntimeException("Failed to save config file: " + config.getFile().getAbsolutePath(), ex);
        }
    }
}