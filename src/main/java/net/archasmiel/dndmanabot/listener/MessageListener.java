package net.archasmiel.dndmanabot.listener;

import java.util.ArrayList;
import java.util.List;
import net.archasmiel.dndmanabot.database.ManaController;
import net.archasmiel.dndmanabot.database.objects.DiscordUser;
import net.archasmiel.dndmanabot.listener.command.basic.Command;
import net.archasmiel.dndmanabot.listener.command.basic.HelpCommand;
import net.archasmiel.dndmanabot.listener.command.manaops.CastCommand;
import net.archasmiel.dndmanabot.listener.command.manaops.NewDayCommand;
import net.archasmiel.dndmanabot.listener.command.userops.AddUserCommand;
import net.archasmiel.dndmanabot.listener.command.userops.ChangeStatCommand;
import net.archasmiel.dndmanabot.listener.command.userops.GetManaUsersCommand;
import net.archasmiel.dndmanabot.listener.command.userops.SetManaUserCommand;
import net.archasmiel.dndmanabot.util.config.BotConfiguration;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

/**
 * Bot message listener.
 */
public class MessageListener extends ListenerAdapter {

  private final List<Command> commands = List.of(
      new HelpCommand(),
      new CastCommand(), new NewDayCommand(),
      new AddUserCommand(), new ChangeStatCommand(),
      new GetManaUsersCommand(), new SetManaUserCommand()
  );
  private final List<SlashCommandData> commandData = commands.stream()
      .map(Command::data).toList();
  private final List<String> guilds = BotConfiguration.getGuilds();

  @Override
  public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
    commands.forEach(e -> {
      if (e.name().equals(event.getCommandPath())) {
        SlashCommandInteraction sci = event.getInteraction();
        String discordUserId = sci.getUser().getId();

        ManaController.INSTANCE.discordUsers.computeIfAbsent(discordUserId,
            id -> new DiscordUser(id, new ArrayList<>(), null));
        ManaController.INSTANCE.saveDiscordUser(discordUserId);

        e.process(sci);
      }
    });
  }

  @Override
  public void onGuildReady(GuildReadyEvent event) {
    Guild guild = event.getGuild();
    if (guilds.contains(guild.getId())) {
      guild.updateCommands()
          .addCommands(commandData)
          .queue();
    }
  }

}
