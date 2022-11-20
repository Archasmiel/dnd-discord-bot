package net.archasmiel.dndbot.command.roll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collector;
import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.exception.IllegalParameters;
import net.archasmiel.dndbot.util.OptionMapper;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

/**
 * Command for dice rolling.
 */
public class RollDiceCommand extends Command {

  private static final Random RANDOM = new Random();

  /**
   * Constructor for command.
   * Used for MessageListener one-time creation and other purposes.
   */
  public RollDiceCommand() {
    super(
        Commands.slash("35roll", "Допомога з ботом")
          .addOptions(
              new OptionData(OptionType.STRING, "dices", "Кубики які треба заролити", true)
          )
    );
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    String msg;

    try {
      Optional<String> dices = OptionMapper.INSTANCE.mapToStr(interaction.getOption("dices"));
      String dicesUnzip = dices.orElseThrow(IllegalParameters::new);

      List<String> rolls = Arrays.stream(dicesUnzip.split("\\+")).toList();
      long addTo = rolls.stream()
          .filter(e -> !e.contains("d"))
          .map(Long::parseLong)
          .reduce(0L, Long::sum);

      List<Long> rolled = new ArrayList<>();
      rolls.stream()
          .filter(e -> e.contains("d"))
          .forEach(e -> {
            String[] params = e.split("d");
            if (params.length == 2) {
              rolled.addAll(rollDices(params));
            }
          });

      String allRolled = rolled.stream().collect(Collector.of(
          () -> new StringBuilder("`["),
          (stringBuilder, a) -> stringBuilder.append(a).append(", "),
          StringBuilder::append,
          stringBuilder -> {
            if (stringBuilder.length() > 2) {
              stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            }
            return stringBuilder.append("]`").toString();
          }
      ));

      String result = String.format("`[%d]`", rolled.stream().reduce(0L, Long::sum) + addTo);

      msg = String.format("<@%s>, задано `[%s]`, випало %s, результат %s",
          interaction.getUser().getId(), dicesUnzip, allRolled, result);

      if (msg.length() >= 2000) {
        throw new IllegalParameters();
      }
    } catch (IllegalParameters e) {
      msg = e.getMessage();
    }

    interaction.reply(msg).queue();
  }

  private List<Long> rollDices(String[] params) {
    List<Long> list = new ArrayList<>();
    for (int i = 0; i < Integer.parseInt(params[0]); i++) {
      list.add(RANDOM.nextLong(1, Integer.parseInt(params[1]) + 1));
    }
    return list;
  }

}
