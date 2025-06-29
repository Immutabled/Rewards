package git.inmutable.awrewards.editor.type.calendar;

import git.inmutable.awrewards.rewards.calendar.CalendarReward;
import git.inmutable.awrewards.util.Color;
import git.inmutable.awrewards.util.TimeUtil;
import git.inmutable.awrewards.util.menu.Button;
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
public class CalendarInfoButton extends Button {

    private final CalendarReward reward;

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.BOOKSHELF);
        var meta = item.getItemMeta();
        meta.displayName(Color.parse("&a" + reward.displayName()));
        List<Component> lore = new ArrayList<>();
        lore.add(Color.parse(" "));
        lore.add(Color.parse("&7This calendar is used to give rewards to "));
        lore.add(Color.parse("&7players who have completed the calendar."));
        lore.add(Color.parse( " "));
        lore.add(Color.parse("&eCurrent: &c" + reward.type().name()));
        lore.add(Color.parse("&eClaimed: &c" + reward.type().name()));

        lore.add(Color.parse( " "));
        lore.add(Color.parse("&aClick to edit calendar rewards"));
        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event) {

    }
}
