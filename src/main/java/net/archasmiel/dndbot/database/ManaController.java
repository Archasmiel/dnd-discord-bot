package net.archasmiel.dndbot.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import net.archasmiel.dndbot.database.objects.DiscordUser;
import net.archasmiel.dndbot.database.objects.JsonConvertible;
import net.archasmiel.dndbot.database.objects.ManaUser;

/**
 * Controller for all mana operations.
 */
public class ManaController {

  public static final ManaController INSTANCE = new ManaController();
  public final Map<String, ManaUser> manaUsers = new HashMap<>();
  public final Map<String, DiscordUser> discordUsers = new HashMap<>();

  /**
   * Load all users to Map.
   */
  public void loadUsers() {
    filesFromDir("discordUsers").forEach(e -> {
      try (Reader reader = Files.newBufferedReader(Paths.get(e))) {
        DiscordUser discordUser = new Gson().fromJson(reader, DiscordUser.class);
        discordUsers.put(discordUser.getId(), discordUser);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    });
  }

  /**
   * Load all characters to Map.
   */
  public void loadCharacters() {
    filesFromDir("manaUsers").forEach(e -> {
      try (Reader reader = Files.newBufferedReader(Paths.get(e))) {
        ManaUser manaUser = new Gson().fromJson(reader, ManaUser.class);
        manaUsers.put(manaUser.getId(), manaUser);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    });
  }

  /**
   * Save user to json file.
   */
  public void saveDiscordUser(String userId) {
    saveDataToFile("discordUsers/" + userId + ".json", discordUsers, userId);
  }

  /**
   * Save user to json file.
   */
  public void saveManaUser(String charId) {
    saveDataToFile("manaUsers/" + charId + ".json", manaUsers, charId);
  }

  private void saveDataToFile(String fileName,
      Map<String, ? extends JsonConvertible> map, String id) {
    createFile(fileName);
    try (Writer writer = new FileWriter(fileName)) {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      JsonObject object = map.get(id).asJsonObject();
      gson.toJson(object, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void createFile(String fileName) {
    File file = new File(fileName);
    try {
      if (file.createNewFile()) {
        System.out.printf("Created new '%s'%n", file);
      }
    } catch (IOException e) {
      System.out.printf("File creation exception of '%s'%n", file);
    }
  }

  private List<String> filesFromDir(String path) {
    try (Stream<Path> stream = Files.walk(Paths.get(path))) {
      return stream.filter(Files::isRegularFile)
          .map(Path::toString).toList();
    } catch (IOException io) {
      return Collections.emptyList();
    }
  }

  public Optional<ManaUser> getManaUser(String id) {
    ManaUser user = manaUsers.getOrDefault(id, null);
    return user == null ? Optional.empty() : Optional.of(user);
  }

  public Optional<DiscordUser> getDiscordUser(String id) {
    DiscordUser user = discordUsers.getOrDefault(id, null);
    return user == null ? Optional.empty() : Optional.of(user);
  }

  public void discordUserCheck(String id) {
    if (!ManaController.INSTANCE.discordUsers.containsKey(id)) {
      ManaController.INSTANCE.discordUsers.put(id, new DiscordUser(id, new ArrayList<>(), null));
      ManaController.INSTANCE.saveDiscordUser(id);
    }
  }

}
