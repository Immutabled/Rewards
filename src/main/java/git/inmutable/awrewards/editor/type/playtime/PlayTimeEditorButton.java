package git.inmutable.awrewards.editor.type.playtime;

import git.inmutable.awrewards.util.Color;
import git.inmutable.awrewards.util.menu.Button;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * @organization Mides Projects
 *
 * @author Inmutable
 * @since mayo 20, 00:08
 */
public class PlayTimeEditorButton extends Button {
    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.CLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Color.parse("&aPlaytime Editor"));
        List<Component> lore = new ArrayList<>();
        lore.add(Color.parse("&aClick to edit playtime."));
        meta.lore(lore);
        item.setItemMeta(meta);

        return item;
    }


    @Override
    public void onClick(Player player, InventoryClickEvent event) {
        new PlayTimeEditorMenu().open(player);
    }
}
