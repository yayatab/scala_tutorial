package _Tutorial

object _3Functions {

  object Timer {
    def oncePerSecond(callback: () => Unit): Unit = {
      for (i <- 1 to 5) {
        callback();
        // Thread.sleep(1000)
        Thread sleep 500
      }
    }

    def timeFlies(): Unit = {
      println("time flies like an arrow...")
    }
  }

  def main(args: Array[String]): Unit = {
    Timer.oncePerSecond(Timer.timeFlies)
    Timer.oncePerSecond(() => println("anonymous func"))
  }
}
