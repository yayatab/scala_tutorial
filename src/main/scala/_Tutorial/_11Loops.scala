package _Tutorial

import com.google.common.collect.Maps

import scala.collection.mutable.ListBuffer

object _11Loops  extends App {
  //generates a Vector: 16, 18, 20, 22, 24, 26, 28, 30, 32, 34 etc...
  val s = for (x <- 1 to 25 if x*x > 50) yield 2*x
  val t = for (x <- 1 to 25 if x*x > 50) yield 2*x
  val u = for (x <- 1 to 25 if x*x > 50) yield 2*x
  println(s)
  println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")

  val z : ListBuffer[Int] = new ListBuffer[Int]
  for (x <-s; y <-t) {
    z.append(x+y)
  }
  print(z.length)


  val xx = new ListBuffer[Int]()
  Array(1,2,3,5,7,9).foreach(xx.append)
  for (x <- xx) {
    if (x % 2 == 0) {
      xx.append(11)
    }
    print(x + ",")
  }






  //  val map : Map[Int, String] = _
//  val accounts:[]
//  val dem_mentions = for {
//    (mention, times) <- map
//    account          <- accounts.get(mention)
//    if account.party == "Democratic"
//  } yield (account, times)
}
