package net.archasmiel.dndbot.command.basic;

import net.archasmiel.dndbot.util.lang.LangMaps;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

/**
 * Command with help data.
 */
public class HelpCommand extends Command {

  /**
   * Constructor for command.
   * Used for MessageListener one-time creation and other purposes.
   */
  public HelpCommand() {
    super(
        Commands.slash("35help", "Помощь с ботом")
    );
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    String discordUserId = interaction.getUser().getId();
    String msg = LangMaps.HELP_MESSAGE;

    interaction.reply(String.format("<@%s>%n%s", discordUserId, msg)).queue();
  }

}
