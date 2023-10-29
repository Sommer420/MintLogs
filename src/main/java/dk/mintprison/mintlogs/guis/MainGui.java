package dk.mintprison.mintlogs.guis;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MainGui {
    public static void mainGui(Player p){
        Gui gui = Gui.gui()
                .title(Component.text("§9Logside §7- Vælg"))
                .rows(5)
                .disableAllInteractions()
                .create();
        gui.getFiller().fillBorder(ItemBuilder.from(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3)).setName("§b").asGuiItem());
        gui.setItem(3, 4, ItemBuilder.from(Material.SKULL_ITEM).setName("§bHeads").asGuiItem(event -> {
            HeadGui.headGui(p);
        }));
        gui.setItem(3, 6, ItemBuilder.from(Material.CHEST).setName("§bItems").asGuiItem(event -> {
            ItemGui.itemGui(p);
        }));
        gui.open(p);
    }
}
