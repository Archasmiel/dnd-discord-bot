package net.archasmiel.dndbot.command.basic;

import net.archasmiel.dndbot.lang.LangMaps;
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
    interaction.reply(LangMaps.HELP_MESSAGE).queue();
  }

}
