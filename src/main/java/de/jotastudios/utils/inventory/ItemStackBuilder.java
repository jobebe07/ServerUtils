package de.jotastudios.utils.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackBuilder {
    private ItemStack stack;

    public ItemStackBuilder(Material material) {
        this.stack = new ItemStack(material);
    }

    public ItemStackBuilder(ItemStack stack) {
        this.stack = stack;
    }

    public static ItemStackBuilder of(Material m) {
        return new ItemStackBuilder(m);
    }
    public static ItemStackBuilder of(ItemStack i) {
        return new ItemStackBuilder(i);
    }

    public ItemStackBuilder count(int count) {
        this.stack.setAmount(count);
        return this;
    }

    public ItemStackBuilder meta(ItemMeta meta) {
        this.stack.setItemMeta(meta);
        return this;
    }

    public ItemMetaBuilder meta() {
        return new ItemMetaBuilder(this, this.stack.getItemMeta());
    }

    public ItemStack build() {
        this.stack.setItemMeta(this.stack.getItemMeta());

        return this.stack;
    }
}
