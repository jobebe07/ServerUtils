package de.jotastudios;

import de.jotastudios.utils.inventory.ItemStackBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Main {
    public static void main(String[] args) {
        ItemStack stack = ItemStackBuilder.of(Material.TRIDENT)
                .count(50)
                    .meta()
                .displayName(Component.text("das mächtige rohr"))
                .addToLore(Component.text("fancy desc"))
                    .finish()
                .build();
    }
}
