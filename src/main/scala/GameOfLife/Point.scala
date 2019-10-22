package GameOfLife

import scala.math.{sqrt, pow}

class Point(row: Int, col: Int) {

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
