package net.archasmiel.dndbot.util;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpellCost {

  public static final Map<Integer, Integer> COST = Stream.of(new Integer[][] {
      { 0, 0 }, { 1, 1 }, { 2, 3 }, { 3, 5 }, { 4, 7 },
      { 5, 9 }, { 6, 11 }, { 7, 13 }, { 8, 15 }, { 9, 17 }
  }).collect(Collectors.toMap(e -> e[0], e -> e[1]));

  public static Integer getValue(int level) {
    return COST.get(level);
  }

}
