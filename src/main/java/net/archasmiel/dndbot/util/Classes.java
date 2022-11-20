package net.archasmiel.dndbot.util;

import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.archasmiel.dndbot.util.maps.BonusSpellPoints;
import net.archasmiel.dndbot.util.maps.MaxClassMaps;
import net.archasmiel.dndbot.util.maps.SpellPointsMaps;

/**
 * Every D&D 3.5 class data.
 */
@Getter
@AllArgsConstructor
public enum Classes {

  BARD("Bard",
      SpellPointsMaps.INSTANCE.bardlike,
      MaxClassMaps.INSTANCE.bardlike),
  CLERIC("Cleric",
      SpellPointsMaps.INSTANCE.clericlike,
      MaxClassMaps.INSTANCE.clericlike),
  DRUID("Druid",
      SpellPointsMaps.INSTANCE.clericlike,
      MaxClassMaps.INSTANCE.clericlike),
  WIZARD("Wizard",
      SpellPointsMaps.INSTANCE.clericlike,
      MaxClassMaps.INSTANCE.clericlike),
  PALADIN("Paladin",
      SpellPointsMaps.INSTANCE.paladinlike,
      MaxClassMaps.INSTANCE.paladinlike),
  RANGER("Ranger",
      SpellPointsMaps.INSTANCE.paladinlike,
      MaxClassMaps.INSTANCE.paladinlike),
  SORCERER("Sorcerer",
      SpellPointsMaps.INSTANCE.sorcererlike,
      MaxClassMaps.INSTANCE.sorcererlike),
  ARTIFICER("Artificer",
      SpellPointsMaps.INSTANCE.artificerlike,
      MaxClassMaps.INSTANCE.artificerlike);

  private final String name;
  private final Map<Integer, Integer> spellPoints;
  private final Map<Integer, Integer> maxSpell;

  /**
   * Method for getting mana information.

   * @param level level value
   * @param param spell casting parameter for class
   * @return ManaQuad with all mana data
   */
  public Optional<ManaQuad> getMana(int level, int param) {
    if (level < 1 || level > 20) {
      return Optional.empty();
    }
    if (param < 12 || param > 51) {
      return Optional.empty();
    }

    int spellPoints = this.spellPoints.get(level);
    int maxSpellNum = this.maxSpell.get(level);
    int bonusSpellPoints = BonusSpellPoints.INSTANCE.getValue(maxSpellNum, param);
    int maxMana = spellPoints + bonusSpellPoints;
    return Optional.of(new ManaQuad(spellPoints, maxSpellNum, bonusSpellPoints, maxMana));
  }

}
