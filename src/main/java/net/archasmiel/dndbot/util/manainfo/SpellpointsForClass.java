package net.archasmiel.dndbot.util.manainfo;

import static net.archasmiel.dndbot.util.manainfo.maps.SpellpointsMaps.ARTIFICERLIKE;
import static net.archasmiel.dndbot.util.manainfo.maps.SpellpointsMaps.BARDLIKE;
import static net.archasmiel.dndbot.util.manainfo.maps.SpellpointsMaps.CLERICLIKE;
import static net.archasmiel.dndbot.util.manainfo.maps.SpellpointsMaps.PALADINLIKE;
import static net.archasmiel.dndbot.util.manainfo.maps.SpellpointsMaps.SORCERERLIKE;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum SpellpointsForClass {

  BARD("Bard", BARDLIKE),
  CLERIC("Cleric", CLERICLIKE), DRUID("Druid", CLERICLIKE), WIZARD("Wizard", CLERICLIKE),
  PALADIN("Paladin", PALADINLIKE), RANGER("Ranger", PALADINLIKE),
  SORCERER("Sorcerer", SORCERERLIKE),
  ARTIFICER("Artificer", ARTIFICERLIKE);

  private final String name;
  private final Map<Integer, Integer> spellpointMap;

  SpellpointsForClass(String name, Map<Integer, Integer> spellpointMap) {
    this.name = name;
    this.spellpointMap = spellpointMap;
  }

  public static Integer getValue(String className, int level) {
    Optional<SpellpointsForClass> res = Arrays.stream(SpellpointsForClass.values())
        .filter(e -> e.getName().equals(className)).findFirst();
    return res.isPresent() ? res.get().getSpellpointMap().get(level) : -100;
  }

}
