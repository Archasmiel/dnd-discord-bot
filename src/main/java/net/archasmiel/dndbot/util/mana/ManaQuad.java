package net.archasmiel.dndbot.util.mana;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Data structure for mana details.
 */
@Getter
@AllArgsConstructor
public class ManaQuad {

  private int spellPoints;
  private int maxSpellLevel;
  private int bonusSpellPoints;

  public int getMaxMana() {
    return spellPoints + bonusSpellPoints;
  }

}
