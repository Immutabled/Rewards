package git.inmutable.awrewards.editor;

import git.inmutable.awrewards.editor.type.calendar.CalendarEditorButton;
import git.inmutable.awrewards.editor.type.playtime.PlayTimeEditorButton;
import git.inmutable.awrewards.util.menu.Button;
import git.inmutable.awrewards.util.menu.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 18, 12:11
 */
public class RewardEditorMenu extends Menu {

    @Override
    public String getTitle() {
        return "Reward Editor";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        int[] slots =
                {
                        0, 1, 2, 3, 4, 5, 6, 7, 8,
                        9,        13,          17,
                        18, 19, 20, 21, 22, 23, 24, 25, 26,
                };

        for (int slot : slots) buttons.put(slot, Button.PINK);

//        buttons.put(11, Button.create());

        buttons.put(11, new CalendarEditorButton());
        buttons.put(15, new PlayTimeEditorButton());




        return buttons;
    }



    @Override
    public int getSize() {
        return 9*3;
    }
}
