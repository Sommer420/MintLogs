package dk.mintprison.mintlogs.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import dk.mintprison.mintlogs.MintLogs;
import dk.mintprison.mintlogs.config.ConfigManager;
import dk.mintprison.mintlogs.guis.MainGui;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender player, Command cmd, String label, String[] args) {
        if (player.isOp()){
            if (args.length > 0){
                Player p = (Player) player;
                if (args[0].equalsIgnoreCase("gui") || args[0].equalsIgnoreCase("menu")){
                    MainGui.mainGui(p);
                } else if (args[0].equalsIgnoreCase("list")) {
                    p.sendMessage("§bHeads §8(" + ConfigManager.getHeads().size() + ")§e: " + ConfigManager.getHeads());
                    p.sendMessage("§7§olooper alle værdier..");
                    for (String id : ConfigManager.getHeads()){
                        p.sendMessage("§bLoopet værdi: §e" + id);
                    }
                    p.sendMessage("§b");
                    p.sendMessage("§bheads.test11: §e" + ConfigManager.getString("heads.test11"));

                } else if (args[0].equalsIgnoreCase("set")) {
                    if (args.length == 3) {
                        try {
                            Integer antal = Integer.parseInt(args[2]);
                            ConfigManager.setHeads(args[1], antal);
                            p.sendMessage("§bSatte §e" + args[1] + "§bs antal drops til §e" + antal + " heads!");
                        } catch (NumberFormatException e) {
                            p.sendMessage("§cUkendt kommando!");
                            p.sendMessage("§cBrug: /log <gui/list/set/add> (id) (antal)");
                        }
                    } else {
                        p.sendMessage("§cUkendt kommando!");
                        p.sendMessage("§cBrug: /log <gui/list/set/add> (id) (antal)");
                    }
                }  else if (args[0].equalsIgnoreCase("add")) {
                    if (args.length == 3) {
                        if (args[1].startsWith("items:")) {
                            try {
                                Integer antal = Integer.parseInt(args[2]);
                                Integer før = ConfigManager.getInt("items." + args[1]);
                                String item = args[1].replace("items:", "");
                                ConfigManager.setItems(item, antal + før);
                                p.sendMessage("§bTilføjede §e" + antal + "§b drops til itemet §e" + item + "§b!");
                            } catch (NumberFormatException e) {
                                p.sendMessage("§cUkendt kommando!");
                                p.sendMessage("§cBrug: /log <gui/list/set/add> (id) (antal)");
                            }
                        } else {
                            try {
                                Integer antal = Integer.parseInt(args[2]);
                                Integer før = ConfigManager.getInt("heads."+args[1]);
                                ConfigManager.setHeads(args[1], (antal+før));
                                p.sendMessage("§bTilføjede §e" + antal + "§b drops til headet §e" + args[1] + "§b!");
                            } catch (NumberFormatException e) {
                                p.sendMessage("§cUkendt kommando!");
                                p.sendMessage("§cBrug: /log <gui/list/set/add> (id) (antal)");
                            }
                        }
                    } else {
                        p.sendMessage("§cUkendt kommando!");
                        p.sendMessage("§cBrug: /log <gui/list/set/add> (id) (antal)");
                    }
                } else {
                    p.sendMessage("§cUkendt kommando!");
                }
            } else {
                player.sendMessage("§cUkendt kommando!");
                player.sendMessage("§cBrug: /log <gui/list/set/add> (id) (antal)");
            }
        } else { player.sendMessage("§cDu har ikke adgang til denne kommando!"); }
        return false;
    }
}
