package net.archasmiel.dndbot.database.objects;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Mana user with all parameters.
 */
@Getter
@AllArgsConstructor
public class ManaUser implements JsonConvertible {

  private String id;
  private String className;
  @Setter
  private int level;
  @Setter
  private int param;
  private int currMana;
  private int maxMana;

  /**
   * Set all character parameters.

   * @param className D&D class name
   * @param level level value
   * @param param param value
   * @param maxMana maximum mana value
   * @param currMana current mana value
   */
  public void setAll(String className, int level, int param,
      int maxMana, int currMana) {
    this.className = className;
    this.level = level;
    this.param = param;
    this.maxMana = maxMana;
    this.currMana = currMana;
  }

  /**
   * Set all character mana parameters.

   * @param maxMana maximum mana value
   * @param currMana current mana value
   */
  public void setMana(int maxMana, int currMana) {
    this.maxMana = maxMana;
    this.currMana = currMana;
  }

  public void setMana(int mana) {
    this.maxMana = mana;
    this.currMana = mana;
  }

  public void setCurrMana(int currMana) {
    this.currMana = currMana;
  }

  /**
   * Returns json object with all parameters.

   * @return json object
   */
  public JsonObject asJsonObject() {
    JsonObject obj = new JsonObject();
    obj.addProperty("id", id);
    obj.addProperty("className", className);
    obj.addProperty("level", level);
    obj.addProperty("param", param);
    obj.addProperty("currMana", currMana);
    obj.addProperty("maxMana", maxMana);
    return obj;
  }

  @Override
  public String toString() {
    return String.format("*`мана: %d/%d`*, *`класc: %s`*, *`уровень: %d`*, *`параметр: %d`*",
        currMana, maxMana, className.toLowerCase(), level, param);
  }

}
