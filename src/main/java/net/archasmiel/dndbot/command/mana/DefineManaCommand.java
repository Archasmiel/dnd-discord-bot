package net.archasmiel.dndbot.command.mana;

import net.archasmiel.dndbot.command.factory.Command;
import net.archasmiel.dndbot.command.factory.DndCommandFactory;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DefineManaCommand extends Command {

  public static final String REGEX = DndCommandFactory.PREFIX + "defmana [1-9][0-9]* [1-9][0-9]* [a-z]+";

  // level = 1;
  // spellparam = 1;

  public DefineManaCommand(MessageReceivedEvent event) {
    super(event);
  }

  @Override
  public void execute() {
    this.event.getChannel().sendMessage(event.getMessage().getContentDisplay()).queue();
  }


}
