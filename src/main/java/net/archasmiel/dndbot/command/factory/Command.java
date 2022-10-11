package net.archasmiel.dndbot.command.factory;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {

  protected final MessageReceivedEvent event;

  protected Command(MessageReceivedEvent event) {
    this.event = event;
  }

  public abstract void execute();

}
