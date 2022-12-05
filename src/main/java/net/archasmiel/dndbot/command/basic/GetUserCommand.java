package net.archasmiel.dndbot.command.basic;

import java.util.Optional;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.objects.ManaUser;
import net.archasmiel.dndbot.exception.NoManaUserFound;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

/**
 * Method for getting all users in current mana.json file.
 */
public class GetUserCommand extends Command {

  /**
   * Constructor for command.
   * Used for MessageListener one-time creation and other purposes.
   */
  public GetUserCommand() {
    super(
        Commands.slash("35stats", "Отримати дані корстувача в системі")
    );
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    String id = interaction.getUser().getId();
    String msg;

    try {
      ManaController.INSTANCE.discordUserCheck(id);
      String muid = ManaController.INSTANCE.discordUsers.get(id).getManaUserId();
      Optional<ManaUser> manaUser = ManaController.INSTANCE.getManaUser(muid);
      ManaUser user = manaUser.orElseThrow(NoManaUserFound::new);

      msg = String.format("`%s`, хар-ки: %s", muid, user);
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(msg).queue();
  }


}
