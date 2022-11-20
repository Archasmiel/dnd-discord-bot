package net.archasmiel.dndbot.exception;

/**
 * Exception for illegal parameters in command call.
 */
public class IllegalParameters extends Exception {

  public IllegalParameters() {
    super("Неправильні аргументи для команди!");
  }

}
