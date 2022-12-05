package net.archasmiel.dndbot.command.stats;

import java.util.Optional;
import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.objects.ManaUser;
import net.archasmiel.dndbot.exception.IllegalParameters;
import net.archasmiel.dndbot.exception.NoManaUserFound;
import net.archasmiel.dndbot.util.ClassesDnD;
import net.archasmiel.dndbot.util.ManaQuad;
import net.archasmiel.dndbot.util.OptionMapper;
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
              .addChoice("level", "level").addChoice("param", "param"),
            new OptionData(OptionType.INTEGER, "value", "Кол-во очков/уровень", true)
      )
    );
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    String id = interaction.getUser().getId();
    String msg;

    try {
      ManaController.INSTANCE.discordUserCheck(id);
      String muid = ManaController.INSTANCE.discordUsers.get(id).getManaUserId();
      Optional<ManaUser> manaUser = ManaController.INSTANCE.getManaUser(muid);
      ManaUser user = manaUser.orElseThrow(NoManaUserFound::new);

      Optional<String> statName = OptionMapper.INSTANCE.mapToStr(interaction.getOption("name"));
      Optional<Integer> value = OptionMapper.INSTANCE.mapToInt(interaction.getOption("value"));
      if (statName.isEmpty() || value.isEmpty()) {
        throw new IllegalParameters();
      }

      statCapping(user, statName.get(), value.get());
      Optional<ManaQuad> manaQuadOpt = ClassesDnD.valueOf(user.getClassName())
          .getMana(user.getLevel(), user.getParam());
      ManaQuad manaQuad = manaQuadOpt.orElseThrow(IllegalParameters::new);
      user.setMana(manaQuad.getMaxMana());
      ManaController.INSTANCE.saveDiscordUser(id);
      ManaController.INSTANCE.saveManaUser(muid);

      msg = String.format("<@%s>, операцию завершено, маны теперь %d + %d = %d",
          id, manaQuad.getSpellPoints(),
          manaQuad.getBonusSpellPoints(), manaQuad.getMaxMana());
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(msg).queue();
  }

  private void statCapping(ManaUser user, String statName, int diff) throws IllegalParameters {
    if (statName.equals("level")) {
      user.setLevel(Math.min(Math.max(1, user.getLevel() + diff), 20));
      return;
    }
    if (statName.equals("param")) {
      user.setParam(Math.min(Math.max(12, user.getParam() + diff), 51));
      return;
    }
    throw new IllegalParameters();
  }

}
