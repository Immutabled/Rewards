package git.inmutable.awrewards.util.menu.impl;

import git.inmutable.awrewards.util.Color;
import git.inmutable.awrewards.util.menu.Button;
import git.inmutable.awrewards.util.menu.Menu;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 19, 00:17
 */
@RequiredArgsConstructor
public class BackButton extends Button {

    private final @NonNull Menu menu;

    @Override
    public ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.REDSTONE, 1);
        var meta = itemStack.getItemMeta();
        meta.displayName(Color.parse("&cBack!"));
        itemStack.setItemMeta(meta);
        return itemStack;
    }


    @Override
    public void onClick(Player player, InventoryClickEvent event)
    {
        this.menu.open(player);
    }
}
