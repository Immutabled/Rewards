package git.inmutable.awrewards.editor.type.playtime;

import git.inmutable.awrewards.util.menu.Button;
import git.inmutable.awrewards.util.menu.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 19, 00:16
 */
public class PlayTimeEditorMenu extends Menu {

    @Override
    public String getTitle() {
        return "Play Time Editor";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        int[] slots = {
                0, 1, 2, 3,  5, 6, 7, 8,
                9, 10, 11, 12, 13, 14, 15, 16,

        };
        return buttons;
    }

    @Override
    public int getSize() {
        return 9*4;
    }
}
