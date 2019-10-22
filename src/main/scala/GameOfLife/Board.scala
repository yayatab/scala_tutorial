package GameOfLife

import com.google.common.base.Joiner
import scala.jdk.CollectionConverters._

class Board() {

  /* --- private members --- */

  private var m_board: Array[Array[Char]] = _

  /* --- constructor --- */

  def this(size: Int) {
    this()
    println("this constructor")
    m_board = Array.tabulate[Char](size, size)((_, _) => 'X')
  }

  /* --- Override methods --- */


  override def toString: String = {
    val builder: StringBuilder = new StringBuilder
    for (row <- m_board) {
      builder.append(row.mkString(" | ")).append("\n")
    }

    builder.toString()
  }
}
