package net.archasmiel.dndbot.database.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Pair with two values.

 * @param <A> Type of first value
 * @param <B> Type of second value
 */
@Getter
@Setter
@AllArgsConstructor
public class Pair<A, B> {

  private A first;
  private B second;

}
