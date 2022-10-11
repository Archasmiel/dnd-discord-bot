package net.archasmiel.dndbot.command.mana;

import net.archasmiel.dndbot.command.factory.Command;
import net.archasmiel.dndbot.command.factory.DndCommandFactory;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.ManaUser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class BasicDefineManaCommand extends Command {

  public static final String REGEX = DndCommandFactory.PREFIX + "bdefmana [1-9][0-9]*";

  public BasicDefineManaCommand(MessageReceivedEvent event) {
    super(event);
  }

  @Override
  public void execute() {
    int mana = Integer.parseInt(event.getMessage().getContentDisplay().split(" ")[1]);
    ManaController.USERS.put(event.getAuthor().getId(), new ManaUser(mana, mana));
    ManaController.writeUsers();
    this.event.getChannel().sendMessage("Для **" + event.getAuthor().getName() + "** створено новий пул мани макс. розміром " + mana).queue();
  }

}
