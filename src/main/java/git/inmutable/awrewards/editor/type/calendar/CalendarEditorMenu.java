package git.inmutable.awrewards.editor.type.calendar;

import git.inmutable.awrewards.util.menu.Button;
import git.inmutable.awrewards.util.menu.Menu;
import git.inmutable.awrewards.util.menu.paginated.PaginatedMenu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 19, 00:17
 */
public class CalendarEditorMenu extends PaginatedMenu {

    @Override
    public String getTitle() {
        return "Calendar Editor";
    }

    @Override
    public int getSize() {
        return 9*27;
    }


    @Override
    public Map<Integer, Button> getPagedButtons(Player player) {

        return Map.of();
    }

    @Override
    public int getButtonsPerPage(Player player) {
        return 9;
    }
}
