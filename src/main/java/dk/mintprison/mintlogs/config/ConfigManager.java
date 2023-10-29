package dk.mintprison.mintlogs.config;

import dk.mintprison.mintlogs.MintLogs;
import org.bukkit.configuration.ConfigurationSection;

import java.util.*;

public class ConfigManager {
    static HashMap<String, String[]> messages;
    public static void loadALL() {
        messages = new HashMap<>();
        for (String path : MintLogs.logsYML.getKeys(true)) {
            if (!MintLogs.logsYML.isConfigurationSection(path)) {
                if(MintLogs.logsYML.getStringList(path) != null && MintLogs.logsYML.isList(path)) {
                    List<String> stringList = MintLogs.logsYML.getStringList(path);
                    messages.put(path, stringList.toArray(new String[0]));
                    continue;
                }

                if(MintLogs.logsYML.getString(path) != null) {
                    List<String> stringList = Collections.singletonList(MintLogs.logsYML.getString(path));
                    messages.put(path, stringList.toArray(new String[0]));
                }
            }
        }
    }
    public static String[] get(String path) {
        if(messages.containsKey(path)){
            return messages.get(path);
        }
        return new String[] { "" };
    }

    public static String getString(String path) {
        if(messages.containsKey(path)){
            return messages.get(path)[0];
        }
        return "";
    }
    public static Integer getInt(String path) {
        if(messages.containsKey(path)){
            return Integer.valueOf(messages.get(path)[0]);
        }
        return 0;
    }
    public static Boolean getBoolean(String path) {
        if(messages.containsKey(path)){
            return Boolean.valueOf(messages.get(path)[0]);
        }
        return false;
    }

    public static void setHeads(String id, Integer antal){
        MintLogs.logsYML.set("heads."+id, antal);
        MintLogs.logs.saveConfig();
    }

    public static void setItems(String id, Integer antal){
        MintLogs.logsYML.set("items."+id, antal);
        MintLogs.logs.saveConfig();
    }

    public static List<String> getHeads(){
        ConfigurationSection section = MintLogs.logsYML.getConfigurationSection("heads");
        return new ArrayList<>(section.getKeys(false));
    }

    public static List<String> getItems(){
        ConfigurationSection section = MintLogs.logsYML.getConfigurationSection("items");
        return new ArrayList<>(section.getKeys(false));
    }
}
