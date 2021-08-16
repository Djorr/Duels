package nl.djorr.duels.utils.effects;

import nl.djorr.duels.Duels;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Player;

public class KillEffect {
    private Duels plugin;
    public KillEffect(Duels Duels) {
        plugin = Duels;
    }

    public static void fwStarEffect(Player player) {
        FireWork.createFireworkEffect(FireworkEffect.builder().flicker(true).trail(true)
                .with(FireworkEffect.Type.STAR).withColor(Color.YELLOW).build(), player.getLocation().add(0, 1, 0));
    }
}
