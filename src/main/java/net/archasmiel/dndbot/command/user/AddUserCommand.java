package net.archasmiel.dndbot.command.user;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.ManaUser;
import net.archasmiel.dndbot.exception.IllegalParameters;
import net.archasmiel.dndbot.util.Classes;
import net.archasmiel.dndbot.util.ManaQuad;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class AddUserCommand extends Command {

  public static final Command INSTANCE = new AddUserCommand();
  private static final List<OptionData> OPTIONS = List.of(
      new OptionData(OptionType.STRING, "class", "Клас персонажа"),
      new OptionData(OptionType.INTEGER, "level", "Рівень персонажа"),
      new OptionData(OptionType.INTEGER, "param", "Параметр персонажа, відповідний за ману")
  );
  private static final SlashCommandData DATA =
      Commands.slash("35signup", "Зареєструвати свого персонажа в системі")
          .addOptions(OPTIONS);

  private AddUserCommand() {

  }

  @Override
  public SlashCommandData getData() {
    return DATA;
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    String msg;

    try {
      if (interaction.getOptions().size() != 3) {
        throw new IllegalParameters();
      }

      ManaUser user = new ManaUser(0, 0, null, 0, 0);
      String className = Objects.requireNonNull(interaction.getOption("class"))
          .getAsString().toUpperCase();
      int level = Objects.requireNonNull(interaction.getOption("level")).getAsInt();
      int param = Objects.requireNonNull(interaction.getOption("param")).getAsInt();

      Optional<ManaQuad> manaQuad = Classes.valueOf(className).getMana(level, param);
      if (manaQuad.isEmpty()) {
        throw new IllegalParameters();
      }

      user.setAll(className, level, param,
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
