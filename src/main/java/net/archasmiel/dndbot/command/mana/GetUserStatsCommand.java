package net.archasmiel.dndbot.command.mana;

import net.archasmiel.dndbot.command.factory.Command;
import net.archasmiel.dndbot.command.factory.DndCommandFactory;
import net.archasmiel.dndbot.database.ManaController;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class GetUserStatsCommand extends Command {

  public static final String REGEX = DndCommandFactory.PREFIX + "stats";

  public GetUserStatsCommand(MessageReceivedEvent event) {
    super(event);
  }

  @Override
  public void execute() {
    if (!ManaController.hasUser(event.getAuthor().getId())) {
      this.event.getChannel().sendMessage("Користувача в системі немає!").queue();
      return;
    }

    if (!ManaController.isFinished(event.getAuthor().getId())) {
      this.event.getChannel().sendMessage("Користувач неправильно заданий!\n" +
          String.format("**@%s**: %s",
              event.getAuthor().getName(), ManaController.USERS.get(event.getAuthor().getId()))
      ).queue();
      return;
    }

    this.event.getChannel().sendMessage(
        String.format("<@%s>: %s",
            event.getAuthor().getId(), ManaController.USERS.get(event.getAuthor().getId()))
    ).queue();
  }


}
