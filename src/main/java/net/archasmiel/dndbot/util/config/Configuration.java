package net.archasmiel.dndbot.util.config;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Configuration information object.
 */
@Getter
@AllArgsConstructor
public class Configuration {

  private final String token;
  private final List<String> guilds;
  private final String discordUsersFolder;
  private final String manaUsersFolder;

  @Override
  public String toString() {
    return String.format("Configuration {%n\ttoken=%s%n\tguilds=%s%n\tdiscordUsersFolder=%s"
        + "%n\tmanaUsersFolder=%s%n}", token, guilds, discordUsersFolder, manaUsersFolder);
  }
}
