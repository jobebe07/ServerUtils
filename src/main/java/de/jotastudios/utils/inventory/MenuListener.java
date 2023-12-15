package de.jotastudios.utils.inventory;

import de.jotastudios.ServerUtils;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.Objects;

public class MenuListener implements Listener {
    private static final ClickType[] PERMITTED_CLICK_TYPES = new ClickType[] {
            ClickType.LEFT,
            ClickType.RIGHT
    };

    private static final InventoryAction[] BLOCKED_ACTIONS = new InventoryAction[] {
            InventoryAction.MOVE_TO_OTHER_INVENTORY,
            InventoryAction.COLLECT_TO_CURSOR
    };

    private static boolean shouldIgnoreEvent(Inventory inv) {
        return !(inv != null &&
                inv.getHolder() != null &&
                inv.getHolder() instanceof Menu &&
                Objects.equals(((Menu) inv.getHolder()).getOwner(), ServerUtils.instance()));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (Arrays.stream(PERMITTED_CLICK_TYPES).noneMatch(type -> type == e.getClick())) {
            e.setResult(Event.Result.DENY);
            return;
        }

        if (Arrays.stream(BLOCKED_ACTIONS).anyMatch(action -> action == e.getAction())) {
            e.setResult(Event.Result.DENY);
        }

        Menu menu = (Menu) e.getClickedInventory().getHolder();
        MenuButton btn = menu.getButton(menu.getCurrentPage(), e.getSlot());
        if(btn != null) {
            btn.click();
        }
    }
}
