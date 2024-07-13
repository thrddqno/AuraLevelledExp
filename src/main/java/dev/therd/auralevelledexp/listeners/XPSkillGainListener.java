/*
Class forked from AuraMobs by Archy-X with modifications to apply to LevelledMobs
 */

package dev.therd.auralevelledexp.listeners;

import dev.aurelium.auraskills.api.event.skill.EntityXpGainEvent;
import dev.therd.auralevelledexp.AuraLevelledExp;
import dev.therd.auralevelledexp.config.ConfigManager;
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

    @EventHandler(priority = EventPriority.LOW)
    public void onXpGain(EntityXpGainEvent event) {
        LivingEntity entity = event.getAttacked();
        if(!ConfigManager.getInstance().getBoolean(ConfigManager.getInstance().getConfig("config.yml"), "levelled_skills_gain.enabled")) return;

        if(LevelledMobs.getInstance().getLevelInterface().getLevellableState(entity) != LevellableState.ALLOWED) return;

        Player player = event.getPlayer();
        double sourceXp = event.getAmount();
        int mob_level = plugin.getMobLevel(entity);

        if (mob_level <= 0) return;

        double growth_rate = ConfigManager.getInstance().getDouble(ConfigManager.getInstance().getConfig("config.yml"), "levelled_skills_gain.growth_rate");
        String XpGainFormula = "$a + ($b^($c-1))"; //sourcexp + (growth_rate ^ (mob_level - 1))
        CompiledExpression exp = Crunch.compileExpression(XpGainFormula);
        double modifiedXp = exp.evaluate(sourceXp, growth_rate, mob_level);
        getLogger().info(String.valueOf(modifiedXp));
        event.setAmount(modifiedXp);
    }
}
