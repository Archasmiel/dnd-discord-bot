package net.archasmiel.dndbot.command.manaops;

import java.util.Objects;
import java.util.stream.IntStream;
import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.basic.Pair;
import net.archasmiel.dndbot.database.objects.ManaUser;
import net.archasmiel.dndbot.util.exception.WrongCommandParameters;
import net.archasmiel.dndbot.util.helper.UserUtil;
import net.archasmiel.dndbot.util.mana.maps.SpellCost;
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
    String discordUserId = interaction.getUser().getId();
    String msg;

    try {
      String manaUserId = UserUtil.getManaUserIdOrError(discordUserId);
      ManaUser manaUser = UserUtil.getManaUserOrError(manaUserId);

      Pair<Integer, Integer> usedManaParams = getCorrectUsedMana(interaction);
      int spellLevel = usedManaParams.getFirst();
      int usedMana = usedManaParams.getSecond();

      if (manaUser.getCurrMana() >= usedMana) {
        manaUser.setCurrMana(manaUser.getCurrMana() - usedMana);
        msg = String.format(
            "Заклинание `%d круга` успешно использовано. Сейчас у тебя `%d/%d` маны.",
            spellLevel, manaUser.getCurrMana(), manaUser.getMaxMana());
        ManaController.INSTANCE.saveDiscordUser(discordUserId);
        ManaController.INSTANCE.saveManaUser(manaUserId);
      } else {
        msg = String.format(
            "Маны не достаточно для заклинания `%d круга`. Сейчас у тебя `%d/%d` маны.",
            spellLevel, manaUser.getCurrMana(), manaUser.getMaxMana());
      }
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(String.format("<@%s>%n%s", discordUserId, msg)).queue();
  }

  private Pair<Integer, Integer> getCorrectUsedMana(SlashCommandInteraction interaction)
      throws WrongCommandParameters {
    if (interaction.getOptions().size() != 1) {
      throw new WrongCommandParameters();
    }

    int spellLevel = Objects.requireNonNull(interaction.getOption("level")).getAsInt();
    if (spellLevel < 0 || spellLevel > 9) {
      throw new WrongCommandParameters();
    }

    return new Pair<>(spellLevel, SpellCost.INSTANCE.getValue(spellLevel));
  }

}
