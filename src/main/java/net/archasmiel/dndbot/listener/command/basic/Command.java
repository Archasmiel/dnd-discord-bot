package net.archasmiel.dndbot.listener.command.basic;

import java.util.List;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

/**
 * Abstract custom Command class.
 */
public abstract class Command {

  private final SlashCommandData data;

  protected Command(SlashCommandData data) {
    this.data = data;
  }

  public abstract void process(SlashCommandInteraction interaction);

  public SlashCommandData data() {
    return data;
  }

  public String name() {
    return data.getName();
  }

  public String description() {
    return data.getDescription();
  }

  public List<OptionData> options() {
    return data.getOptions();
  }

}
