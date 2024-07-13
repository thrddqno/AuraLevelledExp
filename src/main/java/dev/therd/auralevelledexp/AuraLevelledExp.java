package dev.therd.auralevelledexp;

import dev.therd.auralevelledexp.commands.AuraLevelledExpCommands;
import org.bukkit.plugin.java.JavaPlugin;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.therd.auralevelledexp.config.ConfigManager;

public final class AuraLevelledExp extends JavaPlugin {

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).silentLogs(true));
    }

    @Override
    public void onEnable() {
        CommandAPI.onEnable();
        ConfigManager.getInstance().setPlugin(this);
        getLogger().info("Plugin enabled.");
        ConfigManager.getInstance().getConfig("config.yml");
        AuraLevelledExpCommands.registerCommands();
        getLogger().info("Commands registered.");
    }

    @Override
    public void onDisable() {
    }
}
