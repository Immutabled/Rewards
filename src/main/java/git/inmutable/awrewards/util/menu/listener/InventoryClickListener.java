package git.inmutable.awrewards.util.menu.listener;

import git.inmutable.awrewards.registry.MenuRegistry;
import git.inmutable.awrewards.util.menu.Button;
import git.inmutable.awrewards.util.menu.Menu;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 19, 00:06
 */
@RequiredArgsConstructor
public class InventoryClickListener implements Listener {

    private final MenuRegistry registry;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        Menu menu = this.registry.menus.get(player.getUniqueId());

        if (menu == null) return;
        if (event.getClickedInventory() == null) return;
        if (!event.getClickedInventory().equals(menu.getInventory())) return;

        event.setCancelled(true);

        Button button = menu.getButtons(player).get(event.getSlot());
        if (button != null) {
            button.onClick(player, event);
        }
    }
}
