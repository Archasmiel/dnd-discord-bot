package net.archasmiel.dndbot.command.user;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.objects.ManaUser;
import net.archasmiel.dndbot.exception.IllegalParameters;
import net.archasmiel.dndbot.exception.NoManaUserFound;
import net.archasmiel.dndbot.util.maps.SpellCost;
import net.dv8tion.jda.api.interactions.commands.Command.Choice;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

/**
 * Command for spell casting.
 */
public class CastCommand extends Command {

  /**
   * Constructor for command.
   * Used for MessageListener one-time creation and other purposes.
   */
  public CastCommand() {
    super(
        Commands.slash("35cast", "Применить заклинание нек-во уровня")
        .addOptions(
            new OptionData(OptionType.INTEGER, "level", "Круг заклинания", true)
                .addChoices(
                    IntStream.range(0, 10).boxed()
                        .map(e -> new Choice(Integer.toString(e), e))
                        .toList()
                )
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
      Optional<ManaUser> manaUserOpt = ManaController.INSTANCE.getManaUser(muid);
      ManaUser manaUser = manaUserOpt.orElseThrow(NoManaUserFound::new);

      if (interaction.getOptions().size() != 1) {
        throw new IllegalParameters();
      }

      int spellLevel = Objects.requireNonNull(interaction.getOption("level")).getAsInt();
      if (spellLevel < 0 || spellLevel > 9) {
        throw new IllegalParameters();
      }
      int mana = SpellCost.INSTANCE.getValue(spellLevel);

      if (manaUser.getCurrMana() >= mana) {
        manaUser.setCurrMana(manaUser.getCurrMana() - mana);
        ManaController.INSTANCE.saveDiscordUser(id);
        ManaController.INSTANCE.saveManaUser(muid);
        msg = String.format(
            "<@%s>, заклинание **%d круга** успешно использовано. Сейчас у тебя `%d/%d` маны.",
            id, spellLevel, manaUser.getCurrMana(), manaUser.getMaxMana());
      } else {
        msg = String.format(
            "<@%s>, маны не достаточно для заклинания %d круга. Сейчас у тебя `%d/%d` маны.",
            id, spellLevel, manaUser.getCurrMana(), manaUser.getMaxMana());
      }
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(msg).queue();
  }

}
