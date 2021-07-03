package games.skweekychair.buttonmessenger;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

@SerializableAs("HookedButton")
public class HookedButton implements ConfigurationSerializable {

    final String url;
    final String id;
    final String message;
    
    public HookedButton(String url, String id, String message) {
        this.url = Objects.requireNonNull(url);
        this.id = Objects.requireNonNull(id);
        this.message = Objects.requireNonNull(message);
    }

    public HookedButton(String url, Block block, String message) {
        this(url, getID(block), message);
    }

    public String getID() {
        return this.id;
    }

    public static String getID(Block block) {
        return String.format("%d;%d;%d", block.getX(), block.getY(), block.getZ());
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("url", url);
        result.put("message", message);
        result.put("id", id);
        return result;
    }

    public static HookedButton deserialize(Map<String, Object> args) {
        return new HookedButton((String) args.get("url"), (String) args.get("id"), (String) args.get("message"));
    }

}

// TODO: BUTTONS THAT JUST BROADCAST ON SERVER, NO WEBHOOK?