package net.archasmiel.dndbot.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;

public class ManaController {

  public static final String FILENAME = "mana.json";
  public static final Map<String, ManaUser> USERS = new HashMap<>();

  public static void readUsers() {
    try {
      File file = new File(FILENAME);
      if (file.createNewFile()){
        try (FileWriter writer = new FileWriter(file)) {
          writer.append("{\n").append("}");
        }
      }

      try (FileInputStream is = new FileInputStream(file)) {
        JSONObject jsonObject = new JSONObject(new JSONTokener(is));
        jsonObject.keys().forEachRemaining(e -> {
            JSONObject jsonObj = jsonObject.getJSONObject(e);
            USERS.put(
                e, new ManaUser(jsonObj.getInt("max"), jsonObj.getInt("curr"))
            );
          }
        );
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void writeUsers() {
    try {
      File file = new File(FILENAME);
      if (file.createNewFile()){
        try (FileWriter writer = new FileWriter(file)) {
          writer.append("{\n").append("}");
        }
      }

      try (FileOutputStream os = new FileOutputStream(file)) {
        StringWriter writer = new StringWriter();
        JSONWriter jsonWriter = new JSONWriter(writer);
        jsonWriter.object();

        for (Map.Entry<String, ManaUser> entry: USERS.entrySet()) {
          jsonWriter
              .key(entry.getKey())
              .object()
              .key("max").value(entry.getValue().getMaxMana())
              .key("curr").value(entry.getValue().getCurrMana())
              .endObject();
        }
        jsonWriter.endObject();
        os.write(
            BeautifulJson.beautifulJSON(writer.toString()).getBytes()
        );
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
