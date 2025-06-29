package git.inmutable.awrewards.account.listener;

import git.inmutable.awrewards.event.account.AccountJoinEvent;
import git.inmutable.awrewards.registry.AccountRegistry;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 06, 17:13
 */
@RequiredArgsConstructor
public class PlayerJoinListener implements Listener {

    private final @NonNull AccountRegistry registry;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (this.registry.getAccountsById().containsKey(event.getPlayer().getUniqueId())) {
            Bukkit.getServer().getPluginManager().callEvent(
                    new AccountJoinEvent(
                            event.getPlayer(),
                            this.registry.getAccountsById().get(event.getPlayer().getUniqueId())
                    )
            );
        }
    }
}
