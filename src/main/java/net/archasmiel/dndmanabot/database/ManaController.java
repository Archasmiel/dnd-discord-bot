package net.archasmiel.dndmanabot.database;

import java.io.File;
import net.archasmiel.dndmanabot.database.basic.UserMap;
import net.archasmiel.dndmanabot.database.objects.DiscordUser;
import net.archasmiel.dndmanabot.database.objects.ManaUser;
import net.archasmiel.dndmanabot.util.config.BotConfiguration;
import net.archasmiel.dndmanabot.util.helper.ManaUserIdUtil;

/**
 * Controller for all mana operations.
 */
public class ManaController {

  public static final ManaController INSTANCE = new ManaController();

  public final UserMap<ManaUser> manaUsers =
      new UserMap<>(BotConfiguration.getManaUsersFolder(), ManaUser.class);
  public final UserMap<DiscordUser> discordUsers =
      new UserMap<>(BotConfiguration.getDiscordUsersFolder(), DiscordUser.class);

  /**
   * Load all users.
   */
  public void loadData() {
    if (!new File(BotConfiguration.getDiscordUsersFolder()).mkdir()) {
      discordUsers.readData();
    }
    if (!new File(BotConfiguration.getManaUsersFolder()).mkdir()) {
      manaUsers.readData();
      ManaUserIdUtil.INSTANCE.addIds(manaUsers.keySet());
    }
  }

  /**
   * Save user to json file.
   */
  public void saveDiscordUser(String userId) {
    discordUsers.saveData(userId);
  }

  /**
   * Save user to json file.
   */
  public void saveManaUser(String charId) {
    manaUsers.saveData(charId);
  }

}
