package net.archasmiel.dndbot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Data structure for mana details.
 */
@Getter
@Setter
@AllArgsConstructor
public class ManaQuad {

  private int spellPoints;
  private int maxSpellLevel;
  private int bonusSpellPoints;

  public int getMaxMana() {
    return spellPoints + bonusSpellPoints;
  }

}
