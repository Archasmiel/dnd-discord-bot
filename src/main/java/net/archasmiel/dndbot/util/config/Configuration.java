package net.archasmiel.dndbot.util.config;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Configuration information object.
 */
@Getter
@ToString
@AllArgsConstructor
public class Configuration {

  private final String token;
  private final List<String> guilds;
  private final String discordUsersFolder;
  private final String manaUsersFolder;

}
