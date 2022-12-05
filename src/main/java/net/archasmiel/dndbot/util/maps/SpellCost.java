package net.archasmiel.dndbot.util.maps;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

/**
 * Map for spell mana usage.
 */
public class SpellCost {

  public static final SpellCost INSTANCE = new SpellCost();

  public final Map<Integer, Integer> cost = Map.ofEntries(
      new SimpleEntry<>(0, 0), new SimpleEntry<>(1, 1),
      new SimpleEntry<>(2, 3), new SimpleEntry<>(3, 5),
      new SimpleEntry<>(4, 7), new SimpleEntry<>(5, 9),
      new SimpleEntry<>(6, 11), new SimpleEntry<>(7, 13),
      new SimpleEntry<>(8, 15), new SimpleEntry<>(9, 17)
  );

  public Integer getValue(int level) {
    return cost.get(level);
  }

}
