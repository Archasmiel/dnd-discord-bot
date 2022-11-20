package net.archasmiel.dndbot.command.basic;

import java.util.stream.Collectors;
import net.archasmiel.dndbot.database.ManaController;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

/**
 * Method for getting all users.
 */
public class GetAllUsersCommand extends Command {

  /**
   * Constructor for command.
   * Used for MessageListener one-time creation and other purposes.
   */
  public GetAllUsersCommand() {
    super(
        Commands.slash("35statsall", "Отримати всіх користувачів в системі")
    );
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    String msg;

    try {
      msg = ManaController.INSTANCE.users.entrySet().stream()
          .map(e -> String.format("<@%s>, характеристики: %s\n", e.getKey(), e.getValue()))
          .collect(Collectors.joining());
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(msg).queue();
  }


}
