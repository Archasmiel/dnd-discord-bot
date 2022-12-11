package net.archasmiel.dndmanabot.database.basic;

import com.google.gson.JsonObject;

/**
 * Interface for objects convertible to JsonObject.
 */
public interface JsonConvertable {

  JsonObject asJsonObject();

}
