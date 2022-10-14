package net.archasmiel.dndbot.command.basic;

import java.util.List;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public abstract class Command {

  protected Command() {

  }

  public abstract SlashCommandData getData();
  public abstract void process(SlashCommandInteraction interaction);

  public String getName() {
    return getData().getName();
  }

  public String getDescr(){
    return getData().getDescription();
  }

  public List<OptionData> getOptions(){
    return getData().getOptions();
  }

}
