package net.archasmiel.dndbot.util.lang;

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
      new SimpleEntry<>("35stats", "данные о пользователе"),
      new SimpleEntry<>("35newday", "получить полную ману (новый день)"),
      new SimpleEntry<>("35cast <spellLevel>", "применить заклинание нек-во уровня"),
      new SimpleEntry<>("35levelup", "[+1] уровень персонажа"),
      new SimpleEntry<>("35leveldown", "[-1] уровень персонажа"),
      new SimpleEntry<>("35paramup", "[+1] к параметру персонажа"),
      new SimpleEntry<>("35paramdown", "[-1] к параметру персонажа")
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
    return '/' + entry.getKey() + " - " + entry.getValue();
  }

}
