package git.inmutable.awrewards.util.menu.listener;

import git.inmutable.awrewards.registry.MenuRegistry;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 19, 23:47
 */
@RequiredArgsConstructor
public class InventoryCloseListener implements Listener {

    private final MenuRegistry registry;

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player player)) return;
        this.registry.menus.remove(player.getUniqueId());
    }
}
