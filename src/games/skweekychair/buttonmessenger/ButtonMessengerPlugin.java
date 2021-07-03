package games.skweekychair.buttonmessenger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public class ButtonMessengerPlugin extends JavaPlugin {
   
    // Fired when plugin is first enabled
    @Override
    public void onEnable() {
        
        saveDefaultConfig();

        ConfigurationSerialization.registerClass(HookedButton.class, "HookedButton");

        ConfigurationSection buttonsSection = getConfig().getConfigurationSection("buttons");
        ButtonsManager buttonsManager = new ButtonsManager(buttonsSection, this);
        

        this.getCommand("hook").setExecutor(new HookCmd(buttonsManager));
        this.getCommand("unhook").setExecutor(new UnhookCmd(buttonsManager));

        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(buttonsManager, getLogger(), this), this);
    }

    // Fired when plugin is disabled
    @Override
    public void onDisable() {
        saveConfig();
    }

}
