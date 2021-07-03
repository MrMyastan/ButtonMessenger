package games.skweekychair.buttonmessenger;

import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class WarnAndLogTask extends BukkitRunnable {

    private String longWarning;
    private String shortWarning;
    private Player player;
    private Logger logger;
    private Exception e = null;

    public WarnAndLogTask(String longWarning, String shortWarning, Player player, Logger logger, Exception e) {
        this.longWarning = longWarning;
        this.shortWarning = shortWarning;
        this.player = player;
        this.logger = logger;
        this.e = e;
    }

    public WarnAndLogTask(String warning, Player player, Logger logger) {
        this(warning, warning, player, logger, null);
    }


    @Override
    public void run() {
        logger.warning(longWarning);
        if (e != null) {
            e.printStackTrace();
        }
        player.sendMessage(ChatColor.RED + "This plugin (ButtonMessenger) encountered a problem, please notify your server admins and send them the following error message (A longer version may be in the logs):");
        player.sendMessage(shortWarning);
    }
    
}
