package net.archasmiel.dndmanabot.util.mana;

import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.archasmiel.dndmanabot.util.mana.maps.BonusSpellPoints;
import net.archasmiel.dndmanabot.util.mana.maps.MaxSpellLevel;
import net.archasmiel.dndmanabot.util.mana.maps.SpellPoints;

/**
 * Every D&D 3.5 class data.
 */
@Getter
@AllArgsConstructor
public enum ClassesDnD {

  BARD("Bard",
      SpellPoints.INSTANCE.bardlike,
      MaxSpellLevel.INSTANCE.bardlike),
  CLERIC("Cleric",
      SpellPoints.INSTANCE.clericlike,
      MaxSpellLevel.INSTANCE.clericlike),
  DRUID("Druid",
      SpellPoints.INSTANCE.clericlike,
      MaxSpellLevel.INSTANCE.clericlike),
  WIZARD("Wizard",
      SpellPoints.INSTANCE.clericlike,
      MaxSpellLevel.INSTANCE.clericlike),
  PALADIN("Paladin",
      SpellPoints.INSTANCE.paladinlike,
      MaxSpellLevel.INSTANCE.paladinlike),
  RANGER("Ranger",
      SpellPoints.INSTANCE.paladinlike,
      MaxSpellLevel.INSTANCE.paladinlike),
  SORCERER("Sorcerer",
      SpellPoints.INSTANCE.sorcererlike,
      MaxSpellLevel.INSTANCE.sorcererlike),
  ARTIFICER("Artificer",
      SpellPoints.INSTANCE.artificerlike,
      MaxSpellLevel.INSTANCE.artificerlike);

  private final String name;
  private final Map<Integer, Integer> spellPointsMap;
  private final Map<Integer, Integer> maxSpellMap;

  /**
   * Method for getting mana information.

   * @param level level value
   * @param param spell casting parameter for class
   * @return ManaQuad with all mana data
   */
  public Optional<ManaQuad> getMana(int level, int param) {
    if ((level < 1 || level > 20) || (param < 12 || param > 51)) {
      return Optional.empty();
    }

    int spellPoints = this.spellPointsMap.get(level);
    int maxSpellNum = this.maxSpellMap.get(level);
    int bonusSpellPoints = BonusSpellPoints.INSTANCE.getValue(maxSpellNum, param);
    return Optional.of(new ManaQuad(spellPoints, maxSpellNum, bonusSpellPoints));
  }

}
