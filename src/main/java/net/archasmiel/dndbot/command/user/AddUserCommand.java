package net.archasmiel.dndbot.command.user;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;
import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.ManaUserIdGen;
import net.archasmiel.dndbot.database.objects.ManaUser;
import net.archasmiel.dndbot.exception.IllegalParameters;
import net.archasmiel.dndbot.util.ClassesDnD;
import net.archasmiel.dndbot.util.ManaQuad;
import net.archasmiel.dndbot.util.OptionMapper;
import net.dv8tion.jda.api.interactions.commands.Command.Choice;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

/**
 * Creates user on '/35signup [args...]'.
 */
public class AddUserCommand extends Command {

  /**
   * Constructor for command.
   * Used for MessageListener one-time creation and other purposes.
   */
  public AddUserCommand() {
    super(
        Commands.slash("35signup", "Регистрация персонажа в системе").addOptions(
            new OptionData(OptionType.STRING, "class", "Класс персонажа", true)
            .addChoices(
                Arrays.stream(ClassesDnD.values())
                    .map(e -> new Choice(e.getName(), e.getName().toUpperCase()))
                    .toList()
            ),
            new OptionData(OptionType.INTEGER, "level", "Уровень персонажа", true)
            .addChoices(
                IntStream.range(1, 21).boxed()
                    .map(e -> new Choice(Integer.toString(e), e))
                    .toList()
            ),
            new OptionData(OptionType.INTEGER, "param",
                "Основной параметр персонажа, ответственного за касты", true)
      )
    );
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    String id = interaction.getUser().getId();
    String msg;

    try {
      ManaController.INSTANCE.discordUserCheck(id);
      Optional<String> className = OptionMapper.INSTANCE.mapToStr(interaction.getOption("class"));
      Optional<Integer> level = OptionMapper.INSTANCE.mapToInt(interaction.getOption("level"));
      Optional<Integer> param = OptionMapper.INSTANCE.mapToInt(interaction.getOption("param"));
      if (className.isEmpty() || level.isEmpty() || param.isEmpty()) {
        throw new IllegalParameters();
      }

      ManaUser manaUser = new ManaUser(ManaUserIdGen.getId(), null, 0, 0, 0, 0);
      ManaQuad manaQuad = ClassesDnD.valueOf(className.get())
          .getMana(level.get(), param.get())
          .orElseThrow(IllegalParameters::new);

      manaUser.setAll(className.get(), level.get(), param.get(),
          manaQuad.getMaxMana(), manaQuad.getMaxMana());

      ManaController.INSTANCE.manaUsers.put(manaUser.getId(), manaUser);
      ManaController.INSTANCE.discordUsers.get(id).getManaUserIds().add(manaUser.getId());
      ManaController.INSTANCE.discordUsers.get(id).setManaUserId(manaUser.getId());
      ManaController.INSTANCE.saveDiscordUser(id);
      ManaController.INSTANCE.saveManaUser(manaUser.getId());

      msg = String.format("<@%s>, установлено максимальную ману в размере %d + %d = %d",
          interaction.getUser().getId(), manaQuad.getSpellPoints(),
          manaQuad.getBonusSpellPoints(), manaQuad.getMaxMana());
    } catch (Exception e) {
      msg = e.getMessage();
      e.printStackTrace();
    }

    interaction.reply(msg).queue();
  }

}
