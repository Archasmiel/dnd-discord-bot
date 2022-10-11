package net.archasmiel.dndbot.command.mana;

import java.util.Optional;
import net.archasmiel.dndbot.command.factory.Command;
import net.archasmiel.dndbot.command.factory.DndCommandFactory;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.ManaUser;
import net.archasmiel.dndbot.util.manamath.ManaQuad;
import net.archasmiel.dndbot.util.manamath.ManaUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DefineUserCommand extends Command {

  public static final String REGEX = DndCommandFactory.PREFIX + "signup [A-Z][a-z]+ [0-9]+ [0-9]+";

  public DefineUserCommand(MessageReceivedEvent event) {
    super(event);
  }

  @Override
  public void execute() {
    String msg;
    Optional<ManaUser> userOptional = ManaController.getUser(event.getAuthor().getId());
    ManaUser user = userOptional.orElseGet(() ->
        new ManaUser(0, 0, null, 0, 0)
    );
    String[] props = event.getMessage().getContentDisplay().split(" ");

    try {
      String className = props[1];
      int level = Integer.parseInt(props[2]);
      int param = Integer.parseInt(props[3]);

      if (level < 1 || level > 20) throw new IllegalStateException();
      if (param < 12) throw new IllegalStateException();

      user.setClassName(className);
      user.setLevel(level);
      user.setParameter(param);
      ManaQuad data = ManaUtils.calcMana(user);
      user.setMaxMana(data.getMaxMana());
      if (!user.isFinished()) throw new IllegalStateException();

      if (userOptional.isEmpty()) ManaController.USERS.put(event.getAuthor().getId(), user);
      ManaController.writeUsers();

      msg = String.format("<@%s>, встановлено максимальну ману в розмірі %d + %d = %d",
          event.getAuthor().getId(), data.getSpellpoints(),
          data.getBonusSpellpoints(), data.getMaxMana());
    } catch (IllegalStateException e) {
      msg = "Неправильні параметри!";
    }

    this.event.getChannel().sendMessage(msg).queue();
  }

}
