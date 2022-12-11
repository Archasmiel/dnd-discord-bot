package net.archasmiel.dndmanabot.util.lang;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;

/**
 * Objects with translations.
 */
public class LangMaps {

  public static final Map<String, String> COMMAND_DESCRIPTION_MAP = Map.ofEntries(
      new SimpleEntry<>("35help", "помощь с ботом"),
      new SimpleEntry<>("35signup <class> <level> <param>", "регистрация в системе"),
      new SimpleEntry<>("35stats", "данные о всех твоих пользователях"),
      new SimpleEntry<>("35newday", "получить полную ману (новый день)"),
      new SimpleEntry<>("35cast <spellLevel>", "применить заклинание нек-во уровня"),
      new SimpleEntry<>("35changestat <statName> <value>", "изменение параметра персонажа"),
      new SimpleEntry<>("35setuser <id>", "изменение текущего персонажа на другого "
          + "(информация id в /35stats)")
  );

  public static final String HELP_MESSAGE = COMMAND_DESCRIPTION_MAP.entrySet().stream()
      .collect(
        Collector.of(
          StringBuilder::new,
          (sb, s) -> sb.append(commandDescriptionToString(s)).append("\n"),
          StringBuilder::append,
          StringBuilder::toString)
      );

  private LangMaps() {

  }

  public static String commandDescriptionToString(Entry<String, String> entry) {
    return "`/" + entry.getKey() + "` - " + entry.getValue();
  }

}
