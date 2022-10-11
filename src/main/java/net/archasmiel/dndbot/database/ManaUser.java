package net.archasmiel.dndbot.database;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManaUser {

  public int maxMana;
  public int currMana;

  public ManaUser(int maxMana, int currMana) {
    this.maxMana = maxMana;
    this.currMana = currMana;
  }

}
