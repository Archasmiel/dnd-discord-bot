package net.archasmiel.dndbot.listener;

import java.util.List;
import java.util.stream.Collectors;
import net.archasmiel.dndbot.command.basic.*;
import net.archasmiel.dndbot.command.stats.*;
import net.archasmiel.dndbot.command.user.*;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter {

  private static final List<Command> COMMAND_DATA = List.of(
      HelpCommand.INSTANCE,

      AddUserCommand.INSTANCE,
      CastCommand.INSTANCE,
      GetUserCommand.INSTANCE,
      NewDayCommand.INSTANCE,

      LevelUpCommand.INSTANCE,
      LevelDownCommand.INSTANCE,
      ParamUpCommand.INSTANCE,
      ParamDownCommand.INSTANCE
  );

  @Override
  public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
    COMMAND_DATA.forEach(e -> {
      if (e.getName().equals(event.getCommandPath())) {
        e.process(event.getInteraction());
      }
    });
  }

  @Override
  public void onGuildReady(GuildReadyEvent event) {
    if (event.getGuild().getIdLong() == 917510566283718727L) {
      event.getGuild().updateCommands().addCommands(
          COMMAND_DATA.stream()
              .map(Command::getData)
              .collect(Collectors.toList())
      ).queue();
    }
  }

}
