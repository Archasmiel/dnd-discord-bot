package net.archasmiel.dndbot.command.manaops;

import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.objects.ManaUser;
import net.archasmiel.dndbot.util.helper.UserUtil;
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
    String discordUserId = interaction.getUser().getId();
    String msg;

    try {
      String manaUserId = UserUtil.getManaUserIdOrError(discordUserId);
      ManaUser manaUser = UserUtil.getManaUserOrError(manaUserId);

      manaUser.setCurrMana(manaUser.getMaxMana());
      ManaController.INSTANCE.saveManaUser(manaUserId);

      msg = String.format("Твоя мана восстановилась, `%s` хар-ки: %s", manaUserId, manaUser);
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(String.format("<@%s>%n%s", discordUserId, msg)).queue();
  }


}
