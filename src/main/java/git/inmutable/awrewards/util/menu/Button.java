package git.inmutable.awrewards.util.menu;

import git.inmutable.awrewards.util.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 19, 00:04
 */
public abstract class Button {
    public abstract ItemStack getItem();
    public abstract void onClick(Player player, InventoryClickEvent event);

    public static Button create(ItemStack item,  @Nullable ButtonAction action) {
        return new Button() {
            @Override
            public ItemStack getItem() {
                return item;
            }

            @Override
            public void onClick(Player player, InventoryClickEvent event) {
                if (action == null) return;
                action.execute(player, event);
            }
        };
    }

    @FunctionalInterface
    public interface ButtonAction {
        void execute(Player player, InventoryClickEvent event);
    }

    public static Button GRAY = create(createItem(Material.GRAY_STAINED_GLASS_PANE), null);
    public static Button PINK = create(createItem(Material.PINK_STAINED_GLASS_PANE), null);


    private static ItemStack createItem(Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Color.parse(" "));
        item.setItemMeta(meta);

        return item;
    }

}