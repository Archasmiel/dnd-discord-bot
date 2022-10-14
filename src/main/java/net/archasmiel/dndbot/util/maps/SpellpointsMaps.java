package net.archasmiel.dndbot.util.maps;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpellpointsMaps {

  public static final Map<Integer, Integer> BARDLIKE = Stream.of(new Integer[][] {
      { 1, 0 }, { 2, 0 }, { 3, 1 }, { 4, 5 }, { 5, 6 },
      { 6, 9 }, { 7, 14 }, { 8, 17 }, { 9, 22 }, { 10, 29 },
      { 11, 34 }, { 12, 41 }, { 13, 50 }, { 14, 57 }, { 15, 67 },
      { 16, 81 }, { 17, 95 }, { 18, 113 }, { 19, 133 }, { 20, 144 }
  }).collect(Collectors.toMap(e -> e[0], e -> e[1]));

  public static final Map<Integer, Integer> CLERICLIKE = Stream.of(new Integer[][] {
      { 1, 2 }, { 2, 4 }, { 3, 7 }, { 4, 11 }, { 5, 16 },
      { 6, 24 }, { 7, 33 }, { 8, 44 }, { 9, 56 }, { 10, 72 },
      { 11, 88 }, { 12, 104 }, { 13, 120 }, { 14, 136 }, { 15, 152 },
      { 16, 168 }, { 17, 184 }, { 18, 200 }, { 19, 216 }, { 20, 232 }
  }).collect(Collectors.toMap(e -> e[0], e -> e[1]));

  public static final Map<Integer, Integer> PALADINLIKE = Stream.of(new Integer[][] {
      { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 }, { 5, 0 },
      { 6, 1 }, { 7, 1 }, { 8, 1 }, { 9, 1 }, { 10, 4 },
      { 11, 4 }, { 12, 9 }, { 13, 9 }, { 14, 10 }, { 15, 17 },
      { 16, 20 }, { 17, 25 }, { 18, 26 }, { 19, 41 }, { 20, 48 }
  }).collect(Collectors.toMap(e -> e[0], e -> e[1]));

  public static final Map<Integer, Integer> SORCERERLIKE = Stream.of(new Integer[][] {
      { 1, 3 }, { 2, 5 }, { 3, 8 }, { 4, 14 }, { 5, 19 },
      { 6, 29 }, { 7, 37 }, { 8, 51 }, { 9, 63 }, { 10, 81 },
      { 11, 97 }, { 12, 115 }, { 13, 131 }, { 14, 149 }, { 15, 165 },
      { 16, 183 }, { 17, 199 }, { 18, 217 }, { 19, 233 }, { 20, 249 }
  }).collect(Collectors.toMap(e -> e[0], e -> e[1]));

  public static final Map<Integer, Integer> ARTIFICERLIKE = Stream.of(new Integer[][] {
      { 1, 2 }, { 2, 4 }, { 3, 7 }, { 4, 10 }, { 5, 14 },
      { 6, 19 }, { 7, 24 }, { 8, 30 }, { 9, 37 }, { 10, 44 },
      { 11, 51 }, { 12, 60 }, { 13, 69 }, { 14, 78 }, { 15, 89 },
      { 16, 100 }, { 17, 111 }, { 18, 122 }, { 19, 133 }, { 20, 144 }
  }).collect(Collectors.toMap(e -> e[0], e -> e[1]));

}
