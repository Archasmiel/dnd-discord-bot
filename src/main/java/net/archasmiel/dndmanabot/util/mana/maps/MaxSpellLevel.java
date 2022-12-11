package net.archasmiel.dndmanabot.util.mana.maps;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

/**
 * Maximum spell casting level maps for each D&D class.
 */
public class MaxSpellLevel {

  public static final MaxSpellLevel INSTANCE = new MaxSpellLevel();
  public final Map<Integer, Integer> bardlike = Map.ofEntries(
      new SimpleEntry<>(1, 0), new SimpleEntry<>(2, 1), new SimpleEntry<>(3, 1),
      new SimpleEntry<>(4, 2), new SimpleEntry<>(5, 2), new SimpleEntry<>(6, 2), 
      new SimpleEntry<>(7, 3), new SimpleEntry<>(8, 3), new SimpleEntry<>(9, 3), 
      new SimpleEntry<>(10, 4), new SimpleEntry<>(11, 4), new SimpleEntry<>(12, 4), 
      new SimpleEntry<>(13, 5), new SimpleEntry<>(14, 5), new SimpleEntry<>(15, 5), 
      new SimpleEntry<>(16, 6), new SimpleEntry<>(17, 6), new SimpleEntry<>(18, 6), 
      new SimpleEntry<>(19, 6), new SimpleEntry<>(20, 6)
  );

  public final Map<Integer, Integer> clericlike = Map.ofEntries(
      new SimpleEntry<>(1, 1), new SimpleEntry<>(2, 1), new SimpleEntry<>(3, 2), 
      new SimpleEntry<>(4, 2), new SimpleEntry<>(5, 3), new SimpleEntry<>(6, 3), 
      new SimpleEntry<>(7, 4), new SimpleEntry<>(8, 4), new SimpleEntry<>(9, 5), 
      new SimpleEntry<>(10, 5), new SimpleEntry<>(11, 6), new SimpleEntry<>(12, 6), 
      new SimpleEntry<>(13, 7), new SimpleEntry<>(14, 7), new SimpleEntry<>(15, 8), 
      new SimpleEntry<>(16, 8), new SimpleEntry<>(17, 9), new SimpleEntry<>(18, 9), 
      new SimpleEntry<>(19, 9), new SimpleEntry<>(20, 9)
  );

  public final Map<Integer, Integer> paladinlike = Map.ofEntries(
      new SimpleEntry<>(1, 0), new SimpleEntry<>(2, 0), new SimpleEntry<>(3, 0), 
      new SimpleEntry<>(4, 1), new SimpleEntry<>(5, 1), new SimpleEntry<>(6, 1), 
      new SimpleEntry<>(7, 1), new SimpleEntry<>(8, 2), new SimpleEntry<>(9, 2), 
      new SimpleEntry<>(10, 2), new SimpleEntry<>(11, 3), new SimpleEntry<>(12, 3), 
      new SimpleEntry<>(13, 3), new SimpleEntry<>(14, 4), new SimpleEntry<>(15, 4),
      new SimpleEntry<>(16, 4), new SimpleEntry<>(17, 4), new SimpleEntry<>(18, 4),
      new SimpleEntry<>(19, 4), new SimpleEntry<>(20, 4)
  );

  public final Map<Integer, Integer> sorcererlike = Map.ofEntries(
      new SimpleEntry<>(1, 1), new SimpleEntry<>(2, 1), new SimpleEntry<>(3, 1), 
      new SimpleEntry<>(4, 2), new SimpleEntry<>(5, 2), new SimpleEntry<>(6, 3), 
      new SimpleEntry<>(7, 3), new SimpleEntry<>(8, 4), new SimpleEntry<>(9, 4), 
      new SimpleEntry<>(10, 5), new SimpleEntry<>(11, 5), new SimpleEntry<>(12, 6), 
      new SimpleEntry<>(13, 6), new SimpleEntry<>(14, 7), new SimpleEntry<>(15, 7), 
      new SimpleEntry<>(16, 8), new SimpleEntry<>(17, 8), new SimpleEntry<>(18, 9), 
      new SimpleEntry<>(19, 9), new SimpleEntry<>(20, 9)
  );

  public final Map<Integer, Integer> artificerlike = Map.ofEntries(
      new SimpleEntry<>(1, 1), new SimpleEntry<>(2, 1), new SimpleEntry<>(3, 2), 
      new SimpleEntry<>(4, 2), new SimpleEntry<>(5, 3), new SimpleEntry<>(6, 3), 
      new SimpleEntry<>(7, 3), new SimpleEntry<>(8, 4), new SimpleEntry<>(9, 4), 
      new SimpleEntry<>(10, 4), new SimpleEntry<>(11, 5), new SimpleEntry<>(12, 5),
      new SimpleEntry<>(13, 5), new SimpleEntry<>(14, 6), new SimpleEntry<>(15, 6), 
      new SimpleEntry<>(16, 6), new SimpleEntry<>(17, 6), new SimpleEntry<>(18, 6), 
      new SimpleEntry<>(19, 6), new SimpleEntry<>(20, 6)
  );

}
