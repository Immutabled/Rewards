package git.inmutable.awrewards;

import com.google.gson.JsonObject;
import git.inmutable.awrewards.util.gson.JsonAdapter;
import git.inmutable.awrewards.util.gson.JsonConfig;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.File;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 04, 05:15
 */
@Getter
@Setter
public class MainConfig extends JsonConfig<MainConfig> {


    /**
     * The time zone to use for the plugin
     */
    private @NonNull String timeZone = "America/New_York";

    /**
     * Whether to send notifications to player
     */
    private @NonNull Boolean notifications = true;

    /**
     * The permission required to use the /pass command
     */
    private @NonNull String passPermission = "awrewards.pass";

    /**
     * Whether to use a cooldown for the /pass command
     */
    private boolean hasCooldown = true;

    public MainConfig(@NonNull File file) {
        super(file, new JsonAdapter<>() {
                    /**
                     * Convert the instance to a JsonObject
                     *
                     * @param instance the instance to convert
                     * @return the JsonObject
                     */
                    @Override
                    public JsonObject toJson(MainConfig instance) {
                        JsonObject json = new JsonObject();
                        json.addProperty("timeZone", instance.getTimeZone());
                        json.addProperty("notifications", instance.getNotifications());
                        json.addProperty("passPermission", instance.getPassPermission());
                        json.addProperty("hasCooldown", instance.isHasCooldown());

                        return json;
                    }

                    /**
                     * Convert the JsonObject to an instance
                     *
                     * @param json the JsonObject to convert
                     * @return the instance
                     */
                    @Override
                    public MainConfig fromJson(JsonObject json) {
                        MainConfig config = new MainConfig(file);
                        if (json.has("timeZone")) config.setTimeZone(json.get("timeZone").getAsString());
                        if (json.has("notifications")) config.setNotifications(json.get("notifications").getAsBoolean());
                        if (json.has("passPermission")) config.setPassPermission(json.get("passPermission").getAsString());
                        if (json.has("hasCooldown")) config.setHasCooldown(json.get("hasCooldown").getAsBoolean());
                        return config;
                    }
                }
        );
    }
}
