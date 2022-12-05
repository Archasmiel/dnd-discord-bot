package net.archasmiel.dndbot.database.objects;

import com.google.gson.JsonObject;

/**
 * Interface for objects convertible to JsonObject.
 */
public interface JsonConvertible {

  JsonObject asJsonObject();

}
