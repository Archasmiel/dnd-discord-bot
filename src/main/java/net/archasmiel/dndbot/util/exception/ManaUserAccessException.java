package net.archasmiel.dndbot.util.exception;

/**
 * Exception for illegal parameters in command call.
 */
public class ManaUserAccessException extends Exception {

  public ManaUserAccessException(String id) {
    super("Персонаж с id `" + id + "` не принадлежит вам");
  }

}
