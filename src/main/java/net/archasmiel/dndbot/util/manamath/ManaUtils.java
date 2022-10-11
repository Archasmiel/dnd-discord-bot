package net.archasmiel.dndbot.util.manamath;

import net.archasmiel.dndbot.database.ManaUser;
import net.archasmiel.dndbot.util.manainfo.MaxSpellForClass;
import net.archasmiel.dndbot.util.manainfo.SpellpointsForClass;
import net.archasmiel.dndbot.util.manainfo.maps.BonusSpellpoints;

public class ManaUtils {

  public static ManaQuad calcMana(ManaUser user) {
    int spellpoints = SpellpointsForClass.getValue(user.getClassName(), user.getLevel());
    int maxSpellNum = MaxSpellForClass.getValue(user.getClassName(), user.getLevel());
    int bonusSpellPoints = BonusSpellpoints.getValue(maxSpellNum, user.getParameter());
    int maxMana = spellpoints + bonusSpellPoints;
    return new ManaQuad(spellpoints, maxSpellNum, bonusSpellPoints, maxMana);
  }

}
