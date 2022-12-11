package net.archasmiel.dndbot.listener.command.userops;

import java.util.Optional;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.objects.DiscordUser;
import net.archasmiel.dndbot.database.objects.ManaUser;
import net.archasmiel.dndbot.listener.command.basic.Command;
import net.archasmiel.dndbot.util.exception.ManaUserAccessException;
import net.archasmiel.dndbot.util.exception.NoManaUserFound;
import net.archasmiel.dndbot.util.exception.WrongCommandParameters;
import net.archasmiel.dndbot.util.helper.ManaUserIdUtil;
import net.archasmiel.dndbot.util.helper.OptionMapper;
import net.archasmiel.dndbot.util.helper.UserUtil;
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
    String discordUserId = interaction.getUser().getId();
    String msg;

    try {
      Optional<String> newId = OptionMapper.mapToStr(interaction.getOption("id"));
      if (newId.isEmpty() || !ManaUserIdUtil.INSTANCE.checkId(newId.get())) {
        throw new WrongCommandParameters();
      }
      if (!ManaController.INSTANCE.manaUsers.containsKey(newId.get())) {
        throw new NoManaUserFound();
      }

      String discordOwnerId = ManaController.INSTANCE.discordUsers.entrySet().stream()
          .filter(e -> e.getValue().getManaUserIds().contains(newId.get()))
          .findFirst()
          .map(e -> e.getValue().getId())
          .orElseThrow(NullPointerException::new);
      if (!discordOwnerId.equals(discordUserId)) {
        throw new ManaUserAccessException(newId.get());
      }

      DiscordUser discordUser = UserUtil.getDiscordUserOrError(discordUserId);
      discordUser.setManaUserId(newId.get());
      ManaController.INSTANCE.saveDiscordUser(discordUserId);

      ManaUser manaUser = UserUtil.getManaUserOrError(newId.get());

      msg = String.format(
          "Пользователь изменён на `%s`, хар-ки: %s%n", newId.get(), manaUser);
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(String.format("<@%s>%n%s", discordUserId, msg)).queue();
  }


}
