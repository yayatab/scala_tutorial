package _Tutorial._4Classes

/* any class is ingerits from AnyRef */
class Complex(real: Double, imaginary: Double) /* extends scala.AnyRef */ {
  /*
    // this defines them as methods
    def re() = real
    def im() = imaginary
  */
  //this defines them as fields
  def re: Double = real

  def im: Double = imaginary

  private def is_good = true;


  override def toString: String = {
    real + "," + imaginary
  }

  /* --- "operator overloading" --- */

  def +(o: Complex): Complex = {
    new Complex(o.re + this.re, o.im + this.im)
  }

  def -(o: Complex): Complex = new Complex(o.re - this.re, o.im - this.im)

  def unary_~ : Double = {
    Math.sqrt(real * real + im * im)
  }

}

object Complex {

  def main(args: Array[String]): Unit = {
    val c1 = new Complex(4f, 4f)
    val c2 = new Complex(2, 2)
    val c3 = c1 - c2; // c1.-(c2)
    println(c1, c2, c3)
    println(~c3)

  }
}