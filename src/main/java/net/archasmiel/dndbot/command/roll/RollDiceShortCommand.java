package net.archasmiel.dndbot.command.roll;

import java.util.Random;
import net.archasmiel.dndbot.command.basic.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

/**
 * Shortened roll command.
 */
public class RollDiceShortCommand extends Command {

  private static final Random RANDOM = new Random();

  /**
   * Constructor for command.
   * Used for MessageListener one-time creation and other purposes.
   */
  public RollDiceShortCommand() {
    super(
        Commands.slash("35r", "Зароляти кубики (напр. 3d4+2+2d6+4)")
          .addOptions(
              new OptionData(OptionType.STRING, "dices", "Кубики які треба заролити", true)
          )
    );
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    new RollDiceCommand().process(interaction);
  }

}
