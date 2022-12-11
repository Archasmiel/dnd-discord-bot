package net.archasmiel.dndmanabot.listener.command.userops;

import java.util.Optional;
import net.archasmiel.dndmanabot.database.ManaController;
import net.archasmiel.dndmanabot.database.objects.ManaUser;
import net.archasmiel.dndmanabot.listener.command.basic.Command;
import net.archasmiel.dndmanabot.util.exception.WrongCommandParameters;
import net.archasmiel.dndmanabot.util.helper.OptionMapper;
import net.archasmiel.dndmanabot.util.helper.UserUtil;
import net.archasmiel.dndmanabot.util.mana.ClassesDnD;
import net.archasmiel.dndmanabot.util.mana.ManaQuad;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

/**
 * Command for stat value changing.
 */
public class ChangeStatCommand extends Command {

  /**
   * Constructor for command.
   * Used for MessageListener one-time creation and other purposes.
   */
  public ChangeStatCommand() {
    super(
        Commands.slash("35changestat", "Команда для изменения параметра").addOptions(
            new OptionData(OptionType.STRING, "name", "Название характеристики", true)
              .addChoice("Уровень", "level")
              .addChoice("Параметр", "param"),
            new OptionData(OptionType.INTEGER, "value", "Кол-во очков/уровень", true))
    );
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    String discordUserId = interaction.getUser().getId();
    String msg;

    try {
      String manaUserId = UserUtil.getManaUserIdOrError(discordUserId);
      ManaUser manaUser = UserUtil.getManaUserOrError(manaUserId);

      Optional<String> statName = OptionMapper.mapToStr(interaction.getOption("name"));
      Optional<Integer> value = OptionMapper.mapToInt(interaction.getOption("value"));
      if (statName.isEmpty() || value.isEmpty()) {
        throw new WrongCommandParameters();
      }
      statCapping(manaUser, statName.get(), value.get());

      Optional<ManaQuad> manaQuadOpt = ClassesDnD.valueOf(manaUser.getClassName())
          .getMana(manaUser.getLevel(), manaUser.getParam());
      ManaQuad manaQuad = manaQuadOpt.orElseThrow(WrongCommandParameters::new);

      manaUser.setMana(manaUser.getCurrMana(), manaQuad.getMaxMana());
      ManaController.INSTANCE.saveDiscordUser(discordUserId);
      ManaController.INSTANCE.saveManaUser(manaUserId);

      msg = String.format("Операцию по изменению параметров завершено, маны теперь `%d + %d = %d`",
          manaQuad.getSpellPoints(), manaQuad.getBonusSpellPoints(), manaQuad.getMaxMana());
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(String.format("<@%s>%n%s", discordUserId, msg)).queue();
  }

  private void statCapping(ManaUser user, String statName, int diff) throws WrongCommandParameters {
    if (statName.equals("level")) {
      user.setLevel(Math.min(Math.max(1, user.getLevel() + diff), 20));
      return;
    }
    if (statName.equals("param")) {
      user.setParam(Math.min(Math.max(12, user.getParam() + diff), 51));
      return;
    }
    throw new WrongCommandParameters();
  }

}
