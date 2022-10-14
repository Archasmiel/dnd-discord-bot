package net.archasmiel.dndbot.util;

import java.util.Optional;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class OptionMapper {

  public static Optional<Integer> mapToInt(OptionMapping mapping) {
    if (mapping == null) return Optional.empty();
    return mapping.getType() == OptionType.INTEGER ? Optional.of(mapping.getAsInt()) : Optional.empty();
  }

  public static Optional<String> mapToStr(OptionMapping mapping) {
    if (mapping == null) return Optional.empty();
    return mapping.getType() == OptionType.STRING ? Optional.of(mapping.getAsString()) : Optional.empty();
  }

}
