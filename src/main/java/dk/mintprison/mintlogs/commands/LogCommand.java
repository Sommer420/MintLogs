package dk.mintprison.mintlogs.commands;

import dk.mintprison.mintlogs.MintLogs;
import dk.mintprison.mintlogs.config.ConfigManager;
import dk.mintprison.mintlogs.guis.MainGui;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.Console;

public class LogCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.isOp() || sender instanceof ConsoleCommandSender){
            if (args.length > 0){
                if (args[0].equalsIgnoreCase("set")) {
                    if (args.length == 3) {
                        String item = args[1];
                        Integer antal = 0;
                        try{
                            antal = Integer.parseInt(args[2]);
                            if (item.startsWith("head:")){
                                MintLogs.logsYML.set("heads."+item.replace("head:", ""), antal);
                            } else if (item.startsWith("item-")) {
                                MintLogs.logsYML.set("items."+(item.replace("item-", "").toUpperCase()), antal);
                            }
                            sender.sendMessage("§aDu satte §b"+item+"§as drops til §b"+antal+"§a.");
                        } catch (NumberFormatException e){
                            sender.sendMessage("§cDu skal skrive et tal!");
                        }
                    } else {
                        sender.sendMessage("§cUkendt kommando!");
                        sender.sendMessage("§cBrug: /log <gui/list/help/set/add> (id) (antal)");
                    }
                }  else if (args[0].equalsIgnoreCase("add")) {
                    if (args.length == 3) {
                        String item = args[1].toLowerCase();
                        int antal = 0;
                        int før = 0;
                        try{
                            antal = Integer.parseInt(args[2]);
                            if (item.startsWith("head:")){
                                før = MintLogs.logsYML.getInt("heads."+item.replace("head:",""));
                                MintLogs.logsYML.set("heads."+item.replace("head:", ""), (antal+før));
                            } else if (item.startsWith("item-")) {
                                før = MintLogs.logsYML.getInt("items."+(item.replace("item-", "").toUpperCase()));
                                MintLogs.logsYML.set("items."+(item.replace("item-", "").toUpperCase()), (antal+før));
                            }
                            sender.sendMessage("§aDu tilføjede §b"+antal+"§a til §b"+item+"§as drops. §7("+(antal+før)+")");
                        } catch (NumberFormatException e){
                            sender.sendMessage("§cDu skal skrive et tal!");
                        }
                    } else {
                        sender.sendMessage("§cUkendt kommando!");
                        sender.sendMessage("§cBrug: /log <gui/list/help/set/add> (id) (antal)");
                    }
                } else if (args[0].equalsIgnoreCase("gui")) {
                    MainGui.mainGui((Player) sender);
                } else {
                    sender.sendMessage("§cUkendt kommando!");
                }
            } else {
                sender.sendMessage("§cUkendt kommando!");
                sender.sendMessage("§cBrug: /log <gui/list/help/set/add> (id) (antal)");
            }
        } else { sender.sendMessage("§cDu har ikke adgang til denne kommando!"); }
        return false;
    }
}
