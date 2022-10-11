package net.archasmiel.dndbot.command.mana;

import java.util.Optional;
import net.archasmiel.dndbot.command.factory.Command;
import net.archasmiel.dndbot.command.factory.DndCommandFactory;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.database.ManaUser;
import net.archasmiel.dndbot.util.manainfo.SpellCost;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CastCommand extends Command {

  public static final String REGEX = DndCommandFactory.PREFIX + "cast [0-9]";

  public CastCommand(MessageReceivedEvent event) {
    super(event);
  }

  @Override
  public void execute() {
    String msg;
    Optional<ManaUser> userOptional = ManaController.getUser(event.getAuthor().getId());
    if (userOptional.isEmpty()) {
      this.event.getChannel().sendMessage("Неправильні дані корстувача!").queue();
      return;
    }

    try {
      int spellLevel = Integer.parseInt(event.getMessage().getContentDisplay().split(" ")[1]);
      if (spellLevel < 0 || spellLevel > 9) throw new IllegalStateException();

      int manaUsed = SpellCost.getValue(spellLevel);
      ManaUser user = userOptional.get();

      if (user.getCurrMana() >= manaUsed) {
        user.setCurrMana(user.getCurrMana() - manaUsed);
        ManaController.writeUsers();
        msg = String.format("<@%s>, заклинання успішно використано. Зараз в тебе %d/%d мани.",
            event.getAuthor().getId(), user.getCurrMana(), user.getMaxMana());
      } else {
        msg = String.format("<@%s>, мани не достатньо. Зараз в тебе %d/%d мани.",
          event.getAuthor().getId(), user.getCurrMana(), user.getMaxMana());
      }
    } catch (IllegalStateException e) {
      msg = "Неправильні параметри!";
    }

    this.event.getChannel().sendMessage(msg).queue();
  }


}
