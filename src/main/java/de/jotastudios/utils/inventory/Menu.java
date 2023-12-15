package de.jotastudios.utils.inventory;

import de.jotastudios.ServerUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu implements InventoryHolder {
    private final JavaPlugin owner;
    private int rows;
    private Component title;
    private boolean prefilled;

    private List<Map<Integer, MenuButton>> pages;
    private int currentPage;

    public JavaPlugin getOwner() {
        return this.owner;
    }
    public Component getTitle() {
        return this.title;
    }
    public int getRows() {
        return this.rows;
    }
    @Override
    public @NotNull Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, rows*9, this.getTitle());

        if(this.prefilled) {
            for(int row = 0; row < this.rows; row++) {
                for(int col = 1; col <= 9; col ++) {
                    inventory.setItem(row*9+col, ItemCache.placeholder());
                }
            }
        }

        for(int key : this.pages.get(this.currentPage).keySet()) {
            inventory.setItem(key, this.pages.get(this.currentPage).get(key).getItem());
        }

        return inventory;
    }
    public Menu(Component title, int rows, boolean prefilled) {
        this.owner = ServerUtils.instance();
        this.title = title;
        this.prefilled = prefilled;
        this.rows = rows;
        this.pages = new ArrayList<>();
        this.currentPage = 0;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }
    public void setButton(int pageId, int i, MenuButton button) {
        this.pages.get(pageId).put(i, button);
    }
    public MenuButton getButton(int pageId, int i) {
        return this.pages.get(pageId).get(i);
    }
    public void deleteButton(int pageId, int i) {
        this.pages.get(pageId).remove(i);
    }
}
