package net.archasmiel.dndbot.database.basic;

import com.google.gson.JsonObject;

/**
 * Interface for objects convertible to JsonObject.
 */
public interface JsonConvertable {

  JsonObject asJsonObject();

}
