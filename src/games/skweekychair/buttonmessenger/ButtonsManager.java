package games.skweekychair.buttonmessenger;

import org.bukkit.configuration.ConfigurationSection;

public class ButtonsManager {
    
    private ConfigurationSection buttonsSection;
    private ButtonMessengerPlugin plugin;

    public ButtonsManager(ConfigurationSection buttonsSection, ButtonMessengerPlugin plugin) {
        this.buttonsSection = buttonsSection;
        this.plugin = plugin;
    }

    public void add(HookedButton button) {
        buttonsSection.set(button.getID(), button);
        plugin.saveConfig();
    }

    public boolean contains(String id) {
        return buttonsSection.getObject(id, HookedButton.class) != null;
    }

    public void remove(String id) {
        buttonsSection.set(id, null);
        plugin.saveConfig();
    }

    public HookedButton get(String id) {
        return buttonsSection.getObject(id, HookedButton.class);
    }

    // TODO: MORE CONVENIENCE METHODS?

}
