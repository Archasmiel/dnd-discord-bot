package net.archasmiel.dndbot.util;

import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.archasmiel.dndbot.util.maps.BonusSpellpoints;
import net.archasmiel.dndbot.util.maps.MaxClassMaps;
import net.archasmiel.dndbot.util.maps.SpellpointsMaps;

@Getter
@AllArgsConstructor
public enum Classes {

  BARD("Bard", SpellpointsMaps.BARDLIKE, MaxClassMaps.BARDLIKE),
  CLERIC("Cleric", SpellpointsMaps.CLERICLIKE, MaxClassMaps.CLERICLIKE),
  DRUID("Druid", SpellpointsMaps.CLERICLIKE, MaxClassMaps.CLERICLIKE),
  WIZARD("Wizard", SpellpointsMaps.CLERICLIKE, MaxClassMaps.CLERICLIKE),
  PALADIN("Paladin", SpellpointsMaps.PALADINLIKE, MaxClassMaps.PALADINLIKE),
  RANGER("Ranger", SpellpointsMaps.PALADINLIKE, MaxClassMaps.PALADINLIKE),
  SORCERER("Sorcerer", SpellpointsMaps.SORCERERLIKE, MaxClassMaps.SORCERERLIKE),
  ARTIFICER("Artificer", SpellpointsMaps.ARTIFICERLIKE, MaxClassMaps.ARTIFICERLIKE);

  private final String name;
  private final Map<Integer, Integer> spellpoints;
  private final Map<Integer, Integer> maxSpell;

  public Optional<ManaQuad> getMana(int level, int param) {
    if (level < 1 || level > 20) return Optional.empty();
    if (param < 12 || param > 51) return Optional.empty();

    int spellpoints = this.spellpoints.get(level);
    int maxSpellNum = this.maxSpell.get(level);
    int bonusSpellPoints = BonusSpellpoints.getValue(maxSpellNum, param);
    int maxMana = spellpoints + bonusSpellPoints;
    return Optional.of(new ManaQuad(spellpoints, maxSpellNum, bonusSpellPoints, maxMana));
  }

}
