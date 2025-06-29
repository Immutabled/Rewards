package git.inmutable.awrewards.rewards.adapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import git.inmutable.awrewards.rewards.playtime.PlayTimeReward;
import git.inmutable.awrewards.util.gson.JsonAdapter;
import org.bukkit.Material;

import java.util.*;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 10, 13:55
 */
public class PlayTimeRewardAdapter implements JsonAdapter<PlayTimeReward> {
    /**
     * Convert the instance to a JsonObject
     *
     * @param instance the instance to convert
     * @return the JsonObject
     */
    @Override
    public JsonObject toJson(PlayTimeReward instance) {
        JsonObject json = new JsonObject();
        json.addProperty("time", instance.time());
        json.addProperty("permission", instance.permission());

        JsonArray rewards = new JsonArray();
        for (String reward : instance.rewards()) rewards.add(reward);
        json.add("rewards", rewards);

        JsonArray passRewards = new JsonArray();
        for (String passReward : instance.passRewards()) passRewards.add(passReward);
        json.add("passRewards", passRewards);

        JsonArray lockedLore = new JsonArray();
        for (String locked : instance.lockedLore()) lockedLore.add(locked);
        json.add("lockedLore", lockedLore);

        JsonArray availableLore = new JsonArray();
        for (String available : instance.availableLore()) availableLore.add(available);
        json.add("availableLore", availableLore);
        return json;
    }

    /**
     * Convert the JsonObject to an instance.
     *
     * @param json the JsonObject to convert.
     * @return the instance.
     */
    @Override
    public PlayTimeReward fromJson(JsonObject json) {
        String time = json.get("time").getAsString();
        String permission = json.get("permission").getAsString();

        JsonArray rewards = json.get("rewards").getAsJsonArray();
        Set<String> rewardSet = new HashSet<>();
        for (JsonElement reward : rewards) rewardSet.add(reward.getAsString());

        JsonArray passRewards = json.get("passRewards").getAsJsonArray();
        Set<String> passRewardSet = new HashSet<>();
        for (JsonElement passReward : passRewards) passRewardSet.add(passReward.getAsString());

        JsonArray lockedLore = json.get("lockedLore").getAsJsonArray();
        List<String> lockedLoreSet = new ArrayList<>();
        for (JsonElement locked : lockedLore) lockedLoreSet.add(locked.getAsString());

        JsonArray availableLore = json.get("availableLore").getAsJsonArray();
        List<String> availableLoreSet = new ArrayList<>();
        for (JsonElement available : availableLore) availableLoreSet.add(available.getAsString());

        String displayName = json.get("displayName").getAsString();
        Material material = Material.getMaterial(json.get("material").getAsString());
        Integer amount = json.get("amount").getAsInt();


        assert material != null;
        return new PlayTimeReward(
                displayName,
                material,
                amount,
                permission,
                rewardSet,
                passRewardSet,
                lockedLoreSet,
                availableLoreSet,
                Collections.emptySet(),
                Collections.emptySet(),
                Long.parseLong(time)
        );
    }
}
