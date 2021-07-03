package games.skweekychair.buttonmessenger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public class HookCmd implements TabExecutor {

    ButtonsManager buttons;

    HookCmd(ButtonsManager buttons) {
        this.buttons = buttons;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player != true) {
            sender.sendMessage(ChatColor.RED + "Sorry, but you have to be a player to use this command");
            return true;
        }
        
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "You have to provide a Discord webhook link");
            return false;
        } else if (args.length == 1) {
            sender.sendMessage(ChatColor.RED + "Please provide a message to send to the webhook when the button is pressed");
            return false;
        }
        
        if (!args[0].matches("https:\\/\\/(discord|discordapp)\\.com\\/api\\/webhooks\\/[\\d]{18}\\/[a-zA-Z0-9_-]{68}")) {
            sender.sendMessage(ChatColor.YELLOW + "The provided link may not be a valid Discord webhook link.");
        }

        // TODO: REVERSE ARGS ORDER

        Player player = (Player) sender;

        // TODO: PRECISE TARGETING?
        Block targeted = player.getTargetBlock(null, 5);

        if (!Tag.BUTTONS.isTagged(targeted.getType())) {
            sender.sendMessage(ChatColor.RED + "You need to be looking at a button to hook.");
            return false;
        }
        
        List<String> argsList = Arrays.asList(args);

        String message = String.join(" ", argsList.subList(1, argsList.size()));

        buttons.add(new HookedButton(args[0], targeted, message));

        player.sendMessage(ChatColor.GREEN + "Success!");

        return true;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> returns = new ArrayList<String>();

        if (args.length <= 1) {
            returns.add("The link to the Discord Webhook.");
        } else {
            returns.add("The message to send to the Webhook when a player presses the button.");
        }

        // TODO: OFFER $PLACEHOLDERS AS TAB COMPLETES?

        return returns;
    }
    
}
