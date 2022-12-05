package net.archasmiel.dndbot.command.user;

import java.util.Optional;
import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.objects.ManaUser;
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
      ManaController.INSTANCE.discordUserCheck(id);
      String muid = ManaController.INSTANCE.discordUsers.get(id).getManaUserId();

      Optional<ManaUser> manaUser = ManaController.INSTANCE.getManaUser(muid);
      ManaUser user = manaUser.orElseThrow(NoManaUserFound::new);

      user.setCurrMana(user.getMaxMana());
      ManaController.INSTANCE.saveManaUser(muid);

      msg = String.format("<@%s> твоя мана восстановилась, сейчас её запас `%d/%d`",
          interaction.getUser().getId(), user.getCurrMana(), user.getMaxMana());
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(msg).queue();
  }


}
