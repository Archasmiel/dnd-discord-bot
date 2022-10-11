package net.archasmiel.dndbot.command;

import net.archasmiel.dndbot.command.factory.Command;
import net.archasmiel.dndbot.command.factory.DndCommandFactory;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class NewDayCommand extends Command {

  public static final String REGEX = DndCommandFactory.PREFIX + "newday";

  public NewDayCommand(MessageReceivedEvent event) {
    super(event);
  }

  @Override
  public void execute() {
    this.event.getChannel().sendMessage("new day came").queue();
  }


}
