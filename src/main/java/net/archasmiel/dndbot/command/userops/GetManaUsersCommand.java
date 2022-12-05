package net.archasmiel.dndbot.command.userops;

import java.util.stream.Collectors;
import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.objects.DiscordUser;
import net.archasmiel.dndbot.exception.NoDiscordUserFound;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

/**
 * Method for getting all users.
 */
public class GetManaUsersCommand extends Command {

  /**
   * Constructor for command.
   * Used for MessageListener one-time creation and other purposes.
   */
  public GetManaUsersCommand() {
    super(
        Commands.slash("35statsall", "Все персонажи из системы маны в виде списка")
    );
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    String id = interaction.getUser().getId();
    String msg;

    try {
      ManaController.INSTANCE.discordUserCheck(id);
      DiscordUser discordUser = ManaController.INSTANCE.getDiscordUser(id)
          .orElseThrow(NoDiscordUserFound::new);

      msg = discordUser.getManaUserIds().stream()
          .map(ManaController.INSTANCE.manaUsers::get)
          .map(e -> String.format("`%s`, хар-ки: %s%n", e.getId(), e))
          .collect(Collectors.joining());
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(msg).queue();
  }


}
