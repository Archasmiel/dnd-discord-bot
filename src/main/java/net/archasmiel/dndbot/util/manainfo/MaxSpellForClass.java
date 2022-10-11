package net.archasmiel.dndbot.util.manainfo;

import static net.archasmiel.dndbot.util.manainfo.maps.MaxClassMaps.ARTIFICERLIKE;
import static net.archasmiel.dndbot.util.manainfo.maps.MaxClassMaps.BARDLIKE;
import static net.archasmiel.dndbot.util.manainfo.maps.MaxClassMaps.CLERICLIKE;
import static net.archasmiel.dndbot.util.manainfo.maps.MaxClassMaps.PALADINLIKE;
import static net.archasmiel.dndbot.util.manainfo.maps.MaxClassMaps.SORCERERLIKE;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum MaxSpellForClass {

  BARD("Bard", BARDLIKE),
  CLERIC("Cleric", CLERICLIKE), DRUID("Druid", CLERICLIKE), WIZARD("Wizard", CLERICLIKE),
  PALADIN("Paladin", PALADINLIKE), RANGER("Ranger", PALADINLIKE),
  SORCERER("Sorcerer", SORCERERLIKE),
  ARTIFICER("Artificer", ARTIFICERLIKE);

  private final String name;
  private final Map<Integer, Integer> maxSpellMap;

  MaxSpellForClass(String name, Map<Integer, Integer> maxSpellMap) {
    this.name = name;
    this.maxSpellMap = maxSpellMap;
  }

  public static Integer getValue(String className, int level) {
    Optional<MaxSpellForClass> res = Arrays.stream(MaxSpellForClass.values())
        .filter(e -> e.getName().equals(className)).findFirst();
    return res.isPresent() ? res.get().getMaxSpellMap().get(level) : -100;
  }

}
