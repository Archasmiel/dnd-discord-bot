package net.archasmiel.dndbot.command.mana;

import net.archasmiel.dndbot.command.factory.Command;
import net.archasmiel.dndbot.command.factory.DndCommandFactory;
import net.archasmiel.dndbot.database.ManaController;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class GetManaCommand extends Command {

  public static final String REGEX = DndCommandFactory.PREFIX + "getmana";

  public GetManaCommand(MessageReceivedEvent event) {
    super(event);
  }

  @Override
  public void execute() {
    this.event.getChannel().sendMessage(
        String.format("**%s** у тебе зараз %d/%d мани",
            event.getAuthor().getName(),
            ManaController.USERS.get(event.getAuthor().getId()).getCurrMana(),
            ManaController.USERS.get(event.getAuthor().getId()).getMaxMana()
        )
    ).queue();
  }


}
