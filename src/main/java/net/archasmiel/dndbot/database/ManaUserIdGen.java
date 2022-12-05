package net.archasmiel.dndbot.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ManaUser id generator.
 */
public class ManaUserIdGen {

  private static final Random RANDOM = new Random();
  private static final String CHARS = "0123456789abcdef";
  private static final List<String> GENERATED = new ArrayList<>();

  static {
    GENERATED.addAll(ManaController.INSTANCE.manaUsers.keySet());
  }

  private ManaUserIdGen() {

  }

  /**
   * Method for id generating.

   * @return generated id
   */
  public static String getId() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < 16; i++) {
      builder.append(CHARS.charAt(RANDOM.nextInt(0, 16)));
    }
    String id = builder.toString();
    return GENERATED.contains(id) ? getId() : builder.toString();
  }

}
