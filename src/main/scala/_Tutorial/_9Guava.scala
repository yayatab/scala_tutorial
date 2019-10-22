package _Tutorial
import com.google.common.base.Joiner;


object _9Guava extends App {

  val joiner = Joiner.on("; ").skipNulls();

  print(joiner.join("Harry", null, "Ron", "Hermione"));

}
