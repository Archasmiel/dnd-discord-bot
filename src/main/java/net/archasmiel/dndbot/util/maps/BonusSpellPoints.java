package net.archasmiel.dndbot.util.maps;

import java.util.List;

/**
 * Bonus spell points for mana system.
 */
public class BonusSpellPoints {

  public static final BonusSpellPoints INSTANCE = new BonusSpellPoints();

  public final List<List<Integer>> table = List.of(
      List.of(0, 1,  1,  1,  1,   1,   1,   1,   1,   1),
      List.of(0, 1,  4,  4,  4,   4,   4,   4,   4,   4),
      List.of(0, 1,  4,  9,  9,   9,   9,   9,   9,   9),
      List.of(0, 1,  4,  9, 16,  16,  16,  16,  16,  16),
      List.of(0, 2,  5, 10, 17,  26,  26,  26,  26,  26),
      List.of(0, 2,  8, 13, 20,  29,  40,  40,  40,  40),
      List.of(0, 2,  8, 18, 25,  34,  45,  58,  58,  58),
      List.of(0, 2,  8, 18, 32,  41,  52,  65,  80,  80),
      List.of(0, 3,  9, 19, 33,  51,  62,  75,  90, 107),
      List.of(0, 3, 12, 22, 36,  54,  76,  89, 104, 121),
      List.of(0, 3, 12, 24, 38,  56,  78, 104, 119, 136),
      List.of(0, 3, 12, 27, 48,  66,  88, 114, 144, 161),
      List.of(0, 4, 13, 28, 49,  76,  98, 124, 154, 188),
      List.of(0, 4, 16, 31, 52,  77, 110, 136, 166, 200),
      List.of(0, 4, 16, 36, 57,  84, 117, 156, 186, 220),
      List.of(0, 4, 16, 36, 64,  91, 124, 163, 208, 242),
      List.of(0, 5, 17, 37, 65, 101, 134, 173, 218, 269),
      List.of(0, 5, 20, 40, 68, 104, 148, 187, 232, 283),
      List.of(0, 5, 20, 45, 73, 109, 153, 205, 250, 301),
      List.of(0, 5, 20, 45, 80, 116, 160, 212, 272, 323)
  );

  public Integer getValue(int maxSpellNum, int parameterNum) {
    return table.get((parameterNum - 12) / 2).get(maxSpellNum);
  }

}
