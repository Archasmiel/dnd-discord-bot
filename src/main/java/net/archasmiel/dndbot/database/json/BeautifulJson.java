package net.archasmiel.dndbot.database.json;

public class BeautifulJson {

  private BeautifulJson() {

  }

  public static String beautifulJSON(String input) {
    int tabCount = 0;

    StringBuilder inputBuilder = new StringBuilder();
    char[] inputChar = input.toCharArray();

    for (int i = 0; i < inputChar.length; i++) {
      String charI = String.valueOf(inputChar[i]);

      if (charI.equals("}") || charI.equals("]")) {
        tabCount--;
        if (!String.valueOf(inputChar[i - 1]).equals("[") && !String.valueOf(inputChar[i - 1]).equals("{"))
          inputBuilder.append(newLine(tabCount));
      }
      inputBuilder.append(charI);

      if (charI.equals("{") || charI.equals("[")) {
        tabCount++;
        if (String.valueOf(inputChar[i + 1]).equals("]") || String.valueOf(inputChar[i + 1]).equals("}"))
          continue;

        inputBuilder.append(newLine(tabCount));
      }

      if (charI.equals(",")) {
        inputBuilder.append(newLine(tabCount));
      }
    }

    return inputBuilder.toString();
  }

  private static String newLine(int tabCount) {
    return "\n" + "  ".repeat(Math.max(0, tabCount));
  }
}
