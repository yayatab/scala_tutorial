package _Tutorial

import com.google.common.collect.Maps

object _11Loops  extends App {
  //generates a Vector: 16, 18, 20, 22, 24, 26, 28, 30, 32, 34 etc...
  val s = for (x <- 1 to 25 if x*x > 50) yield 2*x
  println(s)



//  val map : Map[Int, String] = _
//  val accounts:[]
//  val dem_mentions = for {
//    (mention, times) <- map
//    account          <- accounts.get(mention)
//    if account.party == "Democratic"
//  } yield (account, times)
}
