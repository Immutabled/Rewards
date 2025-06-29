package git.inmutable.awrewards.rewards;

import git.inmutable.awrewards.Main;
import git.inmutable.awrewards.account.AccountInfo;
import git.inmutable.awrewards.registry.AccountRegistry;
import git.inmutable.awrewards.util.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 08, 09:11
 */
public interface Reward<T> {

    /**
     * The display name of the calendar
     * @return the display name of the calendar
     */
    String displayName();

    /**
     * The material of the calendar
     * @return the material of the calendar
     */
    Material material();

    /**
     * The amount of the calendar
     * @return the amount of the calendar
     */
    Integer amount();

    /**
     * The permission required to use the calendar
     * @return the permission required to use the calendar
     */
    String permission();

    /**
     * The rewards to be given when the calendar is used
     * @return the rewards to be given when the calendar is used
     */
    Set<String> rewards();

    /**
     * The rewards to be given when the calendar is used
     * @return the rewards to be given when the calendar is used
     */
    Set<String> passRewards();

    /**
     * The lore to be displayed when the calendar is locked
     * @return the lore to be displayed when the calendar is locked
     */
    List<String> lockedLore();

    /**
     * The lore to be displayed when the calendar is available
     * @return the lore to be displayed when the calendar is available
     */
    List<String> availableLore();

    Set<UUID> claimedPlayers();
    Set<UUID> claimedPassPlayers();

    default ItemStack getItem(Player player) {
        boolean hasPermission = this.hasPermission(player);
        return new Item(
                (hasPermission ? ChatColor.GREEN : ChatColor.RED) + this.displayName(),
                this.material(),
                this.amount(),
                hasPermission ? this.availableLore() : this.lockedLore()
        ).toItemStack();
    }

    default boolean hasPermission(Player player) {
        Main main = Main.getInstance();
        if (main == null) return false;
        if (main.getConfiguration() == null) return false;

        return player.hasPermission(main.getConfiguration().getPassPermission());
    }

    default boolean hasReached(Player player) {
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

//        return account.playtime() + (System.currentTimeMillis() - account.lastLogin());
        return false;
    }
}