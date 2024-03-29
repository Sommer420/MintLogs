package dk.mintprison.mintlogs.guis;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import dk.mintprison.mintlogs.MintLogs;
import dk.mintprison.mintlogs.config.ConfigManager;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemGui {
    public static void itemGui(Player p){
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        PaginatedGui gui = Gui.paginated()
                .title(Component.text("§9Logmenu"))
                .rows(5)
                .pageSize(36) // Set the size you want, or leave it to be automatic.
                .disableAllInteractions()
                .create();
        gui.getFiller().fillBottom(ItemBuilder.from(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7)).setName("§b").asGuiItem());
        gui.setItem(5, 3, ItemBuilder.from(Material.ARROW).setName("§cTilbage.").asGuiItem(event -> {
            gui.previous();
            gui.updateTitle("§9Logmenu §7- Items §8(" + gui.getCurrentPageNum() + "/" + gui.getPagesNum() + ")");
        }));
        gui.setItem(5, 7, ItemBuilder.from(Material.PAPER).setName("§aNæste.").asGuiItem(event -> {
            gui.next();
            gui.updateTitle("§9Logmenu §7- Items §8(" + gui.getCurrentPageNum() + "/" + gui.getPagesNum() + ")");
        }));
        gui.setItem(5, 5, ItemBuilder.from(new ItemStack(Material.INK_SACK, 1, (short) 1)).setName("§cTilbage").asGuiItem(event -> {
            MainGui.mainGui(p);
        }));
        String[] stringList;
        for (String id : ConfigManager.getItems()){
            if (id.contains(":")){
                stringList = id.split(":");
                GuiItem item = ItemBuilder.from(new ItemStack(Material.valueOf(id.replace(":"+stringList[1],"")), 1, Short.parseShort(stringList[1]))).setLore("§fDrops: §b" + MintLogs.logsYML.getString("items."+id)).setName("§b" + id.replace(":"+stringList[1],"")).asGuiItem();
                gui.addItem(item);
            } else {
                GuiItem item = ItemBuilder.from(Material.valueOf(id)).setLore("§fDrops: §b" + MintLogs.logsYML.getString("items."+id)).setName("§b" + id).asGuiItem();
                gui.addItem(item);
            }
        }
        gui.open(p);
        gui.updateTitle("§9Logmenu §7- Items §8(" + gui.getCurrentPageNum() + "/" + gui.getPagesNum() + ")");
    }
}
