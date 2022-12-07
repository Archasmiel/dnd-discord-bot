package net.archasmiel.dndbot.util.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * ManaUser id generator.
 */
public class ManaUserIdUtil {

  public static final ManaUserIdUtil INSTANCE = new ManaUserIdUtil();

  private final Random random = new Random();
  private final List<String> generated = new ArrayList<>();

  private ManaUserIdUtil() {

  }

  /**
   * Method for id generating.

   * @return generated id
   */
  public String getId() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < 16; i++) {
      builder.append("0123456789abcdef".charAt(random.nextInt(0, 16)));
    }
    String id = builder.toString();
    return generated.contains(id) ? getId() : builder.toString();
  }

  public void addIds(Collection<String> ids) {
    generated.addAll(ids);
  }

  public boolean checkId(String id) {
    return id.length() != 16 || !id.matches("[\\da-f]{16}+");
  }

}
