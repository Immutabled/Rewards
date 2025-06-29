package git.inmutable.awrewards.editor.type.calendar;

import git.inmutable.awrewards.util.Color;
import git.inmutable.awrewards.util.menu.Button;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @organization Mides Projects
 *
 * @author Inmutable
 * @since mayo 20, 00:08
 */
public class CalendarEditorButton extends Button {

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.WRITABLE_BOOK);
        var meta = item.getItemMeta();
        meta.displayName(Color.parse("&aCalendar Editor"));

        List<Component> lore = new ArrayList<>();
        lore.add(Color.parse("&aClick to edit calendars."));
        meta.lore(lore);
        item.setItemMeta(meta);

        return item;
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event) {
        new CalendarEditorMenu().open(player);
    }
}
