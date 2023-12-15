package de.jotastudios;

import de.jotastudios.utils.inventory.ItemStackBuilder;
import de.jotastudios.utils.inventory.Menu;
import de.jotastudios.utils.inventory.MenuButton;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ItemStack stack = ItemStackBuilder.of(Material.TRIDENT)
                .count(50)
                    .meta()
                .displayName(Component.text("das mächtige rohr"))
                .lore(Component.text("fancy desc"))
                .enchant(Enchantment.DURABILITY)
                .flags(ItemFlag.HIDE_ATTRIBUTES)
                    .finish()
                .build();

        ItemStack way2 = new ItemStack(Material.TRIDENT, 50);
        ItemMeta meta = way2.getItemMeta();
        meta.displayName(Component.text("ein name"));
        meta.lore(new ArrayList<>() {{add(Component.text("fancy desc"));}});
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        way2.setItemMeta(meta);

        Menu menu = new Menu(Component.text("Inv"), 3, true);
        MenuButton btn = new MenuButton(Material.ALLIUM, Component.text("Hi"),
                List.of(Component.text("some lore")), false);
        btn.onClick(menuButton -> {
            menuButton.addGlint();
        });
        //btn.onClick(MenuButton::addGlint);
        menu.setButton(0, 5, btn);


    }
}
