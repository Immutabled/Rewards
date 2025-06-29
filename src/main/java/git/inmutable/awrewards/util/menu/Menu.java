package git.inmutable.awrewards.util.menu;

import git.inmutable.awrewards.Main;
import git.inmutable.awrewards.registry.MenuRegistry;
import git.inmutable.awrewards.util.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 19, 00:04
 */
public abstract class Menu implements InventoryHolder {

    protected Inventory inventory;
    public abstract String getTitle();
    public abstract Map<Integer, Button> getButtons(Player player);
    public abstract int getSize();

    public void open(Player player) {
        player.closeInventory();


        inventory = Bukkit.createInventory(this, this.getSize(), Color.parse(this.getTitle()));

        this.getButtons(player).forEach((slot, button) -> inventory.setItem(slot, button.getItem()));

        player.openInventory(inventory);

        MenuRegistry registry = Main.getInstance() != null ? Main.getInstance().getMenuRegistry() : null;
        if (registry == null) throw new IllegalStateException("MenuRegistry not loaded!");
        registry.menus.put(player.getUniqueId(), this);
    }

    public void close(Player player) {
        player.closeInventory();
        MenuRegistry registry = Main.getInstance() != null ? Main.getInstance().getMenuRegistry() : null;
        if (registry == null) throw new IllegalStateException("MenuRegistry not loaded!");
        registry.menus.remove(player.getUniqueId());
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}