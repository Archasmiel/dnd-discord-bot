package net.archasmiel.dndbot.exception;

/**
 * Exception for no user in database.
 */
public class NoManaUserFound extends Exception {

  public NoManaUserFound() {
    super("Не найдено персонажа в системе");
  }

}
