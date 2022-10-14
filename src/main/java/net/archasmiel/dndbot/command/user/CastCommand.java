package net.archasmiel.dndbot.command.user;

import java.util.Objects;
import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.ManaUser;
import net.archasmiel.dndbot.exception.IllegalParameters;
import net.archasmiel.dndbot.exception.NoManaUserFound;
import net.archasmiel.dndbot.util.SpellCost;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public class CastCommand extends Command {

  public static final Command INSTANCE = new CastCommand();
  private static final SlashCommandData DATA =
      Commands.slash("35cast", "Викликати заклинання і відняти ману")
          .addOption(OptionType.INTEGER, "level", "Рівень заклинання");

  private CastCommand() {

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
      if (interaction.getOptions().size() != 1) {
        throw new IllegalParameters();
      }

      int spellLevel = Objects.requireNonNull(interaction.getOption("level")).getAsInt();
      if (spellLevel < 0 || spellLevel > 9) {
        throw new IllegalParameters();
      }
      int mana = SpellCost.getValue(spellLevel);

      if (user.getCurrMana() >= mana) {
        user.setCurrMana(user.getCurrMana() - mana);
        ManaController.writeUsers();
        msg = String.format("<@%s>, заклинання **%d рівня** успішно використано. Зараз в тебе %d/%d мани.",
            interaction.getUser().getId(), spellLevel, user.getCurrMana(), user.getMaxMana());
      } else {
        msg = String.format("<@%s>, мани не достатньо для заклинання %d рівня. Зараз в тебе %d/%d мани.",
            interaction.getUser().getId(), spellLevel, user.getCurrMana(), user.getMaxMana());
      }
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(msg).queue();
  }
}
