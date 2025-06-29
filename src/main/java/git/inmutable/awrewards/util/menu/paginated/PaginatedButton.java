package git.inmutable.awrewards.util.menu.paginated;

import git.inmutable.awrewards.util.Color;
import git.inmutable.awrewards.util.menu.Button;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@RequiredArgsConstructor
public class PaginatedButton extends Button {

    private final int increment;
    private final PaginatedMenu parent;


    @Override
    public ItemStack getItem() {
        String symbol = this.increment > 0 ? "⇾" : "⇽";
        ItemStack item;
        ItemMeta meta;

        if (parent.getPage() == 1 && this.increment == -1) {
            item = new ItemStack(Material.LIGHT_GRAY_WOOL);
            meta = item.getItemMeta();
            meta.displayName(Color.parse(ChatColor.GRAY + "First Page (1/" + parent.getPages() + ")"));
        } else if (parent.getPage() == parent.getPages() && this.increment == 1) {
            item = new ItemStack(Material.LIGHT_GRAY_WOOL);
            meta = item.getItemMeta();
            meta.displayName(Color.parse(ChatColor.GRAY + "Last Page (" + parent.getPages() + "/" + parent.getPages() + ")"));
        } else {
            item = new ItemStack(Material.BLUE_WOOL);
            meta = item.getItemMeta();
            meta.displayName(Color.parse(ChatColor.GRAY + symbol + " (" + (parent.getPage() + increment) + "/" + parent.getPages() + ")"));
        }

        item.setItemMeta(meta);
        return item;
    }
    @Override
    public void onClick(Player player, InventoryClickEvent event) {
        if (this.increment < 0 && parent.getPage() == 1) {
            return;
        }

        if (this.increment > 0 && parent.getPage() == parent.getPages()) {
            return;
        }

        parent.setPage(parent.getPage() + increment, player);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PaginatedButton) {
            PaginatedButton other = (PaginatedButton) obj;
            return other.parent == this.parent && other.parent.getPage() == this.parent.getPage();
        }
        return false;
    }
}