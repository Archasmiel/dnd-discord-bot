package net.archasmiel.dndbot.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Controller for all mana operations.
 */
public class ManaController {

  public static final ManaController INSTANCE = new ManaController();
  public final String filename = "mana.json";
  public final Map<String, ManaUser> users = new HashMap<>();

  /**
   * Read all users to Map.
   */
  public void readUsers() {
    checkFile();
    try (Reader reader = Files.newBufferedReader(Paths.get(filename))) {
      Type type = new TypeToken<HashMap<String, ManaUser>>(){}.getType();
      users.putAll(new Gson().fromJson(reader, type));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Write all users to json file.
   */
  public void writeUsers() {
    checkFile();
    try (Writer writer = new FileWriter(filename)) {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      JsonObject object = new JsonObject();
      users.forEach((s, user) -> object.add(s, user.asJsonObject()));
      gson.toJson(object, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Optional<ManaUser> get(String id) {
    ManaUser user = users.getOrDefault(id, null);
    return user == null ? Optional.empty() : Optional.of(user);
  }

  private void checkFile() {
    File file = new File(filename);
    try {
      if (file.createNewFile()) {
        System.out.printf("Created new '%s'!", filename);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
