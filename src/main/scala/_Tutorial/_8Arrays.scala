package _Tutorial

import java.util

object _8Arrays extends App {

  var t1: Array[Int] = new Array[Int](5)
  var t2 = Array.ofDim[Int](2,2)
  println(util.Arrays.toString(t1))
  println(t1.getClass.getName)
  println(t2(0).getClass.getName)
  println(t2.getClass.getName)
  for (typ <- t2) {
    for (hui <- typ) {
      print(hui + ",")
    }
    println()
  }


}
