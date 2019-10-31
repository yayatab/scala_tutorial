package GameOfLife

//three states for a cell - empty, dead or alive.

abstract class Cell(rep: Char) {
  private def stat: Char = rep

  def shouldBeAlive(myPos: (Int, Int), others: Array[(Int, Int)]): Boolean = {
    //must be a better way
    val tmp: List[Point] = List[Point]()
    others.foreach(t => tmp.appended(t))
    shouldBeAlive(new Point(myPos), tmp.toArray)
  }

  //we assume that the cell is dead from the start
  def shouldBeAlive(myPos: Point, others: Array[Point]): Boolean = {
    var liveCount = 0
    others.foreach(p => if (myPos.isNear(p)) {
      liveCount += 1
    })
    if (liveCount == 3) {
      return true
    }
    false
  }

  /* -- Override methods-- */

  override def toString = s"$stat"

}

case class Alive() extends Cell('O') {
  //
  override def shouldBeAlive(myPos: Point, others: Array[Point]): Boolean = {
    var liveCount = 0
    others.foreach(p => if (myPos.isNear(p)) {
      liveCount += 1
    })
    if (liveCount > 3) {
      return false
    } else if (liveCount == 2 || liveCount == 3) {
      return true
    }
    false
  }
}

private case class Dead() extends Cell('X')

private case class Empty() extends Cell('-')