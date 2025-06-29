package git.inmutable.awrewards.expansion;

import git.inmutable.awrewards.Main;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 01, 22:46
 */
@AllArgsConstructor
public class RewardExpansion extends PlaceholderExpansion {

    private @NonNull Main plugin;

    @Override
    public @NotNull String getIdentifier() {
        return this.plugin.getName().toLowerCase();
    }

    @Override
    public @NotNull String getAuthor() {
        return this.plugin.getDescription().getAuthors().getFirst();
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier) {
        if (player == null) {
            plugin.getLogger().warning("Trying to process placeholder with player null for: " + identifier);
            return "";
        }

        return null;
    }
}