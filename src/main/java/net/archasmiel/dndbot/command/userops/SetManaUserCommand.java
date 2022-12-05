package net.archasmiel.dndbot.command.userops;

import java.util.Optional;
import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.objects.DiscordUser;
import net.archasmiel.dndbot.database.objects.ManaUser;
import net.archasmiel.dndbot.exception.IllegalParameters;
import net.archasmiel.dndbot.exception.NoDiscordUserFound;
import net.archasmiel.dndbot.exception.NoManaUserFound;
import net.archasmiel.dndbot.util.OptionMapper;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

/**
 * Method for getting all users.
 */
public class SetManaUserCommand extends Command {

  /**
   * Constructor for command.
   * Used for MessageListener one-time creation and other purposes.
   */
  public SetManaUserCommand() {
    super(
        Commands.slash("35setuser", "Выбрать персонажа для работы")
            .addOptions(
                new OptionData(OptionType.STRING, "id", "Идентификатор пользователя", true))
    );
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    String id = interaction.getUser().getId();
    String msg;

    try {
      ManaController.INSTANCE.discordUserCheck(id);
      Optional<String> newId = OptionMapper.INSTANCE.mapToStr(interaction.getOption("id"));
      if (newId.isEmpty() || newId.get().length() != 16 || !newId.get().matches("[\\da-f]{16}+")) {
        throw new IllegalParameters();
      }
      if (!ManaController.INSTANCE.manaUsers.containsKey(newId.get())) {
        throw new NoManaUserFound();
      }

      DiscordUser discordUser = ManaController.INSTANCE.getDiscordUser(id)
          .orElseThrow(NoDiscordUserFound::new);
      Optional<ManaUser> manaUser = ManaController.INSTANCE.getManaUser(newId.get());
      if (manaUser.isEmpty()) {
        throw new NoManaUserFound();
      }

      discordUser.setManaUserId(newId.get());
      ManaController.INSTANCE.saveDiscordUser(id);

      msg = String.format("`%s`, хар-ки: %s%n", newId.get(), manaUser.get());
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(msg).queue();
  }


}
