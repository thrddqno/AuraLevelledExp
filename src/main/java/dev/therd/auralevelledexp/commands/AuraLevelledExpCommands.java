package dev.therd.auralevelledexp.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.therd.auralevelledexp.config.ConfigManager;

public class AuraLevelledExpCommands {
    public static void registerCommands(){
        // Reload configuration command
        new CommandAPICommand("auralevelledmobs")
                .withAliases("ale", "alexp", "lxp")
                .withSubcommand(new CommandAPICommand("reload")
                        .withPermission(CommandPermission.OP)
                        .executes((sender, args) -> {
                            ConfigManager.getInstance().reloadConfigs();
                            sender.sendMessage("Configuration reloaded.");
                        })
                )
                .withSubcommand(new CommandAPICommand("get")
                        .withSubcommand(new CommandAPICommand("levelled_skills_gain")
                                .executes((sender, args) -> {
                                    sender.sendMessage(String.valueOf(ConfigManager.getInstance().getBoolean(ConfigManager.getInstance().getConfig("config.yml"), "levelled_skills_gain.enabled")));
                                })
                        )
                )
                .register();
    }
}
