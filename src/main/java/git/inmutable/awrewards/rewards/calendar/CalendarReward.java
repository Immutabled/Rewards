package git.inmutable.awrewards.rewards.calendar;

import git.inmutable.awrewards.account.AccountInfo;
import git.inmutable.awrewards.registry.AccountRegistry;
import git.inmutable.awrewards.rewards.Reward;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 09, 10:09
 */
public record CalendarReward(@NonNull CalendarType type, @NonNull String permission, @NonNull Set<String> rewards,
                             @NonNull Set<String> passRewards, @NonNull List<String> lockedLore,
                             @NonNull List<String> availableLore, @NonNull String displayName,
                             @NonNull Material material, @NonNull Integer amount, @NonNull Set<UUID> claimedPlayers,
                             Set<UUID> claimedPassPlayers) implements Reward<CalendarReward> {

    public static CalendarReward empty(CalendarType type) {
        return new CalendarReward(
                type,
                "Example.Permission",
                Collections.emptySet(),
                Collections.emptySet(),
                new ArrayList<>(),
                new ArrayList<>(),
                "Example",
                Material.CLOCK,
                1,
                Collections.emptySet(),
                Collections.emptySet()
        );
    }

    public Long getTimeRemaining(Player player) {
        AccountRegistry accountRegistry = Bukkit.getServer()
                .getServicesManager()
                .load(AccountRegistry.class);

        if (accountRegistry == null) {
            throw new IllegalStateException("Account registry not loaded");
        }

        AccountInfo account = accountRegistry.getAccountsById().get(player.getUniqueId());

        if (account == null) {
            throw new IllegalStateException("Account not found");
        }

        return account.playtime() + (System.currentTimeMillis() - account.lastLogin());
    }

}
