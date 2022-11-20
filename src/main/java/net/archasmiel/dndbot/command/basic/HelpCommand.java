package net.archasmiel.dndbot.command.basic;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

/**
 * Command with help data.
 */
public class HelpCommand extends Command {

  private static final String MESSAGE = """
      /35signup <class> <level> <param> - задати користувача в системі
      /35stats - отримати дані про користувача
      /35newday - отримати повний обсяг мани (новий день)
      /35cast <spellLevel> - застосувати заклинання та відняти ману
      /35levelup - +1 рівень до персонажа
      /35leveldown - -1 рівень до персонажа
      /35paramup - +1 очко параметра до персонажа
      /35paramdown - -1 очко параметра до персонажа
      """;

  /**
   * Constructor for command.
   * Used for MessageListener one-time creation and other purposes.
   */
  public HelpCommand() {
    super(
        Commands.slash("35help", "Допомога з ботом")
    );
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    interaction.reply(MESSAGE).queue();
  }

}
