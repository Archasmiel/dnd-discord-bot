package net.archasmiel.dndbot.database.basic;

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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Map for user objects.

 * @param <V> user object
 */
public class UserMap<V extends Identified & JsonConvertable> extends HashMap<String, V> {

  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

  private final String dirName;
  private final Class<V> typeClass;

  /**
   * Collection constructor.
   * All variables must be correct.

   * @param dirName directory name for data saving
   * @param typeClass class type - for Gson processing
   */
  public UserMap(String dirName, Class<V> typeClass) {
    super();
    this.dirName = dirName;
    this.typeClass = typeClass;
  }

  public Optional<V> getUser(String id) {
    V value = getOrDefault(id, null);
    return value == null ? Optional.empty() : Optional.of(value);
  }

  /**
   * Method for data saving in file.

   * @param id object id, object must be Identified
   */
  public void saveData(String id) {
    String fileName = String.format("%s/%s.json", dirName, id);
    createFile(fileName);
    try (Writer writer = new FileWriter(fileName)) {
      JsonObject object = this.get(id).asJsonObject();
      GSON.toJson(object, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method for data reading from file with correct json structure.
   */
  public void readData() {
    filesFromDir(dirName).forEach(e -> {
      try (Reader reader = Files.newBufferedReader(Paths.get(e))) {
        V value = GSON.fromJson(reader, typeClass);
        super.put(value.getId(), value);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    });
  }

  private List<String> filesFromDir(String path) {
    try (Stream<Path> stream = Files.walk(Paths.get(path))) {
      return stream.filter(Files::isRegularFile)
          .map(Path::toString).toList();
    } catch (IOException io) {
      return Collections.emptyList();
    }
  }

  private void createFile(String fileName) {
    File file = new File(fileName);
    try {
      if (file.createNewFile()) {
        System.out.printf("Created new file '%s'%n", file);
      }
    } catch (IOException e) {
      System.out.printf("File creation exception '%s'%n", file);
    }
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
