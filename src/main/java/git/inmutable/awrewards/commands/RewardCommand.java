package git.inmutable.awrewards.commands;

import git.inmutable.awrewards.editor.RewardEditorMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 01, 22:43
 */
public class RewardCommand implements CommandExecutor {

    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
//            sender.sendMessage(Messages.NO_CONSOLE.build());
            return true;
        }
        if (args[0].equalsIgnoreCase("editor")) {
            if (!player.hasPermission("awrewards.editor")) return true;
            new RewardEditorMenu().open(player);
            return true;
        }

        new RewardEditorMenu().open(player);
        return true;
    }
}