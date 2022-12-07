package net.archasmiel.dndbot.database.objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.archasmiel.dndbot.database.basic.Identified;
import net.archasmiel.dndbot.database.basic.JsonConvertable;

/**
 * Mana user with all parameters.
 */
@Getter
@AllArgsConstructor
public class DiscordUser implements JsonConvertable, Identified {

  private String id;

  private List<String> manaUserIds;
  @Setter
  private String manaUserId;

  /**
   * Set all user parameters.
   */
  public void setAll(String id, List<String> manaUserIds, String manaUserId) {
    this.id = id;
    this.manaUserIds = manaUserIds;
    this.manaUserId = manaUserId;
  }

  /**
   * Returns json object with all parameters.

   * @return json object
   */
  public JsonObject asJsonObject() {
    JsonObject obj = new JsonObject();
    obj.addProperty("id", id);
    JsonArray arr = new JsonArray();
    manaUserIds.forEach(arr::add);
    obj.add("manaUserIds", arr);
    obj.addProperty("manaUserId", manaUserId);
    return obj;
  }

  @Override
  public String toString() {
    return "DiscordUser{"
        + "id='" + id + '\''
        + ", manaUserIds=" + manaUserIds
        + ", manaUserId='" + manaUserId + '\''
        + '}';
  }

}
