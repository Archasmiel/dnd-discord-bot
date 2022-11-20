package net.archasmiel.dndbot.command.basic;

import java.util.Optional;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.ManaUser;
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
      Optional<ManaUser> manaUser = ManaController.INSTANCE.get(id);
      ManaUser user = manaUser.orElseThrow(NoManaUserFound::new);

      msg = String.format("<@%s>, твої хар-ки: %s", id, user);
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(msg).queue();
  }


}
