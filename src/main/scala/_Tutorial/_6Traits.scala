package _Tutorial

/*
 The easiest way for a Java programmer to understand what traits are is to view them as interfaces which can also
  contain code. In Scala, when a class inherits from a trait, it implements that trait’s interface,
  and inherits all the code contained in the trait.
 */

/*
  This definition both creates a new type called Ord, which plays the same role as Java’s Comparable interface,
  and default implementations of three predicates in terms of a fourth, abstract one.
  The predicates for equality and inequality do not appear here since they are by default present in all objects.
 */

trait Ord {
  // Any is a super-type of all other types in Scala.
  // it is also a super-type of basic types like Int, Float, etc.
  def < (that: Any): Boolean  // this is an abstract method
  def <=(that: Any): Boolean =  (this < that) || (this == that)
  def > (that: Any): Boolean = !(this <= that)
  def >=(that: Any): Boolean = !(this < that)
}

class Date(y: Int, m: Int, d: Int) extends Ord {
  def year: Int = y
  def month: Int = m
  def day: Int = d
  override def toString(): String = year + "-" + month + "-" + day
  override def equals(that: Any): Boolean = // override from Object
    that.isInstanceOf[Date] && {
      val o = that.asInstanceOf[Date]
      o.day == day && o.month == month && o.year == year
    }
  def <(that: Any): Boolean = {
    if (!that.isInstanceOf[Date])
      sys.error("cannot compare " + that + " and a Date")

    val o = that.asInstanceOf[Date]
    (year < o.year) ||
      (year == o.year && (month < o.month ||
        (month == o.month && day < o.day)))
  }
}

class _6Traits {
  val d = new Date(1970, 1,1)

};
