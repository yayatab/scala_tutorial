package _Tutorial.argseparse
import scopt.OParser

import java.io.File

object MainArgsThingy {

  def main(args: Array[String]): Unit = {
    val builder = OParser.builder[ExampleArgs]
    val parser1 = {
      import builder._
      OParser.sequence(
        programName("scopt"),
        head("scopt", "4.x"),
        opt[Int]('f', "foo")
          .action((x, c) => c.copy(foo = x))
          .text("foo is an integer property"),
        opt[File]('o', "out")
          .required()
          .valueName("<file>")
          .action((x, c) => c.copy(out = x))
          .text("out is a required file property"),
        opt[(String, Int)]("max")
          .action({ case ((k, v), c) => c.copy(libName = k, maxCount = v) })
          .validate(x =>
            if (x._2 > 0) success
            else failure("Value <max> must be >0"))
          .keyValueName("<libname>", "<max>")
          .text("maximum count for <libname>"),
        opt[Seq[File]]('j', "jars")
          .valueName("<jar1>,<jar2>...")
          .action((x, c) => c.copy(jars = x))
          .text("jars to include"),
        opt[Map[String, String]]("kwargs")
          .valueName("k1=v1,k2=v2...")
          .action((x, c) => c.copy(kwargs = x))
          .text("other arguments"),
        opt[Unit]("verbose")
          .action((_, c) => c.copy(verbose = true))
          .text("verbose is a flag"),
        opt[Unit]("debug")
          .hidden()
          .action((_, c) => c.copy(debug = true))
          .text("this option is hidden in the usage text"),
        help("help").text("prints this usage text"),
        arg[File]("<file>...")
          .unbounded()
          .optional()
          .action((x, c) => c.copy(files = c.files :+ x))
          .text("optional unbounded args"),
        note("some notes." + sys.props("line.separator")),
        cmd("update")
          .action((_, c) => c.copy(mode = "update"))
          .text("update is a command.")
          .children(
            opt[Unit]("not-keepalive")
              .abbr("nk")
              .action((_, c) => c.copy(keepalive = false))
              .text("disable keepalive"),
            opt[Boolean]("xyz")
              .action((x, c) => c.copy(xyz = x))
              .text("xyz is a boolean property"),
            opt[Unit]("debug-update")
              .hidden()
              .action((_, c) => c.copy(debug = true))
              .text("this option is hidden in the usage text"),
            checkConfig(
              c =>
                if (c.keepalive && c.xyz) failure("xyz cannot keep alive")
                else success)
          )
      )
    }
    val hui =  OParser.parse(parser1, args, ExampleArgs())
    // OParser.parse returns Option[Config]
    OParser.parse(parser1, args, ExampleArgs()) match {
      case Some(config) =>
      // do something
      case _ =>
        parser1
      // arguments are bad, error message will have been displayed
    }
  }

}
