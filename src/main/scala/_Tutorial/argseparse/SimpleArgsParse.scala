//package _Tutorial.argseparse
//
//import java.io.File
//
//import scopt.{OParser, Read}
//
//class Point(xi: Int = -1, yi: Int = -1) extends Read[(Int, Int)] {
//  def x(): Int = xi
//
//  def y(): Int = yi
//
//  override def arity: Int = 1
//
//  override def reads: String => (Int, Int) = huina
//
//
//  def huina(string: String): (Int, Int) = {
//    val strings = string.split(",").map(_.toInt)
//    (strings(0), strings(1))
//  }
//}
//
//
//
//case class SimpleConfid(hello: String = "hello", kwargs: Map[String, String] = null, p: Point = null) {} // ) {}
//
//object SimpleArgsParse {
//  val className = "SimpleArgsParse"
//  implicit def pathRead: scopt.Read[Point] = scopt.Read.stringRead.map{Point.huina(_)}
//
//  def main(args: Array[String]): Unit = {
//
//    val builder = OParser.builder[SimpleConfid]
//    val parser1 = {
//      import builder._
//      OParser.sequence(
//        programName(className),
//        opt[String]('h', "hello")
//          .action((x, c) => c.copy(hello = x))
//          .text("this is a string"),
//        opt[Map[String, String]]("kwargs")
//          .valueName("k1=v1,k2=v2...")
//          .action((x, c) => c.copy(kwargs = x))
//          .text("other arguments"),
//        opt[Point]("point")
//          .valueName("1,2")
//          .action((po,c) => c.copy(p = po))
//          .text("huina gdola"),
//        help("help").text("prints this usage text"))
//    }
//    //    val hui =  OParser.parse(parser1, args, SimpleConfid())
//    // OParser.parse returns Option[Config]
//    OParser.parse(parser1, args, SimpleConfid()) match {
//      case Some(config) =>
//      // do something
//      case _ =>
//        parser1
//      // arguments are bad, error message will have been displayed
//    }
//  }
//
//}
