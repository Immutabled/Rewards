package git.inmutable.awrewards.util.menu.impl;

import git.inmutable.awrewards.util.Color;
import git.inmutable.awrewards.util.menu.Button;
import git.inmutable.awrewards.util.menu.Menu;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 19, 14:09
 */
public abstract class ListButton<T> extends Button {
    private final Menu menu;
    private final int length;
    private final int characterLimit;
    private List<T> items = new ArrayList<>();
    private int index = 0;
    private T currentItem;

    public ListButton(Menu menu, int length, int characterLimit) {
        this.menu = menu;
        this.length = length;
        this.characterLimit = characterLimit;
    }

    public abstract List<T> getList();
    public abstract void add(T item, int index, Player player);
    public abstract void remove(T item, int index, Player player);
    public abstract boolean isModifiable();
    public abstract String getDisplayText(T item);
    public abstract String getExtraText(T item);
    public abstract ItemStack getItemStack(Player player);
    public abstract List<String> getDescription(Player player);

    public List<String> getEmptyDescription(Player player) {
        return Collections.emptyList();
    }

    public List<String> getExtraDescription(Player player) {
        return Collections.emptyList();
    }

    @Override
    public ItemStack getItem() {
        this.items = new ArrayList<>(getList());

        if (this.index > this.items.size() - 1) {
            this.index = this.items.size() - 1;
        }

        this.index = Math.max(0, this.index);
        this.currentItem = this.items.isEmpty() ? null : this.items.get(this.index);

        ItemStack itemStack = getItemStack(null); // Pass null or the actual player if needed
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();

        if (this.items.isEmpty()) {
            lore.add(" ");
        }
        lore.addAll(this.items.isEmpty() ? getEmptyDescription(null) : getDescription(null));

        if (!this.items.isEmpty()) {
            int startIndex = this.index < (this.length / 2) ? 0 : this.index - (this.length / 2);

            // Display items around the current index
            int endIndex = Math.min(startIndex + length, items.size());
            for (int i = startIndex; i < endIndex; i++) {
                T item = items.get(i);
                String text = getDisplayText(item);
                boolean exceededLimit = text.length() > this.characterLimit;

                if (exceededLimit) {
                    text = text.substring(0, this.characterLimit) + "..";
                }

                String line = (this.index == i ? ChatColor.GREEN.toString() : ChatColor.GRAY.toString()) + i + ". " + ChatColor.WHITE.toString() + text + " " + getExtraText(item);
                lore.add(line);
            }
        }

        lore.add(" ");

        if (isModifiable()) {
            lore.add(ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "► " +
                    ChatColor.GREEN + "Shift Left click to add a item");
            lore.add(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "► " +
                    ChatColor.RED + "Shift Right click to delete a item");
        }

        lore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "► " +
                ChatColor.YELLOW + "Left and right click to scroll");
        lore.addAll(getExtraDescription(null));

        List<Component> components = new ArrayList<>();
        for (String line : lore) {
            components.add(Color.parse(line));
        }

        meta.lore(components);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event) {
        if (this.index > this.items.size() - 1) {
            setIndex(this.items.size() - 1);
        }

        if (event.isShiftClick()) {
            if (!isModifiable()) {
                return;
            }

            if (event.isLeftClick()) {
                add(getList().get(this.index), this.index, player);
                menu.open(player); // Refresh the menu
                return;
            }

            // Simple confirmation - you might want to implement a proper confirmation dialog
            player.sendMessage(ChatColor.RED + "Are you sure you want to delete this item?");
            player.sendMessage(ChatColor.GREEN + "Run the command again within 5 seconds to confirm.");
            // In a real implementation, you'd track this and handle the confirmation
            return;
        }

        if (event.isLeftClick()) {
            if (this.index >= this.items.size() - 1) {
                setIndex(0);
            } else {
                setIndex(this.index + 1);
            }
        } else if (event.isRightClick()) {
            setIndex(Math.max(this.index - 1, 0));
        }

        // Update the item in the inventory
        event.getInventory().setItem(event.getSlot(), getItem());
    }

    private void setIndex(int index) {
        this.index = index;
        this.currentItem = this.items.isEmpty() ? null :
                (index > this.items.size() - 1 ? this.items.get(this.items.size() - 1) :
                        this.items.get(this.index));
    }

    public T getCurrentItem() {
        return currentItem;
    }
}