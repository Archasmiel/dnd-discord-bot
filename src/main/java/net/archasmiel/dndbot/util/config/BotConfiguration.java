package net.archasmiel.dndbot.util.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.util.List;
import lombok.Getter;

/**
 * Bot configuration data.
 */
@Getter
public class BotConfiguration {

  private static final Configuration CONFIGURATION;

  static {
    Configuration config = null;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    try (FileReader fileReader = new FileReader("token.json")) {
      config = gson.fromJson(fileReader, Configuration.class);
      System.out.printf("%s=%s%n%s=%s%n%s=%s%n%s=%s%n",
          "token", config.getToken(),
          "guilds", config.getGuilds(),
          "discordUsersFolder", config.getDiscordUsersFolder(),
          "manaUsersFolder", config.getManaUsersFolder());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      CONFIGURATION = config;
    }
  }

  private BotConfiguration() {

  }

  public static String token() {
    return CONFIGURATION.getToken();
  }

  public static List<String> guilds() {
    return CONFIGURATION.getGuilds();
  }

  public static String discordUsersFolder() {
    return CONFIGURATION.getDiscordUsersFolder();
  }

  public static String manaUsersFolder() {
    return CONFIGURATION.getManaUsersFolder();
  }

}
