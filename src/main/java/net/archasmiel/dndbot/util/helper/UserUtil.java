package net.archasmiel.dndbot.util.helper;

import java.util.Optional;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.objects.DiscordUser;
import net.archasmiel.dndbot.database.objects.ManaUser;
import net.archasmiel.dndbot.util.exception.NoDiscordUserFound;
import net.archasmiel.dndbot.util.exception.NoManaUserFound;

/**
 * Utilities for user operations.
 */
public class UserUtil {

  private UserUtil() {

  }

  /**
   * Method for getting DiscordUser.

   * @param discordUserId Discord user id
   * @return DiscordUser object
   * @throws NoDiscordUserFound if no Discord user found
   */
  public static DiscordUser getDiscordUserOrError(String discordUserId) throws NoDiscordUserFound {
    Optional<DiscordUser> discordUserOpt =
        ManaController.INSTANCE.discordUsers.getUser(discordUserId);
    return discordUserOpt.orElseThrow(NoDiscordUserFound::new);
  }

  /**
   * Method for getting ManaUser.

   * @param manaUserId Discord user id
   * @return ManaUser object
   * @throws NoManaUserFound if no mana user found
   */
  public static ManaUser getManaUserOrError(String manaUserId) throws NoManaUserFound {
    Optional<ManaUser> manaUserOpt =
        ManaController.INSTANCE.manaUsers.getUser(manaUserId);
    return manaUserOpt.orElseThrow(NoManaUserFound::new);
  }

  public static String getManaUserIdOrError(String discordUserId) throws NoDiscordUserFound {
    DiscordUser discordUser = getDiscordUserOrError(discordUserId);
    return discordUser.getManaUserId();
  }

}
