package dk.mintprison.mintlogs.commands;

import dk.mintprison.mintlogs.config.ConfigManager;
import dk.mintprison.mintlogs.guis.MainGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LogCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender player, Command cmd, String label, String[] args) {
        if (player.isOp()){
            if (args.length > 0){
                if(!(player instanceof Player)){
                    CommandSender p = player;
                    if (args[0].equalsIgnoreCase("set")) {
                        if (args.length == 3) {
                            String item = args[1];
                            Integer antal = 0;
                            try{
                                antal = Integer.parseInt(args[2]);
                                if (item.startsWith("head:")){
                                    ConfigManager.setHeads(item.replace("head:", ""), antal);
                                } else if (item.startsWith("item-")) {
                                    ConfigManager.setItems(item.replace("item-", "").toUpperCase(), antal);
                                }
                                p.sendMessage("§aDu satte §b"+item+"§as drops til §b"+antal+"§a.");
                            } catch (NumberFormatException e){
                                p.sendMessage("§cDu skal skrive et tal!");
                            }
                        } else {
                            p.sendMessage("§cUkendt kommando!");
                            p.sendMessage("§cBrug: /log <gui/list/help/set/add> (id) (antal)");
                        }
                    }  else if (args[0].equalsIgnoreCase("add")) {
                        if (args.length == 3) {
                            String item = args[1].toLowerCase();
                            Integer antal = 0;
                            int før = 0;
                            try{
                                antal = Integer.parseInt(args[2]);
                                if (item.startsWith("head:")){
                                    før = ConfigManager.getInt("heads."+item.replace("head:",""));
                                    ConfigManager.setHeads(item.replace("head:", ""), (antal+før));
                                } else if (item.startsWith("item-")) {
                                    før = ConfigManager.getInt("items."+item.replace("item-",""));
                                    ConfigManager.setItems(item.replace("item-", "").toUpperCase(), (antal+før));
                                }
                                p.sendMessage("§aDu tilføjede §b"+(antal+før)+"§a til §b"+item+"§as drops.");
                            } catch (NumberFormatException e){
                                p.sendMessage("§cDu skal skrive et tal!");
                            }
                        } else {
                            p.sendMessage("§cUkendt kommando!");
                            p.sendMessage("§cBrug: /log <gui/list/help/set/add> (id) (antal)");
                        }
                    } else {
                        p.sendMessage("§cUkendt kommando!");
                    }
                } else {
                    Player p = (Player) player;
                    if (args[0].equalsIgnoreCase("gui") || args[0].equalsIgnoreCase("menu")){
                        MainGui.mainGui(p);
                    } else if (args[0].equalsIgnoreCase("list")) {
                        p.sendMessage("§bHeads §8(" + ConfigManager.getHeads().size() + ")§e: " + ConfigManager.getHeads());
                        p.sendMessage("§7§olooper alle værdier..");
                        for (String id : ConfigManager.getHeads()){
                            p.sendMessage("§bLoopet værdi: §e" + id);
                        }
                    } else if (args[0].equalsIgnoreCase("help")) {
                        p.sendMessage("§7Tilføj heads: §b/logs add head:<id> (antal)");
                        p.sendMessage("§7Eksempel: §b/logs add head:1 2");
                        p.sendMessage("§a");
                        p.sendMessage("§7Tilføj items: §b/logs add item-<id> (antal)");
                        p.sendMessage("§7Eksempel: §b/logs add item-RED_ROSE:1 2");
                        p.sendMessage("§b");
                        p.sendMessage("§7Sæt heads: §b/logs set head:<id> (antal)");
                        p.sendMessage("§7Eksempel: §b/logs set head:1 2");
                        p.sendMessage("§c");
                        p.sendMessage("§7Sæt items: §b/logs set item-<id> (antal)");
                        p.sendMessage("§7Eksempel: §b/logs set item-RED_ROSE:1 2");
                        p.sendMessage("§d");
                        p.sendMessage("§7Åben logs: §b/logs gui");
                    } else if (args[0].equalsIgnoreCase("set")) {
                        if (args.length == 3) {
                            String item = args[1];
                            Integer antal = 0;
                            try{
                                antal = Integer.parseInt(args[2]);
                                if (item.startsWith("head:")){
                                    ConfigManager.setHeads(item.replace("head:", ""), antal);
                                } else if (item.startsWith("item-")) {
                                    ConfigManager.setItems(item.replace("item-", "").toUpperCase(), antal);
                                }
                                p.sendMessage("§aDu satte §b"+item+"§as drops til §b"+antal+"§a.");
                            } catch (NumberFormatException e){
                                p.sendMessage("§cDu skal skrive et tal!");
                            }
                        } else {
                            p.sendMessage("§cUkendt kommando!");
                            p.sendMessage("§cBrug: /log <gui/list/help/set/add> (id) (antal)");
                        }
                    }  else if (args[0].equalsIgnoreCase("add")) {
                        if (args.length == 3) {
                            String item = args[1].toLowerCase();
                            Integer antal = 0;
                            int før = 0;
                            try{
                                antal = Integer.parseInt(args[2]);
                                if (item.startsWith("head:")){
                                    før = ConfigManager.getInt("heads."+item.replace("head:",""));
                                    ConfigManager.setHeads(item.replace("head:", ""), (antal+før));
                                } else if (item.startsWith("item-")) {
                                    før = ConfigManager.getInt("items."+item.replace("item-",""));
                                    ConfigManager.setItems(item.replace("item-", "").toUpperCase(), (antal+før));
                                }
                                p.sendMessage("§aDu tilføjede §b"+(antal+før)+"§a til §b"+item+"§as drops.");
                            } catch (NumberFormatException e){
                                p.sendMessage("§cDu skal skrive et tal!");
                            }
                        } else {
                            p.sendMessage("§cUkendt kommando!");
                            p.sendMessage("§cBrug: /log <gui/list/help/set/add> (id) (antal)");
                        }
                    } else {
                        p.sendMessage("§cUkendt kommando!");
                    }
                }
            } else {
                player.sendMessage("§cUkendt kommando!");
                player.sendMessage("§cBrug: /log <gui/list/help/set/add> (id) (antal)");
            }
        } else { player.sendMessage("§cDu har ikke adgang til denne kommando!"); }
        return false;
    }
}
