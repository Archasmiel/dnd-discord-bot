package net.archasmiel.dndmanabot.util.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.util.List;
import lombok.Getter;
import net.archasmiel.dndmanabot.BotApplication;

/**
 * Bot configuration data.
 */
@Getter
public class BotConfiguration {

  private static final Configuration CONFIGURATION;

  static {
    String msg = null;
    Configuration config = null;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    try (FileReader fileReader = new FileReader("config.json")) {
      config = gson.fromJson(fileReader, Configuration.class);
      if (config == null) {
        throw new NullPointerException();
      }
      msg = config.toString();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      CONFIGURATION = config;
    }
    BotApplication.LOGGER.info(msg);
  }

  private BotConfiguration() {

  }

  public static String getToken() {
    return CONFIGURATION.getToken();
  }

  public static List<String> getGuilds() {
    return CONFIGURATION.getGuilds();
  }

  public static String getDiscordUsersFolder() {
    return CONFIGURATION.getDiscordUsersFolder();
  }

  public static String getManaUsersFolder() {
    return CONFIGURATION.getManaUsersFolder();
  }

}
