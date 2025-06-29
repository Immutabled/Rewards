package git.inmutable.awrewards.util.menu.impl;

import git.inmutable.awrewards.util.Color;
import git.inmutable.awrewards.util.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 19, 14:10
 */
public class ExitButton extends Button {


    @Override
    public ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.RED_BED, 1);
        var meta = itemStack.getItemMeta();
        meta.displayName(Color.parse("&cExit!"));
        itemStack.setItemMeta(meta);
        return itemStack;
    }


    @Override
    public void onClick(Player player, InventoryClickEvent event) {
        player.closeInventory();
    }
}
