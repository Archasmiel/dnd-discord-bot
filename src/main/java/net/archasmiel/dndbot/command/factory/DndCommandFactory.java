package net.archasmiel.dndbot.command.factory;

import java.util.regex.Pattern;
import net.archasmiel.dndbot.command.mana.BasicDefineManaCommand;
import net.archasmiel.dndbot.command.mana.CastCommand;
import net.archasmiel.dndbot.command.NewDayCommand;
import net.archasmiel.dndbot.command.mana.GetManaCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DndCommandFactory {

  public static final String PREFIX = "[/]35";

  public static Command produce(MessageReceivedEvent event) {
    if (isThisCommand(CastCommand.REGEX, event.getMessage().getContentDisplay())) {
      return new CastCommand(event);
    }
    if (isThisCommand(NewDayCommand.REGEX, event.getMessage().getContentDisplay())) {
      return new NewDayCommand(event);
    }
    if (isThisCommand(BasicDefineManaCommand.REGEX, event.getMessage().getContentDisplay())) {
      return new BasicDefineManaCommand(event);
    }
    if (isThisCommand(GetManaCommand.REGEX, event.getMessage().getContentDisplay())) {
      return new GetManaCommand(event);
    }

    return new WrongCommand(event);
  }

  private static boolean isThisCommand(String regex, String cmd) {
    return Pattern.compile(regex).matcher(cmd).matches();
  }

}
