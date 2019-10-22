package _Tutorial

import java.text.DateFormat._
import java.util.{Date, Locale}

object _2Dates {
  def main(args: Array[String]): Unit = {
      val now = new Date()
      val date_format = getDateInstance(LONG, Locale.ENGLISH)
            //date_format.format(now)
      println(date_format format now)
  }

}
