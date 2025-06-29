package git.inmutable.awrewards.editor.type.playtime.add;

import git.inmutable.awrewards.rewards.RewardConfig;
import git.inmutable.awrewards.rewards.playtime.PlayTimeReward;
import git.inmutable.awrewards.util.Color;
import git.inmutable.awrewards.util.DurationPrompt;
import git.inmutable.awrewards.util.menu.Button;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 19, 00:20
 */
@RequiredArgsConstructor
public class PlayTimeAddButton extends Button {

    private final RewardConfig config;

    @Override
    public ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.EMERALD, 1);
        var meta = itemStack.getItemMeta();
        meta.displayName(Color.parse("&aAdd a Playtime"));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event) {
        new DurationPrompt(duration -> {

            if (duration == null) {
                throw new IllegalStateException("Duration is null");
            }

            config.getPlayTimeRewards().add(PlayTimeReward.empty(duration));
        });
    }
}
