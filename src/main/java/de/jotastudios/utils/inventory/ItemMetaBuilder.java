package de.jotastudios.utils.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemMetaBuilder {
    private ItemStackBuilder builder;
    private ItemMeta meta;


    public ItemMetaBuilder(ItemStackBuilder builder, ItemMeta meta) {
        this.builder = builder;
        this.meta = meta;
    }

    public ItemStackBuilder finish() {
        return this.builder.meta(this.meta);
    }

    public ItemMetaBuilder displayName(Component c) {
        this.meta.displayName(c);
        return this;
    }

    public ItemMetaBuilder clearLore() {
        this.meta.lore(new ArrayList<>());
        return this;
    }

    public ItemMetaBuilder lore(Component... lines) {
        this.meta.lore(List.of(lines));
        return this;
    }

    public ItemMetaBuilder clearFlags() {
        this.meta.removeItemFlags(this.meta.getItemFlags().toArray(new ItemFlag[0]));
        return this;
    }

    public ItemMetaBuilder flags(ItemFlag... flags) {
        this.meta.addItemFlags(flags);
        return this;
    }

    public ItemMetaBuilder enchant(Enchantment e, int level, boolean ignoreLevelRestriction) {
        this.meta.addEnchant(e, level, ignoreLevelRestriction);
        return this;
    }

    public ItemMetaBuilder enchant(Enchantment e, int level) {
        return this.enchant(e, level, false);
    }

    public ItemMetaBuilder enchant(Enchantment e) {
        return this.enchant(e, 1);
    }

    public ItemMetaBuilder clearEnchant() {
        for(Enchantment e : this.meta.getEnchants().keySet()) {
            this.meta.removeEnchant(e);
        }
        return this;
    }
}