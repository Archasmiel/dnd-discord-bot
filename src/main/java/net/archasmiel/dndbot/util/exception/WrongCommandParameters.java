package net.archasmiel.dndbot.util.exception;

/**
 * Exception for illegal parameters in command call.
 */
public class WrongCommandParameters extends Exception {

  public WrongCommandParameters() {
    super("Неправильные аргументы команды");
  }

}
