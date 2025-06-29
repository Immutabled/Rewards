package git.inmutable.awrewards.editor.type.playtime.edit.buttons;

import git.inmutable.awrewards.rewards.playtime.PlayTimeReward;
import git.inmutable.awrewards.util.menu.Menu;
import git.inmutable.awrewards.util.menu.impl.ListButton;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 19, 14:19
 */
public class PlayTimeRewardsButton extends ListButton<String> {

    private final PlayTimeReward reward;

    public PlayTimeRewardsButton(Menu menu, PlayTimeReward reward) {
        super(menu, 32, 32);
        this.reward = reward;
    }

    @Override
    public List<String> getList() {
        return new ArrayList<>(this.reward.rewards());
    }

    @Override
    public void add(String item, int index, Player player) {

    }

    @Override
    public void remove(String item, int index, Player player) {

    }

    @Override
    public boolean isModifiable() {
        return true;
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
