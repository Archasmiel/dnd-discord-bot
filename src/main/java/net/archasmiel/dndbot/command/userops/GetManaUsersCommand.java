package net.archasmiel.dndbot.command.userops;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import net.archasmiel.dndbot.command.basic.Command;
import net.archasmiel.dndbot.util.helper.UserUtil;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

/**
 * Method for getting all users.
 */
public class GetManaUsersCommand extends Command {

  /**
   * Constructor for command.
   * Used for MessageListener one-time creation and other purposes.
   */
  public GetManaUsersCommand() {
    super(
        Commands.slash("35stats", "Все персонажи из системы маны в виде списка")
    );
  }

  @Override
  public void process(SlashCommandInteraction interaction) {
    String discordUserId = interaction.getUser().getId();
    String msg;

    try {
      String manaUserId = UserUtil.getManaUserIdOrError(discordUserId);
      List<String> manaUserIds = UserUtil.getDiscordUserOrError(discordUserId)
          .getManaUserIds();

      msg = manaUserIds.stream()
          .map(e -> {
            try {
              return UserUtil.getManaUserOrError(e);
            } catch (Exception ex) {
              return null;
            }
          })
          .filter(Objects::nonNull)
          .map(e -> {
            try {
              String line = String.format("`id=%s`: %s%n", e.getId(), e);
              return e.getId().equals(manaUserId) ? "__" + line + "__" : line;
            } catch (Exception ex) {
              return null;
            }
          })
          .filter(Objects::nonNull)
          .collect(Collectors.joining());
    } catch (Exception e) {
      msg = e.getMessage();
    }

    interaction.reply(String.format("<@%s>%n%s", discordUserId, msg)).queue();
  }


}
