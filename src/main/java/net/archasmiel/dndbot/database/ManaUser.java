package net.archasmiel.dndbot.database;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Mana user with all parameters.
 */
@Getter
@Setter
@AllArgsConstructor
public class ManaUser {

  public String className;
  public int level;
  public int param;
  public int currMana;
  public int maxMana;

  /**
   * Set all character parameters.

   * @param className D&D class name
   * @param level level value
   * @param param param value
   * @param maxMana maximum mana value
   * @param currMana current mana value
   */
  public void setAll(String className, int level, int param, int maxMana, int currMana) {
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

  /**
   * Returns json object with all parameters.

   * @return json object
   */
  public JsonObject asJsonObject() {
    JsonObject obj = new JsonObject();
    obj.addProperty("className", className);
    obj.addProperty("level", level);
    obj.addProperty("param", param);
    obj.addProperty("currMana", currMana);
    obj.addProperty("maxMana", maxMana);
    return obj;
  }

  @Override
  public String toString() {
    return String.format("*`мана: %d/%d`*, *`клас: %s`*, *`рівень: %d`*, *`параметр: %d`*",
        currMana, maxMana, className.toLowerCase(), level, param);
  }

}
