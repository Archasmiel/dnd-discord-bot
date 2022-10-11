package net.archasmiel.dndbot.command.factory;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelpCommand extends Command {

  public static final String REGEX = DndCommandFactory.PREFIX + "help";

  protected HelpCommand(MessageReceivedEvent event) {
    super(event);
  }

  @Override
  public void execute() {
    this.event.getChannel().sendMessage(
    """
        /35help - цей гайд-допомога
        /35signup <Class> <level> <param> - задати корстувача в системі
        /35stats - отримати дані корстувача в системі
        /35newday - отримати повний обсяг мани (на новий день)
        /35cast <spellLevel> - закстувати заклинання та відняти ману відносно системи
        /35levelup - +1 рівень до персонажа
        /35leveldown - -1 рівень до персонажа
        /35paramup - +1 очко параметра до персонажа
        /35paramdown - -1 очко параметра до персонажа
        """
    ).queue();
  }


}
