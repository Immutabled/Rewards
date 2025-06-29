package git.inmutable.awrewards.util;

import com.destroystokyo.paper.profile.ProfileProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 10, 09:14
 */
@Getter @Setter
@RequiredArgsConstructor
public class Item {

    private final @NonNull String displayName;
    private final @NonNull Material material;
    private final @NonNull Integer amount;
    private final @NonNull List<String> lore;
    private @Nullable String texture = null;


    /**
     * Converts the item to an item stack
     * @return the item stack
     */
    public ItemStack toItemStack() {
        ItemStack itemStack = new ItemStack(material, amount);
        var meta = itemStack.getItemMeta();

        if (meta != null) {
            meta.displayName(Color.parse(displayName));

            List<Component> lore = new ArrayList<>();
            for (String line : this.lore) {
                lore.add(Color.parse(line));
            }
            meta.lore(lore);

            if (material == Material.PLAYER_HEAD && texture != null) {
                if (meta instanceof SkullMeta skullMeta) {
                    applyTexture(skullMeta, texture);
                    itemStack.setItemMeta(skullMeta);
                    return itemStack;
                }
            }

            itemStack.setItemMeta(meta);
        }

        return itemStack;
    }


    public void applyTexture(SkullMeta skullMeta, String base64) {
        var profile = Bukkit.createProfile(UUID.randomUUID());
        profile.setProperty(new ProfileProperty("textures", base64));
        skullMeta.setPlayerProfile(profile);
    }
}
