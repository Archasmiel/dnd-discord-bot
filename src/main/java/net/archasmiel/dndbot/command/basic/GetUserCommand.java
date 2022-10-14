package net.archasmiel.dndbot.command.basic;

import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.ManaUser;
import net.archasmiel.dndbot.exception.NoManaUserFound;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class GetUserCommand extends Command {

  public GetUserCommand() {
    super(
      Commands.slash("35stats", "Отримати дані корстувача в системі")
    );
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    String msg;

    try {
      ManaUser user = ManaController.get(interaction.getUser().getId());
      if (user == null) {
        throw new NoManaUserFound();
      }

      msg = String.format("<@%s>, твої хар-ки: %s", interaction.getUser().getId(), user);
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(msg).queue();
  }


}
