package net.archasmiel.dndbot.command.stats;

import java.util.Optional;
import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.ManaUser;
import net.archasmiel.dndbot.exception.IllegalParameters;
import net.archasmiel.dndbot.exception.NoManaUserFound;
import net.archasmiel.dndbot.util.Classes;
import net.archasmiel.dndbot.util.ManaQuad;
import net.archasmiel.dndbot.util.OptionMapper;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class ChangeStatCommand extends Command {

  public ChangeStatCommand() {
    super(
      Commands.slash("35changestat", "Команда для зміни параметра").addOptions(
          new OptionData(OptionType.STRING, "name", "Назва характеристики", true)
              .addChoice("level", "level").addChoice("param", "param"),
          new OptionData(OptionType.INTEGER, "value", "Кількість очків/рівнів", true)
      )
    );
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    String msg;

    try {
      ManaUser user = ManaController.get(interaction.getUser().getId());
      if (user == null) {
        throw new NoManaUserFound();
      }

      Optional<String> statName = OptionMapper.mapToStr(interaction.getOption("name"));
      Optional<Integer> value = OptionMapper.mapToInt(interaction.getOption("value"));
      if (statName.isEmpty() || value.isEmpty()) throw new IllegalParameters();

      statCapping(user, statName.get(), value.get());
      Optional<ManaQuad> manaQuad = Classes.valueOf(user.getClassName())
          .getMana(user.getLevel(), user.getParam());
      if (manaQuad.isEmpty()) throw new IllegalParameters();
      user.setMana(manaQuad.get().getMaxMana());
      ManaController.writeUsers();

      msg = String.format("<@%s>, операцію завершено, мана тепер %d + %d = %d",
          interaction.getUser().getId(), manaQuad.get().getSpellpoints(),
          manaQuad.get().getBonusSpellpoints(), manaQuad.get().getMaxMana());
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(msg).queue();
  }

  private void statCapping(ManaUser user, String statName, int diff) throws IllegalParameters {
    if (statName.equals("level")) {
      user.setLevel( Math.min(Math.max(1, user.getLevel()+diff), 20) );
      return;
    }
    if (statName.equals("param")) {
      user.setParam( Math.min(Math.max(12, user.getParam()+diff), 51) );
      return;
    }
    throw new IllegalParameters();
  }

}
