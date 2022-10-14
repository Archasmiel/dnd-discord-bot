package net.archasmiel.dndbot.command.basic;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class HelpCommand extends Command {

  public static final Command INSTANCE = new HelpCommand();
  private static final SlashCommandData DATA = Commands.slash("35help", "Допомога з ботом");
  private static final String MESSAGE = """
      /35help - цей гайд-допомога
      /35signup <Class> <level> <param> - задати корстувача в системі
      /35stats - отримати дані корстувача в системі
      /35newday - отримати повний обсяг мани (на новий день)
      /35cast <spellLevel> - закстувати заклинання та відняти ману відносно системи
      /35levelup - +1 рівень до персонажа
      /35leveldown - -1 рівень до персонажа
      /35paramup - +1 очко параметра до персонажа
      /35paramdown - -1 очко параметра до персонажа
      """;

  private HelpCommand() {

  }

  @Override
  public SlashCommandData getData() {
    return DATA;
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    interaction.reply(MESSAGE).queue();
  }


}
