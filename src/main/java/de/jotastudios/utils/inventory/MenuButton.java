package de.jotastudios.utils.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.Consumer;

public class MenuButton {
    private ItemStack item;
    private Consumer<MenuButton> onClick;
    public MenuButton(Material m, Component title, List<Component> lore, boolean hasGlint) {
        this.item = ItemStackBuilder.of(m)
                .meta()
                .displayName(title)
                .lore(lore.toArray(new Component[0])).finish().build();
        if(hasGlint) {
            this.addGlint();
        }
    }
    public MenuButton(ItemStack i, boolean hasGlint) {
        this.item = i;
        this.addGlint();
    }

    public void addGlint() {
        this.item = ItemStackBuilder.of(this.item).meta()
                .enchant(Enchantment.DURABILITY)
                .flags(ItemFlag.HIDE_ENCHANTS).finish().build();
    }
    public void removeGlint() {
        this.item = ItemStackBuilder.of(this.item).meta()
                .clearEnchant().finish().build();
    }

    public void onClick(Consumer<MenuButton> c) {
        this.onClick = c;
    }
    public void click() {
        this.onClick.accept(this);
    }

    public ItemStack getItem() {
        return this.item;
    }
}
