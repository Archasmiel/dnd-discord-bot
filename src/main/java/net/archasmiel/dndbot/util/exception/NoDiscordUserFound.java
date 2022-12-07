package net.archasmiel.dndbot.util.exception;

/**
 * Exception for no user in database.
 */
public class NoDiscordUserFound extends Exception {

  public NoDiscordUserFound() {
    super("Не найдено пользователя в системе");
  }

}
