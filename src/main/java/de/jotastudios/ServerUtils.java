package de.jotastudios;

import de.jotastudios.utils.inventory.ItemCache;
import de.jotastudios.utils.inventory.MenuListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerUtils {
    private static JavaPlugin instance;
    public static void initialize(JavaPlugin plugin) {
        instance = plugin;

        instance.getServer().getPluginManager().registerEvents(new MenuListener(), instance);

        ItemCache.init();
    }

    public static JavaPlugin instance() {
        return instance;
    }
}
