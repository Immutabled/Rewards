package git.inmutable.awrewards.account.listener;

import git.inmutable.awrewards.account.AccountInfo;
import git.inmutable.awrewards.account.AccountRepository;
import git.inmutable.awrewards.event.account.AccountQuitEvent;
import git.inmutable.awrewards.registry.AccountRegistry;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.Nullable;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 06, 06:27
 */
@RequiredArgsConstructor
public class PlayerQuitListener implements Listener {

    private final @NonNull AccountRegistry registry;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        final @Nullable AccountInfo account = this.registry.getAccountsById().get(event.getPlayer().getUniqueId());
        final @Nullable AccountRepository repository = this.registry.getRepository();

        if (repository == null) {
            throw new IllegalStateException("Account repository not loaded");
        }

        if (account != null) {
            AccountInfo updated = new AccountInfo(
                    account.id(),
                    account.name(),
                    account.firstLogin(),
                    account.playtime() + (System.currentTimeMillis() - account.lastLogin()),
                    account.lastLogin(),
                    System.currentTimeMillis()
            );

            // remove old account
            this.registry.getAccounts().remove(account);
            this.registry.getAccountsById().remove(account.id());

            // add new account
            this.registry.getRepository().updateById(updated);
            this.registry.getAccountsById().put(event.getPlayer().getUniqueId(), updated);
            this.registry.getAccounts().add(updated);

            Bukkit.getServer().getPluginManager().callEvent(
                    new AccountQuitEvent(
                            event.getPlayer(),
                            updated
                    )
            );
            return; // return because doesn't need more actions
        }

        throw  new IllegalStateException("Account not found");
    }
}
