package git.inmutable.awrewards.editor.type.playtime.edit.buttons;

import git.inmutable.awrewards.util.menu.Menu;
import git.inmutable.awrewards.util.menu.impl.ListButton;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 19, 14:18
 */
public class PlayTimeClaimedButton extends ListButton<String> {

    public PlayTimeClaimedButton(Menu menu, int length, int characterLimit) {
        super(menu, length, characterLimit);
    }

    @Override
    public List<String> getList() {
        return List.of();
    }

    @Override
    public void add(String item, int index, Player player) {

    }

    @Override
    public void remove(String item, int index, Player player) {

    }

    @Override
    public boolean isModifiable() {
        return false;
    }

    @Override
    public String getDisplayText(String item) {
        return "";
    }

    @Override
    public String getExtraText(String item) {
        return "";
    }

    @Override
    public ItemStack getItemStack(Player player) {
        return null;
    }

    @Override
    public List<String> getDescription(Player player) {
        return List.of();
    }
}
