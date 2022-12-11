package net.archasmiel.dndmanabot.util.mana.maps;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

/**
 * Maps for all classes non-bonus spell points.
 */
public class SpellPoints {

  public static final SpellPoints INSTANCE = new SpellPoints();

  public final Map<Integer, Integer> bardlike = Map.ofEntries(
      new SimpleEntry<>(1, 0), new SimpleEntry<>(2, 0), new SimpleEntry<>(3, 1),
      new SimpleEntry<>(4, 5), new SimpleEntry<>(5, 6), new SimpleEntry<>(6, 9),
      new SimpleEntry<>(7, 14), new SimpleEntry<>(8, 17), new SimpleEntry<>(9, 22),
      new SimpleEntry<>(10, 29), new SimpleEntry<>(11, 34), new SimpleEntry<>(12, 41),
      new SimpleEntry<>(13, 50), new SimpleEntry<>(14, 57), new SimpleEntry<>(15, 67),
      new SimpleEntry<>(16, 81), new SimpleEntry<>(17, 95), new SimpleEntry<>(18, 113),
      new SimpleEntry<>(19, 133), new SimpleEntry<>(20, 144)
  );
  
  public final Map<Integer, Integer> clericlike = Map.ofEntries(
      new SimpleEntry<>(1, 2), new SimpleEntry<>(2, 4), new SimpleEntry<>(3, 7),
      new SimpleEntry<>(4, 11), new SimpleEntry<>(5, 16), new SimpleEntry<>(6, 24),
      new SimpleEntry<>(7, 33), new SimpleEntry<>(8, 44), new SimpleEntry<>(9, 56),
      new SimpleEntry<>(10, 72), new SimpleEntry<>(11, 88), new SimpleEntry<>(12, 104),
      new SimpleEntry<>(13, 120), new SimpleEntry<>(14, 136), new SimpleEntry<>(15, 152),
      new SimpleEntry<>(16, 168), new SimpleEntry<>(17, 184), new SimpleEntry<>(18, 200),
      new SimpleEntry<>(19, 216), new SimpleEntry<>(20, 232)
  );

  public final Map<Integer, Integer> paladinlike = Map.ofEntries(
      new SimpleEntry<>(1, 0), new SimpleEntry<>(2, 0), new SimpleEntry<>(3, 0),
      new SimpleEntry<>(4, 0), new SimpleEntry<>(5, 0), new SimpleEntry<>(6, 1),
      new SimpleEntry<>(7, 1), new SimpleEntry<>(8, 1), new SimpleEntry<>(9, 1),
      new SimpleEntry<>(10, 4), new SimpleEntry<>(11, 4), new SimpleEntry<>(12, 9),
      new SimpleEntry<>(13, 9), new SimpleEntry<>(14, 10), new SimpleEntry<>(15, 17),
      new SimpleEntry<>(16, 20), new SimpleEntry<>(17, 25), new SimpleEntry<>(18, 26),
      new SimpleEntry<>(19, 41), new SimpleEntry<>(20, 48)
  );

  public final Map<Integer, Integer> sorcererlike = Map.ofEntries(
      new SimpleEntry<>(1, 3), new SimpleEntry<>(2, 5), new SimpleEntry<>(3, 8),
      new SimpleEntry<>(4, 14), new SimpleEntry<>(5, 19), new SimpleEntry<>(6, 29),
      new SimpleEntry<>(7, 37), new SimpleEntry<>(8, 51), new SimpleEntry<>(9, 63),
      new SimpleEntry<>(10, 81), new SimpleEntry<>(11, 97), new SimpleEntry<>(12, 115),
      new SimpleEntry<>(13, 131), new SimpleEntry<>(14, 149), new SimpleEntry<>(15, 165),
      new SimpleEntry<>(16, 183), new SimpleEntry<>(17, 199), new SimpleEntry<>(18, 217),
      new SimpleEntry<>(19, 233), new SimpleEntry<>(20, 249)
  );

  public final Map<Integer, Integer> artificerlike = Map.ofEntries(
      new SimpleEntry<>(1, 2), new SimpleEntry<>(2, 4), new SimpleEntry<>(3, 7),
      new SimpleEntry<>(4, 10), new SimpleEntry<>(5, 14), new SimpleEntry<>(6, 19),
      new SimpleEntry<>(7, 24), new SimpleEntry<>(8, 30), new SimpleEntry<>(9, 37),
      new SimpleEntry<>(10, 44), new SimpleEntry<>(11, 51), new SimpleEntry<>(12, 60),
      new SimpleEntry<>(13, 69), new SimpleEntry<>(14, 78), new SimpleEntry<>(15, 89),
      new SimpleEntry<>(16, 100), new SimpleEntry<>(17, 111), new SimpleEntry<>(18, 122),
      new SimpleEntry<>(19, 133), new SimpleEntry<>(20, 144)
  );

}
