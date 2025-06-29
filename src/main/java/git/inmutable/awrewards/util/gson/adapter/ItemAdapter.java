package git.inmutable.awrewards.util.gson.adapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import git.inmutable.awrewards.util.Item;
import git.inmutable.awrewards.util.gson.JsonAdapter;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 10, 09:23
 */
public class ItemAdapter implements JsonAdapter<Item> {
    /**
     * Convert the instance to a JsonObject
     *
     * @param instance the instance to convert
     * @return the JsonObject
     */
    @Override
    public JsonObject toJson(Item instance) {
        JsonObject json = new JsonObject();
        json.addProperty("displayName", instance.getDisplayName());
        json.addProperty("material", instance.getMaterial().name());
        json.addProperty("amount", instance.getAmount());
        JsonArray lore = new JsonArray();
        for (String line : instance.getLore()) {
            lore.add(line);
        }
        json.add("lore", lore);
        json.addProperty("texture", instance.getTexture());
        return null;
    }

    /**
     * Convert the JsonObject to an instance.
     *
     * @param json the JsonObject to convert.
     * @return the instance.
     */
    @Override
    public Item fromJson(JsonObject json) {
        String displayName = json.get("displayName").getAsString();
        Material material = Material.valueOf(json.get("material").getAsString());
        int amount = json.get("amount").getAsInt();

        List<String> lore = new ArrayList<>();
        if (json.has("lore") && json.get("lore").isJsonArray()) {
            json.get("lore").getAsJsonArray().forEach(elem -> lore.add(elem.getAsString()));
        }

        String texture = json.has("texture") ? json.get("texture").getAsString() : null;

        final Item item = new Item(displayName, material, amount, lore);
        item.setTexture(texture);
        return item;
    }
}
