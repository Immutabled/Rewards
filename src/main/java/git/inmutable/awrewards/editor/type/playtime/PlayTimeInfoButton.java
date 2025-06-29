package git.inmutable.awrewards.editor.type.playtime;

import git.inmutable.awrewards.rewards.playtime.PlayTimeReward;
import git.inmutable.awrewards.util.Color;
import git.inmutable.awrewards.util.menu.Button;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 19, 00:21
 */
@RequiredArgsConstructor
public class PlayTimeInfoButton extends Button {

    private final @NonNull PlayTimeReward reward;

    @Override
    public ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.CLOCK, 1);
        var meta = itemStack.getItemMeta();
        meta.displayName(Color.parse("&a" + reward));
        List<String> lore  = new ArrayList<>();
        lore.add("");
        lore.add("&6DisplayName &a" + reward.displayName());
        lore.add("&6Time: &a" + reward.time());
        lore.add("&6Pass Rewards: &a" + reward.passRewards());
        lore.add("&6Claimed Players: &a" + reward.claimedPlayers().size());
        lore.add("&6Claimed Pass Players: &a" + reward.claimedPassPlayers().size());
        lore.add("&6Permission: &a" + reward.permission());
        lore.add("");
        lore.add("&aClick to edit this playtime reward.");

        List<Component> components = new ArrayList<>();
        for (String line : lore) {
            components.add(Color.parse(line));
        }

        meta.lore(components);

        itemStack.setItemMeta(meta);

        return null;
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event) {
        //unnecessary
    }
}
