package net.archasmiel.dndbot.command.stats;

import java.util.Optional;
import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.ManaUser;
import net.archasmiel.dndbot.exception.IllegalParameters;
import net.archasmiel.dndbot.exception.NoManaUserFound;
import net.archasmiel.dndbot.util.Classes;
import net.archasmiel.dndbot.util.ManaQuad;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class ParamDownCommand extends Command {

  public static final Command INSTANCE = new ParamDownCommand();
  private static final SlashCommandData DATA =
      Commands.slash("35paramdown", "Персонаж отримує -1 рівень та оновлює макс. ману");

  private ParamDownCommand() {

  }


  @Override
  public SlashCommandData getData() {
    return DATA;
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    String msg;

    try {
      ManaUser user = ManaController.get(interaction.getUser().getId());
      if (user == null) {
        throw new NoManaUserFound();
      }

      user.setParameter(user.getParameter() <= 12 ? 12 : user.getParameter()-1);

      Optional<ManaQuad> manaQuad = Classes.valueOf(user.getClassName())
          .getMana(user.getLevel(), user.getParameter());
      if (manaQuad.isEmpty()) throw new IllegalParameters();
      user.setMana(manaQuad.get().getMaxMana());
      ManaController.writeUsers();

      msg = String.format("<@%s>, операцію з рівнем завершено, мана тепер %d + %d = %d",
          interaction.getUser().getId(), manaQuad.get().getSpellpoints(),
          manaQuad.get().getBonusSpellpoints(), manaQuad.get().getMaxMana());
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(msg).queue();
  }


}
