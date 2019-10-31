package GameOfLife

import scala.math.{sqrt, pow}

// i know it's redundnt. im exploring scala.
class Point(row: Int, col: Int) {

  def this(tuple : (Int, Int)) {
    this(tuple._1, tuple._2)
  }

  def x: Int = row

  def y: Int = col

  private val sqrt2 = sqrt(2);

  def isNear(o: Point): Boolean = {
    val dist = sqrt(pow(o.x - this.x, 2) + pow(o.y - this.y, 2))
    if (dist == 1 || dist == sqrt2)
      return true
    else
      return false
  }

}
