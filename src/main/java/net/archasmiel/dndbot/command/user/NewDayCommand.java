package net.archasmiel.dndbot.command.user;

import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.ManaUser;
import net.archasmiel.dndbot.exception.NoManaUserFound;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class NewDayCommand extends Command {

  public static final Command INSTANCE = new NewDayCommand();
  private static final SlashCommandData DATA =
      Commands.slash("35newday", "Отримати повний обсяг мани");

  private NewDayCommand() {

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

      user.setCurrMana(user.getMaxMana());
      ManaController.writeUsers();

      msg = String.format("<@%s> твоя мана оновилась, зараз запас %d/%d",
          interaction.getUser().getId(), user.getCurrMana(), user.getMaxMana());
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(msg).queue();
  }


}
