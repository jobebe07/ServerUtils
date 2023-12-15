package de.jotastudios.utils.inventory;

import io.papermc.paper.inventory.ItemRarity;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemStackBuilder {
    private ItemStack stack;
    private Material material;
    private int count;
    private ItemMeta meta;

    public ItemStackBuilder(Material material) {
        this.material = material;

        this.stack = new ItemStack(this.material);
        this.meta = this.stack.getItemMeta();
    }

    public ItemStackBuilder(ItemStack stack) {
        this.material = stack.getType();
        this.count = stack.getAmount();
        this.meta = stack.getItemMeta();

        this.stack = stack;
    }

    public static ItemStackBuilder of(Material m) {
        return new ItemStackBuilder(m);
    }
    public static ItemStackBuilder of(ItemStack i) {
        return new ItemStackBuilder(i);
    }

    public ItemStackBuilder count(int count) {
        this.count = count;
        return this;
    }

    public ItemStackBuilder meta(ItemMeta meta) {
        this.meta = meta;
        return this;
    }

    public ItemMetaBuilder meta() {
        return new ItemMetaBuilder(this);
    }

    public ItemStack build() {
        this.stack.setAmount(this.count);
        this.stack.setItemMeta(this.meta);

        return this.stack;
    }


    public class ItemMetaBuilder {
        private ItemStackBuilder builder;
        private ItemMeta meta;
        private Component displayName;
        private List<ItemFlag> itemFlags;
        private List<Component> lore;


        public ItemMetaBuilder(ItemStackBuilder builder) {
            this.builder = builder;
            ItemMeta meta = builder.meta;



            this.displayName = meta.displayName();
            this.lore = meta.lore();
        }

        public ItemStackBuilder finish() {
            this.meta.displayName(this.displayName);
            this.meta.lore(this.lore);
            this.meta.addItemFlags(this.itemFlags.toArray(new ItemFlag[0]));

            return this.builder.meta(this.meta);
        }

        public ItemMetaBuilder displayName(Component c) {
            this.displayName = c;
            return this;
        }

        public ItemMetaBuilder clearLore() {
            this.lore = new ArrayList<>();
            return this;
        }

        public ItemMetaBuilder lore(Component... lines) {
            this.lore = List.of(lines);
            return this;
        }

        public ItemMetaBuilder clearFlags() {
            this.meta.removeItemFlags(this.itemFlags.toArray(new ItemFlag[0]));
            this.meta.removeItemFlags(this.meta.getItemFlags().toArray(new ItemFlag[0]));
            return this;
        }

         public ItemMetaBuilder flags(ItemFlag... flags) {
            this.itemFlags.addAll(List.of(flags));
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
}
