package net.archasmiel.dndbot.util.maps;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Maximum spell casting level maps for each D&D class.
 */
public class MaxClassMaps {

  public static final MaxClassMaps INSTANCE = new MaxClassMaps();

  public final Map<Integer, Integer> bardlike = Stream.of(new Integer[][] {
      { 1, 0 }, { 2, 1 }, { 3, 1 }, { 4, 2 }, { 5, 2 },
      { 6, 2 }, { 7, 3 }, { 8, 3 }, { 9, 3 }, { 10, 4 },
      { 11, 4 }, { 12, 4 }, { 13, 5 }, { 14, 5 }, { 15, 5 },
      { 16, 6 }, { 17, 6 }, { 18, 6 }, { 19, 6 }, { 20, 6 }
  }).collect(Collectors.toMap(e -> e[0], e -> e[1]));

  public final Map<Integer, Integer> clericlike = Stream.of(new Integer[][] {
      { 1, 1 }, { 2, 1 }, { 3, 2 }, { 4, 2 }, { 5, 3 },
      { 6, 3 }, { 7, 4 }, { 8, 4 }, { 9, 5 }, { 10, 5 },
      { 11, 6 }, { 12, 6 }, { 13, 7 }, { 14, 7 }, { 15, 8 },
      { 16, 8 }, { 17, 9 }, { 18, 9 }, { 19, 9 }, { 20, 9 }
  }).collect(Collectors.toMap(e -> e[0], e -> e[1]));

  public final Map<Integer, Integer> paladinlike = Stream.of(new Integer[][] {
      { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 1 }, { 5, 1 },
      { 6, 1 }, { 7, 1 }, { 8, 2 }, { 9, 2 }, { 10, 2 },
      { 11, 3 }, { 12, 3 }, { 13, 3 }, { 14, 3 }, { 15, 3 },
      { 16, 3 }, { 17, 3 }, { 18, 3 }, { 19, 3 }, { 20, 3 }
  }).collect(Collectors.toMap(e -> e[0], e -> e[1]));

  public final Map<Integer, Integer> sorcererlike = Stream.of(new Integer[][] {
      { 1, 1 }, { 2, 1 }, { 3, 1 }, { 4, 2 }, { 5, 2 },
      { 6, 3 }, { 7, 3 }, { 8, 4 }, { 9, 4 }, { 10, 5 },
      { 11, 5 }, { 12, 6 }, { 13, 6 }, { 14, 7 }, { 15, 7 },
      { 16, 8 }, { 17, 8 }, { 18, 9 }, { 19, 9 }, { 20, 9 }
  }).collect(Collectors.toMap(e -> e[0], e -> e[1]));

  public final Map<Integer, Integer> artificerlike = Stream.of(new Integer[][] {
      { 1, 1 }, { 2, 1 }, { 3, 2 }, { 4, 2 }, { 5, 3 },
      { 6, 3 }, { 7, 3 }, { 8, 4 }, { 9, 4 }, { 10, 4 },
      { 11, 5 }, { 12, 5 }, { 13, 5 }, { 14, 6 }, { 15, 6 },
      { 16, 6 }, { 17, 6 }, { 18, 6 }, { 19, 6 }, { 20, 6 }
  }).collect(Collectors.toMap(e -> e[0], e -> e[1]));

}
