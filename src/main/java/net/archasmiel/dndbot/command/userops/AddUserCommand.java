package net.archasmiel.dndbot.command.userops;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;
import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.objects.DiscordUser;
import net.archasmiel.dndbot.database.objects.ManaUser;
import net.archasmiel.dndbot.util.exception.WrongCommandParameters;
import net.archasmiel.dndbot.util.helper.ManaUserIdUtil;
import net.archasmiel.dndbot.util.helper.UserUtil;
import net.archasmiel.dndbot.util.mana.ClassesDnD;
import net.archasmiel.dndbot.util.mana.ManaQuad;
import net.archasmiel.dndbot.util.mana.OptionMapper;
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
    String discordUserId = interaction.getUser().getId();
    String msg;

    try {
      final DiscordUser discordUser = UserUtil.getDiscordUserOrError(discordUserId);

      Optional<String> className = OptionMapper.INSTANCE.mapToStr(interaction.getOption("class"));
      Optional<Integer> level = OptionMapper.INSTANCE.mapToInt(interaction.getOption("level"));
      Optional<Integer> param = OptionMapper.INSTANCE.mapToInt(interaction.getOption("param"));
      if (className.isEmpty() || level.isEmpty() || param.isEmpty()) {
        throw new WrongCommandParameters();
      }

      String manaUserId = ManaUserIdUtil.INSTANCE.getId();
      ManaUser manaUser = new ManaUser(manaUserId, null, 0, 0, 0, 0);
      ManaQuad manaQuad = ClassesDnD.valueOf(className.get())
          .getMana(level.get(), param.get())
          .orElseThrow(WrongCommandParameters::new);

      manaUser.setAll(className.get(), level.get(), param.get(),
          manaQuad.getMaxMana(), manaQuad.getMaxMana());

      ManaController.INSTANCE.manaUsers.put(manaUser.getId(), manaUser);
      discordUser.getManaUserIds().add(manaUser.getId());
      discordUser.setManaUserId(manaUser.getId());
      ManaController.INSTANCE.saveDiscordUser(discordUserId);
      ManaController.INSTANCE.saveManaUser(manaUserId);

      msg = String.format("Пользователь `%s` создан, установлено ману в размере `%d + %d = %d`",
          manaUserId, manaQuad.getSpellPoints(),
          manaQuad.getBonusSpellPoints(), manaQuad.getMaxMana());
    } catch (Exception e) {
      msg = e.getMessage();
      e.printStackTrace();
    }

    interaction.reply(String.format("<@%s>%n%s", discordUserId, msg)).queue();
  }

}
