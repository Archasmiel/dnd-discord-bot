package net.archasmiel.dndbot.command.user;

import java.util.Optional;
import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.ManaUser;
import net.archasmiel.dndbot.exception.NoManaUserFound;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

/**
 * Command for resetting mana (new day).
 */
public class NewDayCommand extends Command {

  /**
   * Constructor for command.
   * Used for MessageListener one-time creation and other purposes.
   */
  public NewDayCommand() {
    super(
        Commands.slash("35newday", "Отримати повний обсяг мани")
    );
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    String id = interaction.getUser().getId();
    String msg;

    try {
      Optional<ManaUser> manaUser = ManaController.INSTANCE.get(id);
      manaUser.orElseThrow(NoManaUserFound::new);
      ManaUser user = manaUser.get();

      user.setCurrMana(user.getMaxMana());
      ManaController.INSTANCE.writeUsers();

      msg = String.format("<@%s> твоя мана оновилась, зараз запас %d/%d",
          interaction.getUser().getId(), user.getCurrMana(), user.getMaxMana());
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(msg).queue();
  }


}
