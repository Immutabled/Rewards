package git.inmutable.awrewards;

import git.inmutable.awrewards.account.listener.AsyncPlayerPreLoginListener;
import git.inmutable.awrewards.account.listener.PlayerJoinListener;
import git.inmutable.awrewards.account.listener.PlayerQuitListener;
import git.inmutable.awrewards.commands.RewardCommand;
import git.inmutable.awrewards.expansion.RewardExpansion;
import git.inmutable.awrewards.registry.AccountRegistry;
import git.inmutable.awrewards.registry.MenuRegistry;
import git.inmutable.awrewards.util.gson.ConfigUtil;
import git.inmutable.awrewards.util.menu.listener.InventoryClickListener;
import git.inmutable.awrewards.util.menu.listener.InventoryCloseListener;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Objects;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 01, 21:54
 */
@Getter
public final class Main extends JavaPlugin {

    /**
     * The plugin instance
     */
    static @Getter @Nullable Main instance = null;

    /**
     * The account registry instance
     */
    private @Nullable AccountRegistry accountRegistry = null;

    /**
     *  The menu registry instance
     */
    private @Nullable MenuRegistry menuRegistry = null;

    /**
     * The main configuration instance
     */
    private  @Nullable  MainConfig configuration = null;


    /**
     * Method executed when the plugin is enabled
     * This method is used to register the expansion and to load the data of all players
     * also register listeners, service and commands
     */
    @Override
    public void onEnable() {
        // initialize instance of plugin
        if (instance == null) instance = this;

        // load config
        if (configuration == null)  {
            configuration = ConfigUtil.register("config", this.getName(), this.getDataFolder(), new MainConfig(this.getDataFolder()));
            ConfigUtil.save(configuration);
        }

        // load the account registry
        if (accountRegistry == null) {
            AccountRegistry registry = new AccountRegistry(this);
            this.accountRegistry = registry;
            this.getServer().getPluginManager().registerEvents(new AsyncPlayerPreLoginListener(registry), this);
            this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(registry), this);
            this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(registry), this);


            this.getLogger().info("Successfully loaded the Account Registry.");
        }
        if (menuRegistry == null) {
            this.menuRegistry = new MenuRegistry();
            this.getServer().getPluginManager().registerEvents(new InventoryClickListener(this.menuRegistry), this);
            this.getServer().getPluginManager().registerEvents(new InventoryCloseListener(this.menuRegistry), this);
            this.getLogger().info("Successfully loaded the Menu Registry.");
        }


        if (this.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null)
            new RewardExpansion(this).register();


        Objects.requireNonNull(this.getCommand("rewards")).setExecutor(new RewardCommand());
    }

    /**
     * Method executed when the plugin is disabled
     * This method is used to save the data of all players
     */
    @Override
    public void onDisable() {
        if (configuration != null) {
            ConfigUtil.save(configuration);
            this.getLogger().info("Saving config file...");
        }

        if (accountRegistry != null) {
            accountRegistry.close();
            this.getLogger().info("Closing account registry...");
        }

        if (menuRegistry != null) {
            menuRegistry.menus.clear();
            this.getLogger().info("Closing menu registry...");
        }
    }

}