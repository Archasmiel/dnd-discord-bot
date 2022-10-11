package net.archasmiel.dndbot.command.mana;

import net.archasmiel.dndbot.command.factory.Command;
import net.archasmiel.dndbot.command.factory.DndCommandFactory;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.ManaUser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class NewDayCommand extends Command {

  public static final String REGEX = DndCommandFactory.PREFIX + "newday";

  public NewDayCommand(MessageReceivedEvent event) {
    super(event);
  }

  @Override
  public void execute() {
    if (!ManaController.isFinished(event.getAuthor().getId())) {
      this.event.getChannel().sendMessage("Не заповнено корстувача!").queue();
      return;
    }

    ManaUser user = ManaController.USERS.get(event.getAuthor().getId());
    user.setCurrMana(user.getMaxMana());
    this.event.getChannel().sendMessage(
        String.format("<@%s> твоя мана оновилась, зараз запас %d/%d",
            event.getAuthor().getId(), user.getCurrMana(), user.getMaxMana())
    ).queue();
  }


}
