package git.inmutable.awrewards.event.account;

import git.inmutable.awrewards.account.AccountInfo;
import lombok.NonNull;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 01, 23:40
 */
public class AccountQuitEvent extends PlayerEvent {

    private final @NonNull AccountInfo account;

    public AccountQuitEvent(@NonNull Player who, @NonNull AccountInfo account) {
        super(who);
        this.account = account;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    private static final HandlerList handlers = new HandlerList();
}
