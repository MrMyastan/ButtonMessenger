package games.skweekychair.buttonmessenger;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public class UnhookCmd implements TabExecutor {

    ButtonsManager buttons;

    UnhookCmd(ButtonsManager buttons) {
        this.buttons = buttons;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player != true) {
            sender.sendMessage(ChatColor.RED + "Sorry, but you have to be a player to use this command");
            return true;
        }

        Player player = (Player) sender;

        Block targeted = player.getTargetBlock(null, 5);

        if (!Tag.BUTTONS.isTagged(targeted.getType())) {
            sender.sendMessage(ChatColor.RED + "You need to be looking at a hooked button to unhook.");
            return true;
        }

        if (!buttons.contains(HookedButton.getID(targeted))) {
            sender.sendMessage(ChatColor.RED + "The button you are targeting is not hooked.");
            return true;
        }

        buttons.remove(HookedButton.getID(targeted));

        player.sendMessage(ChatColor.GREEN + "Success!");
        
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> returns = new ArrayList<String>();
        return returns;
    }

}