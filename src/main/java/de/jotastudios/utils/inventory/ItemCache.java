package de.jotastudios.utils.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemCache {
    private static Map<String, ItemStack> cache;
    private static ItemStack placeholder;
    public static void init() {
        cache = new HashMap<>();

        // initializing the default placeholder
        placeholder = ItemStackBuilder.of(Material.LIGHT_GRAY_STAINED_GLASS_PANE)
                .meta().displayName(Component.empty()).lore(Component.empty()).finish().build();
    }

    public static ItemStack placeholder() {
        return placeholder;
    }

    public static void setDefaultPlaceholder(ItemStack stack) {
        placeholder = stack;
    }

    public static boolean save(String key, ItemStack stack) {
        if(hasKey(key)) return false;

        cache.put(key, stack);
        return true;
    }

    public static ItemStack get(String key) {
        return cache.get(key);
    }

    public static boolean hasKey(String key) {
        return cache.containsKey(key);
    }

    public static ItemStack delete(String key) {
        return cache.remove(key);
    }

    public static void clear() {
        cache.clear();
    }
}