package _Tutorial
import com.google.common;


object _9Guava extends App {

  val joiner: Joiner = Joiner.on("; ").skipNulls();

  return joiner.join("Harry", null, "Ron", "Hermione");

}
