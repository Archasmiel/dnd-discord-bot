package net.archasmiel.dndbot.database;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManaUser {

  public int maxMana;
  public int currMana;

  public String className;
  public int level;
  public int parameter;

  public ManaUser(int maxMana, int currMana, String className, int level, int parameter) {
    this.maxMana = maxMana;
    this.currMana = currMana;
    this.className = className;
    this.level = level;
    this.parameter = parameter;
  }

  public boolean isFinished() {
    return maxMana >= 0 && className != null && (level >= 1 && level <= 20) && parameter > 0;
  }
  @Override
  public String toString() {
    return
        "maxMana=" + maxMana +
        ", currMana=" + currMana +
        ", class='" + className + '\'' +
        ", level=" + level +
        ", param=" + parameter;
  }
}
