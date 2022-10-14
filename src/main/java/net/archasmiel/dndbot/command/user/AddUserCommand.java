package net.archasmiel.dndbot.command.user;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.ManaUser;
import net.archasmiel.dndbot.exception.IllegalParameters;
import net.archasmiel.dndbot.util.Classes;
import net.archasmiel.dndbot.util.ManaQuad;
import net.archasmiel.dndbot.util.OptionMapper;
import net.dv8tion.jda.api.interactions.commands.Command.Choice;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class AddUserCommand extends Command {

  public AddUserCommand() {
    super(
      Commands.slash("35signup", "Зареєструвати свого персонажа в системі").addOptions(
        new OptionData(OptionType.STRING, "class", "Клас персонажа", true)
            .addChoices(
                Arrays.stream(Classes.values())
                    .map(e -> new Choice(e.getName(), e.getName().toUpperCase()))
                    .collect(Collectors.toList())
            ),
        new OptionData(OptionType.INTEGER, "level", "Рівень персонажа", true)
            .addChoices(
                IntStream.range(1, 21).boxed()
                    .map(e -> new Choice(Integer.toString(e), e))
                    .collect(Collectors.toList())
            ),
        new OptionData(OptionType.INTEGER, "param", "Кількість параметра персонажа, відповідного за ману", true)
      )
    );
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    String msg;

    try {
      Optional<String> className = OptionMapper.mapToStr(interaction.getOption("class"));
      Optional<Integer> level = OptionMapper.mapToInt(interaction.getOption("level"));
      Optional<Integer> param = OptionMapper.mapToInt(interaction.getOption("param"));
      if (className.isEmpty() || level.isEmpty() || param.isEmpty()) throw new IllegalParameters();

      ManaUser user = new ManaUser(null, 0, 0, 0, 0);
      Optional<ManaQuad> manaQuad = Classes.valueOf(className.get()).getMana(level.get(), param.get());
      if (manaQuad.isEmpty()) {
        throw new IllegalParameters();
      }

      user.setAll(className.get(), level.get(), param.get(),
          manaQuad.get().getMaxMana(), manaQuad.get().getMaxMana());

      ManaController.USERS.put(interaction.getUser().getId(), user);
      ManaController.writeUsers();

      msg = String.format("<@%s>, встановлено максимальну ману в розмірі %d + %d = %d",
          interaction.getUser().getId(), manaQuad.get().getSpellpoints(),
          manaQuad.get().getBonusSpellpoints(), manaQuad.get().getMaxMana());
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(msg).queue();
  }

}
