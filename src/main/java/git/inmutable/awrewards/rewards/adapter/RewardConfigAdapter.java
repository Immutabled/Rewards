package git.inmutable.awrewards.rewards.adapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import git.inmutable.awrewards.rewards.RewardConfig;
import git.inmutable.awrewards.rewards.calendar.CalendarReward;
import git.inmutable.awrewards.rewards.playtime.PlayTimeReward;
import git.inmutable.awrewards.util.gson.JsonAdapter;

import java.util.*;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 10, 13:37
 */
public class RewardConfigAdapter implements JsonAdapter<RewardConfig> {
    /**
     * Convert the instance to a JsonObject
     *
     * @param instance the instance to convert
     * @return the JsonObject
     */
    @Override
    public JsonObject toJson(RewardConfig instance) {
        JsonObject root = new JsonObject();

        JsonArray calendarRewardsJson = new JsonArray();
        for (CalendarReward reward : instance.getCalendarRewards()) {
            calendarRewardsJson.add(new CalendarRewardAdapter().toJson(reward));
        }

        root.add("calendarRewards", calendarRewardsJson);

        JsonArray playTimeRewardsJson = new JsonArray();
        for (PlayTimeReward reward : instance.getPlayTimeRewards()) {
            playTimeRewardsJson.add(new PlayTimeRewardAdapter().toJson(reward));
        }

        root.add("playTimeRewards", playTimeRewardsJson);

        return root;
    }

    /**
     * Convert the JsonObject to an instance.
     *
     * @param json the JsonObject to convert.
     * @return the instance.
     */
    @Override
    public RewardConfig fromJson(JsonObject json) {
        Set<CalendarReward> calendarRewards = new LinkedHashSet<>();
        JsonArray calendarRewardsArray = json.getAsJsonArray("calendarRewards");
        for (JsonElement element : calendarRewardsArray) {
            calendarRewards.add(new CalendarRewardAdapter().fromJson(element.getAsJsonObject()));
        }

        Set<PlayTimeReward> playTimeRewards = new LinkedHashSet<>();
        JsonArray playTimeRewardsArray = json.getAsJsonArray("playTimeRewards");
        for (JsonElement element : playTimeRewardsArray) {
            playTimeRewards.add(new PlayTimeRewardAdapter().fromJson(element.getAsJsonObject()));
        }

        RewardConfig config = new RewardConfig();
        config.setCalendarRewards(calendarRewards);
        config.setPlayTimeRewards(playTimeRewards);

        return config;
    }
}
