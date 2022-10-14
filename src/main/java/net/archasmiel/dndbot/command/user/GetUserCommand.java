package net.archasmiel.dndbot.command.user;

import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.ManaUser;
import net.archasmiel.dndbot.exception.NoManaUserFound;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class GetUserCommand extends Command {

  public static final Command INSTANCE = new GetUserCommand();
  private static final SlashCommandData DATA =
      Commands.slash("35stats", "Отримати дані корстувача в системі");

  private GetUserCommand() {

  }

  @Override
  public SlashCommandData getData() {
    return DATA;
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
