package net.archasmiel.dndbot.command.level;

import java.util.Optional;
import net.archasmiel.dndbot.command.factory.Command;
import net.archasmiel.dndbot.command.factory.DndCommandFactory;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.ManaUser;
import net.archasmiel.dndbot.util.manamath.ManaQuad;
import net.archasmiel.dndbot.util.manamath.ManaUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ParamUpCommand extends Command {

  public static final String REGEX = DndCommandFactory.PREFIX + "paramup";

  public ParamUpCommand(MessageReceivedEvent event) {
    super(event);
  }

  @Override
  public void execute() {
    Optional<ManaUser> userOptional = ManaController.getUser(event.getAuthor().getId());
    if (userOptional.isEmpty()) {
      this.event.getChannel().sendMessage("Неправильні дані корстувача!").queue();
      return;
    }

    ManaUser user = userOptional.get();
    user.setParameter(user.getParameter() >= 51 ? 51 : user.getParameter()+1);

    ManaQuad data = ManaUtils.calcMana(user);
    user.setMaxMana(data.getMaxMana());
    ManaController.writeUsers();

    this.event.getChannel().sendMessage(
      String.format("<@%s>, операцію з параметром завершено, мана тепер %d + %d = %d",
          event.getAuthor().getId(), data.getSpellpoints(),
          data.getBonusSpellpoints(), data.getMaxMana())
    ).queue();
  }


}
