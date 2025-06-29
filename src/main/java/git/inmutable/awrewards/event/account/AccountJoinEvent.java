package git.inmutable.awrewards.event.account;

import git.inmutable.awrewards.account.AccountInfo;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 03, 23:59
 */
@Getter
public class AccountJoinEvent extends PlayerEvent {

    private final @NotNull AccountInfo account;

    public AccountJoinEvent(@NotNull Player who, @NotNull AccountInfo account) {
        super(who);
        this.account = account;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    private static final HandlerList handlers = new HandlerList();
}
