package git.inmutable.awrewards.account.listener;

import git.inmutable.awrewards.account.AccountInfo;
import git.inmutable.awrewards.account.AccountRepository;
import git.inmutable.awrewards.registry.AccountRegistry;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.jetbrains.annotations.Nullable;


/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 06, 05:42
 */
@RequiredArgsConstructor
public class AsyncPlayerPreLoginListener implements Listener {

    private final AccountRegistry registry;


    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        final @Nullable AccountInfo account = this.registry.getAccountsById().get(event.getUniqueId());
        final @Nullable AccountRepository repository = this.registry.getRepository();

        if (repository == null) {
            throw new IllegalStateException("Account repository not loaded");
        }

        if (account != null) {
            AccountInfo updated = new AccountInfo(
                    account.id(),
                    account.name(),
                    account.firstLogin(),
                    account.playtime(),
                    System.currentTimeMillis(),
                    account.lastQuit()
            );

            // remove old account
            this.registry.getAccounts().remove(account);
            this.registry.getAccountsById().remove(account.id());
            // add new account
            this.registry.getAccounts().add(updated);
            this.registry.getAccountsById().put(account.id(), updated);
            this.registry.getRepository().updateById(updated);
            return;
        }

        //new account
        AccountInfo newAccount = new AccountInfo(
                event.getUniqueId(),
                event.getName(),
                System.currentTimeMillis(),
                0L,
                System.currentTimeMillis(),
                0L
        );

        this.registry.getAccountsById().put(event.getUniqueId(), newAccount);
        this.registry.getAccounts().add(newAccount);
        this.registry.getRepository().updateById(newAccount);
    }


}
