package net.archasmiel.dndbot.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ManaController {

  public static final String FILENAME = "mana.json";
  public static final Map<String, ManaUser> USERS = new HashMap<>();

  public static void readUsers() {
    try (Reader reader = Files.newBufferedReader(Paths.get(FILENAME))) {
      USERS.putAll(new Gson().fromJson(reader, new TypeToken<HashMap<String, ManaUser>>(){}.getType()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void writeUsers() {
    try (Writer writer = new FileWriter(FILENAME)) {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      JsonObject object = new JsonObject();
      USERS.forEach((s, user) -> object.add(s, user.asJsonObject()));
      gson.toJson(object, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static ManaUser get(String id) {
    return USERS.getOrDefault(id, null);
  }

}
