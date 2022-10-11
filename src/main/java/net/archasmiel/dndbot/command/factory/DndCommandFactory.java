package net.archasmiel.dndbot.command.factory;

import java.util.regex.Pattern;
import net.archasmiel.dndbot.command.level.LevelDownCommand;
import net.archasmiel.dndbot.command.level.LevelUpCommand;
import net.archasmiel.dndbot.command.level.ParamDownCommand;
import net.archasmiel.dndbot.command.level.ParamUpCommand;
import net.archasmiel.dndbot.command.mana.CastCommand;
import net.archasmiel.dndbot.command.mana.NewDayCommand;
import net.archasmiel.dndbot.command.mana.DefineUserCommand;
import net.archasmiel.dndbot.command.mana.GetUserStatsCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DndCommandFactory {

  public static final String PREFIX = "[/]35";

  public static Command produce(MessageReceivedEvent event) {
    if (isThisCommand(NewDayCommand.REGEX, event.getMessage().getContentDisplay())) {
      return new NewDayCommand(event);
    }
    if (isThisCommand(CastCommand.REGEX, event.getMessage().getContentDisplay())) {
      return new CastCommand(event);
    }
    if (isThisCommand(HelpCommand.REGEX, event.getMessage().getContentDisplay())) {
      return new HelpCommand(event);
    }

    if (isThisCommand(LevelUpCommand.REGEX, event.getMessage().getContentDisplay())) {
      return new LevelUpCommand(event);
    }
    if (isThisCommand(LevelDownCommand.REGEX, event.getMessage().getContentDisplay())) {
      return new LevelDownCommand(event);
    }
    if (isThisCommand(ParamUpCommand.REGEX, event.getMessage().getContentDisplay())) {
      return new ParamUpCommand(event);
    }
    if (isThisCommand(ParamDownCommand.REGEX, event.getMessage().getContentDisplay())) {
      return new ParamDownCommand(event);
    }

    if (isThisCommand(DefineUserCommand.REGEX, event.getMessage().getContentDisplay())) {
      return new DefineUserCommand(event);
    }
    if (isThisCommand(GetUserStatsCommand.REGEX, event.getMessage().getContentDisplay())) {
      return new GetUserStatsCommand(event);
    }

    return new WrongCommand(event);
  }

  private static boolean isThisCommand(String regex, String cmd) {
    return Pattern.compile(regex).matcher(cmd).matches();
  }

}
