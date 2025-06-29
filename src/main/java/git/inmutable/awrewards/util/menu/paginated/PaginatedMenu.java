package git.inmutable.awrewards.util.menu.paginated;

import git.inmutable.awrewards.Main;
import git.inmutable.awrewards.registry.MenuRegistry;
import git.inmutable.awrewards.util.menu.Button;
import git.inmutable.awrewards.util.menu.Menu;
import git.inmutable.awrewards.util.menu.impl.BackButton;
import git.inmutable.awrewards.util.menu.impl.ExitButton;
import lombok.Getter;
import org.bukkit.entity.Player;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class PaginatedMenu extends Menu {
    private int pages = 1;
    private int amount = 18;
    private int page = 1;
    private Menu backMenu = null;

    public void setPage(int page, Player player) {
        this.page = Math.max(1, page);
        this.update(player);
    }


    public PaginatedMenu withBackMenu(Menu menu) {
        this.backMenu = menu;
        return this;
    }

    public int getBackSlot() {
        return 4;
    }

    public AbstractMap.SimpleEntry<Integer, Integer> getPageSlots() {
        return new AbstractMap.SimpleEntry<>(0, 8);
    }

    public int[] getButtonSlots() {
        return new int[0];
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> toReturn = new HashMap<>();

        int[] slots = this.getButtonSlots();
        Map<Integer, Button> buttons = this.getPagedButtons(player);

        this.amount = this.getButtonsPerPage(player);
        this.pages = this.calculatePages(buttons, this.amount);

        if (this.page > this.pages) {
            this.page = this.pages;
        }

        AbstractMap.SimpleEntry<Integer, Integer> pageSlots = this.getPageSlots();
        toReturn.put(pageSlots.getKey(), new PaginatedButton(-1, this));
        toReturn.put(pageSlots.getValue(), new PaginatedButton(1, this));

        if (this.backMenu != null) {
            toReturn.put(this.getBackSlot(), new BackButton(this.backMenu));
        } else {
            toReturn.put(this.getBackSlot(), new ExitButton());
        }

        int range = (this.page - 1) * this.amount;
        int maxRange = this.page * this.amount;

        int index = 0;

        for (int key : buttons.keySet()) {
            if (key >= range && key < maxRange) {
                if (slots.length == 0) {
                    toReturn.put((9 + key) - range, buttons.get(key));
                } else {
                    if (index > (slots.length - 1)) {
                        index = 0;
                    }
                    toReturn.put(slots[index++], buttons.get(key));
                }
            }
        }

        toReturn.putAll(this.getStaticButtons(player));

        return toReturn;
    }

    private int calculatePages(Map<Integer, Button> buttons, int amount) {
        int toReturn = buttons.size() / amount;

        if (buttons.size() - (amount * toReturn) > 0) {
            toReturn++;
        }

        return Math.max(1, toReturn);
    }

    public abstract Map<Integer, Button> getPagedButtons(Player player);
    public abstract int getButtonsPerPage(Player player);

    public Map<Integer, Button> getStaticButtons(Player player) {
        return new HashMap<>();
    }

    public void open(Player player, int page) {
        if (page == 1) {
            super.open(player);
            return;
        }

        this.setPage(page, player);
        MenuRegistry registry = Main.getInstance() != null ? Main.getInstance().getMenuRegistry() : null;
        if (registry == null) throw new IllegalStateException("MenuRegistry not loaded!");
        registry.menus.put(player.getUniqueId(), this);
    }

    public void update(Player player) {
        if (this.inventory == null) return;
        
        this.inventory.clear();
        this.getButtons(player).forEach((slot, button) -> inventory.setItem(slot, button.getItem()));
        player.updateInventory();
    }
}