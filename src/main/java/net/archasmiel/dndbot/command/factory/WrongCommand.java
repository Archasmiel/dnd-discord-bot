package net.archasmiel.dndbot.command.factory;

import net.archasmiel.dndbot.command.factory.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class WrongCommand extends Command {

  protected WrongCommand(MessageReceivedEvent event) {
    super(event);
  }

  @Override
  public void execute() {
    this.event.getChannel().sendMessage("Неправильна команда!").queue();
  }


}
