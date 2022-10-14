package net.archasmiel.dndbot.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ManaUser {

  public int maxMana;
  public int currMana;
  public String className;
  public int level;
  public int parameter;

  public void setAll(String className, int level, int parameter, int maxMana, int currMana) {
    this.className = className;
    this.level = level;
    this.parameter = parameter;
    this.maxMana = maxMana;
    this.currMana = currMana;
  }

  public void setMana(int maxMana, int currMana) {
    this.maxMana = maxMana;
    this.currMana = currMana;
  }

  public void setMana(int mana) {
    this.maxMana = mana;
    this.currMana = mana;
  }

  @Override
  public String toString() {
    return String.format("***мана: %d/%d***, ***клас: %s***, ***рівень: %d***, ***параметр: %d***",
        currMana, maxMana, className.toLowerCase(), level, parameter);
  }

}
