package games.skweekychair.buttonmessenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WebhookSendTask extends BukkitRunnable {

    private String urlStr;
    private String message;
    private Logger logger;
    private Player player;
    private ButtonMessengerPlugin plugin;
    private HookedButton button;

    public WebhookSendTask(HookedButton button, String message, Logger logger, Player player, ButtonMessengerPlugin plugin) {
        this.urlStr = button.url;
        this.message = message;
        this.logger = logger;
        this.player = player;
        this.plugin = plugin;
        this.button = button;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;
            http.setRequestMethod("POST"); // PUT is another valid option
            http.setDoOutput(true);
            
            JsonObject json = new JsonObject();
            json.addProperty("content", message);

            byte[] out = new Gson().toJson(json).getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }

            if (http.getResponseCode() != HttpURLConnection.HTTP_NO_CONTENT) {
                try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    new WarnAndLogTask(String.format("The webhook for the button at (x;y;z) %s responded with something other than 204 No Content.\nResponse Code: %d\nResponse:\n%s", button.getID(), http.getResponseCode(), response.toString()), player, logger).runTask(plugin);
                }
            }

            http.disconnect();
        } catch (MalformedURLException e) {
            new WarnAndLogTask("The webhook link for the button at (x;y;z) " + button.getID() + " is an invalid url.", player, logger).runTask(plugin);
        } catch (IOException e) {
            new WarnAndLogTask(String.format("An IOException occurred trying to send to a button's webhook.\nButton's location (x;y;z): %s", button.getID()), "An IOException occurred trying to send to this button's webhook with message:\n" + e.getMessage(), player, logger, e).runTask(plugin);

        }
        
    }
    
}

// TODO: FIX GETTING THE RESPONSE
