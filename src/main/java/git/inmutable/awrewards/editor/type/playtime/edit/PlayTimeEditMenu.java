package git.inmutable.awrewards.editor.type.playtime.edit;

import git.inmutable.awrewards.util.menu.Button;
import git.inmutable.awrewards.util.menu.Menu;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 19, 01:00
 */
@RequiredArgsConstructor
public class PlayTimeEditMenu extends Menu {
    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        return Map.of();
    }

    @Override
    public int getSize() {
        return 0;
    }
}
