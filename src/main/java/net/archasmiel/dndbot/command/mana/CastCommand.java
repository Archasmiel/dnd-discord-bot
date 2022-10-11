package net.archasmiel.dndbot.command.mana;

import net.archasmiel.dndbot.command.factory.Command;
import net.archasmiel.dndbot.command.factory.DndCommandFactory;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CastCommand extends Command {

  public static final String REGEX = DndCommandFactory.PREFIX + "cast [1-9][0-9]*";

  public CastCommand(MessageReceivedEvent event) {
    super(event);
  }

  @Override
  public void execute() {
    this.event.getChannel().sendMessage(event.getMessage().getContentDisplay()).queue();
  }


}
