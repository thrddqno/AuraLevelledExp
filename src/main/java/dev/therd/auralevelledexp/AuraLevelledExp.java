package dev.therd.auralevelledexp;

import dev.aurelium.auraskills.api.AuraSkillsApi;
import dev.therd.auralevelledexp.commands.AuraLevelledExpCommands;
import dev.therd.auralevelledexp.listeners.XPSkillGainListener;
import io.github.arcaneplugins.levelledmobs.LevelledMobs;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.therd.auralevelledexp.config.ConfigManager;

import java.util.Objects;

public final class AuraLevelledExp extends JavaPlugin {

    AuraSkillsApi auraSkills;

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).silentLogs(true));
    }

    @Override
    public void onEnable() {
        CommandAPI.onEnable();

        auraSkills = AuraSkillsApi.get();

        ConfigManager.getInstance().setPlugin(this);
        getLogger().info("Plugin enabled.");
        ConfigManager.getInstance().getConfig("config.yml");
        AuraLevelledExpCommands.registerCommands();
        getLogger().info("Commands registered.");

        this.getServer().getPluginManager().registerEvents(new XPSkillGainListener(this), this);
    }

    @Override
    public void onDisable() {
    }

    public AuraSkillsApi getAuraSkills() {
        return auraSkills;
    }

    public int getMobLevel(LivingEntity livingEntity){
        Plugin levelledMobsPlugin = Bukkit.getPluginManager().getPlugin("LevelledMobs");
        if (levelledMobsPlugin == null) return 0;
        NamespacedKey levelKey = new NamespacedKey(levelledMobsPlugin, "level");
        return Objects.requireNonNullElse(
                livingEntity.getPersistentDataContainer().get(levelKey, PersistentDataType.INTEGER),
                0
        );
    }
}
