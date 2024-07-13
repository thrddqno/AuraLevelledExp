/*
Class forked from AuraMobs by Archy-X with modifications to apply to LevelledMobs
 */

package dev.therd.auralevelledexp.listeners;

import dev.aurelium.auraskills.api.event.skill.EntityXpGainEvent;
import dev.therd.auralevelledexp.AuraLevelledExp;
import dev.therd.auralevelledexp.config.ConfigManager;
import io.github.arcaneplugins.levelledmobs.LevelInterface;
import io.github.arcaneplugins.levelledmobs.LevelledMobs;
import io.github.arcaneplugins.levelledmobs.enums.LevellableState;
import io.github.arcaneplugins.levelledmobs.libs.crunch.CompiledExpression;
import io.github.arcaneplugins.levelledmobs.libs.crunch.Crunch;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import static org.bukkit.Bukkit.getLogger;

public class XPSkillGainListener implements Listener {

    private final AuraLevelledExp plugin;

    public XPSkillGainListener(AuraLevelledExp plugin){
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onXpGain(EntityXpGainEvent event) {

        final LevelInterface levelInterface = LevelledMobs.getInstance().getLevelInterface();

        LivingEntity entity = event.getAttacked();
        //getLogger().info(String.valueOf(ConfigManager.getInstance().getBoolean(ConfigManager.getInstance().getConfig("config.yml"), "levelled_skills_gain.enabled")));
        if(!ConfigManager.getInstance().getBoolean(ConfigManager.getInstance().getConfig("config.yml"), "levelled_skills_gain.enabled")) return;
        //getLogger().info(String.valueOf(levelInterface.getLevellableState(entity) != LevellableState.ALLOWED));
        if(levelInterface.getLevellableState(entity) != LevellableState.ALLOWED) return;

        Player player = event.getPlayer();
        double sourceXp = event.getAmount();
        int mob_level = plugin.getMobLevel(entity);

        if (mob_level <= 0) return;

        double growth_rate = ConfigManager.getInstance().getInt(ConfigManager.getInstance().getConfig("config.yml"), "levelled_skills_gain.growth_rate");
        String XpGainFormula = "$1 + (1 + $2/100)^$3"; //sourcexp * (1 + mob_lvl/100)^growth_rate
        CompiledExpression exp = Crunch.compileExpression(XpGainFormula);
        double modifiedXp = exp.evaluate(sourceXp, growth_rate , mob_level);
        //getLogger().info(String.valueOf(modifiedXp));
        event.setAmount(modifiedXp);
    }
}
