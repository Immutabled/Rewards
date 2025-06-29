package git.inmutable.awrewards.editor.type.calendar.add;

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
 * @since mayo 19, 00:20
 */
public class CalendarAddButton extends Button {
    @Override
    public ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.EMERALD, 1);
        var meta = itemStack.getItemMeta();
        meta.displayName(Color.parse("&aAdd Calendar"));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event) {


    }
}
