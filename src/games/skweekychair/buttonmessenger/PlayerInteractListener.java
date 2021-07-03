package games.skweekychair.buttonmessenger;

import java.util.Date;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    
    ButtonsManager buttons; 
    Logger logger;
    ButtonMessengerPlugin plugin;

    public PlayerInteractListener(ButtonsManager buttons, Logger logger, ButtonMessengerPlugin plugin) {
        this.buttons = buttons;
        this.logger = logger;
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block clicked = event.getClickedBlock();
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK
            || !Tag.BUTTONS.isTagged(clicked.getType())
            || !buttons.contains(HookedButton.getID(clicked))) {
            return; 
        }

        HookedButton button = buttons.get(HookedButton.getID(clicked));

        String message = button.message;

        message = message.replace("$Player", event.getPlayer().getName());
        message = message.replace("$Time", String.format("%1$tF %1$tT %1$tZ", new Date()));

        Bukkit.broadcastMessage(message);

        new WebhookSendTask(button, message, logger, event.getPlayer(), plugin).runTaskAsynchronously(plugin);
    }

}
