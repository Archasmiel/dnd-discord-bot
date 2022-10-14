package net.archasmiel.dndbot.command.user;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.ManaUser;
import net.archasmiel.dndbot.exception.IllegalParameters;
import net.archasmiel.dndbot.exception.NoManaUserFound;
import net.archasmiel.dndbot.util.SpellCost;
import net.dv8tion.jda.api.interactions.commands.Command.Choice;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class CastCommand extends Command {

  public CastCommand() {
    super(
      Commands.slash("35cast", "Викликати заклинання і відняти ману")
        .addOptions(
            new OptionData(OptionType.INTEGER, "level", "Рівень заклинання", true)
                .addChoices(
                    IntStream.range(0, 10).boxed()
                        .map(e -> new Choice(Integer.toString(e), e))
                        .collect(Collectors.toList())
                )
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
