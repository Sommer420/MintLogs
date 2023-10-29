package dk.mintprison.mintlogs;

import dk.mintprison.mintlogs.commands.LogCommand;
import dk.mintprison.mintlogs.config.Config;
import dk.mintprison.mintlogs.config.ConfigManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class MintLogs extends JavaPlugin {

    private static MintLogs instance;
    public static Config logs;
    public static FileConfiguration logsYML;

    @Override
    public void onEnable() {
        instance = this;
        if(!(new java.io.File(getDataFolder(), "logs.yml")).exists())saveResource("logs.yml", false);
        logs = new Config(this, null, "logs.yml");
        logsYML = logs.getConfig();
        ConfigManager.loadALL();
        getCommand("logs").setExecutor(new LogCommand());
        /* Reload logs.yml filen:
        MintLogs.logs.reloadConfig();
        MintLogs.logsYML = MintLogs.logs.getConfig();

        logs.saveConfig();
         */
    }
}
