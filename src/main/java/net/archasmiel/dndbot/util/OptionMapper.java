package net.archasmiel.dndbot.util;

import java.util.Optional;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

/**
 * Command options' mapper.
 */
public class OptionMapper {

  public static final OptionMapper INSTANCE = new OptionMapper();

  /**
   * Get optional of Integer from OptionMapping.
   *
   * @param mapping int mapping
   * @return optional of Integer
   */
  public Optional<Integer> mapToInt(OptionMapping mapping) {
    if (mapping == null) {
      return Optional.empty();
    }
    return mapping.getType() == OptionType.INTEGER
        ? Optional.of(mapping.getAsInt()) : Optional.empty();
  }

  /**
   * Get optional of String from OptionMapping.
   *
   * @param mapping int mapping
   * @return optional of String
   */
  public Optional<String> mapToStr(OptionMapping mapping) {
    if (mapping == null) {
      return Optional.empty();
    }
    return mapping.getType() == OptionType.STRING
        ? Optional.of(mapping.getAsString()) : Optional.empty();
  }

}
