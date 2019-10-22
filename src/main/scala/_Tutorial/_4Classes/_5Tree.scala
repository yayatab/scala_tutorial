package _Tutorial._4Classes

/*
   ---
      The aim of this program is to manipulate very simple arithmetic expressions composed of sums,
      integer constants and variables. Two examples of such expressions are 1+2 and (x+x)+(7+y)
   ---
   ---
      after reading the code, you should see:
      https://docs.scala-lang.org/tutorials/scala-for-java-programmers.html#case-classes-and-pattern-matching
   ---
*/

abstract class Tree // this is interface
case class Sum(l: Tree, r: Tree) extends Tree

case class Var(n: String) extends Tree

case class Const(v: Int) extends Tree


/*

The fact that classes Sum, Var and Const are declared as case classes
means that they differ from standard classes in several respects:

• the new keyword is not mandatory to create instances of these classes
  (i.e., one can write Const(5) instead of new Const(5))
• getter functions are automatically defined for the constructor parameters
  (i.e., it is possible to get the value of the v constructor parameter of some
   instance c of class Const just by writing c.v)
• default definitions for methods equals and hashCode are provided, which work on
  the structure of the instances and not on their identity
• a default definition for method toString is provided, and prints the value in a “source form”
  (e.g., the tree for expression x+1 prints as Sum(Var(x),Const(1))),
• instances of these classes can be decomposed through pattern matching

 */



object _5Tree {
  // we can use Define like in cpp!
  type hui = Int
  type Environment = String => Int


  def eval(t: Tree, env: Environment): Int = t match {
    case Sum(l, r) => eval(l, env) + eval(r, env)
    case Var(n)    => env(n)
    case Const(v)  => v
  }

  def derive(t: Tree, v: String): Tree = t match {
    case Sum(l, r) => Sum(derive(l, v), derive(r, v))
    case Var(n) if (v == n) => Const(1)
    case _ => Const(0)
  }

  def main(args: Array[String]): Unit = {
    val exp: Tree = Sum(Sum(Var("x"),Var("x")),Sum(Const(7),Var("y")))
    val env: Environment = { case "x" => 5 case "y" => 7 }
    println("Expression: " + exp)
    println("Evaluation with x=5, y=7: " + eval(exp, env))
    println("Derivative relative to x:\n " + derive(exp, "x"))
    println("Derivative relative to y:\n " + derive(exp, "y"))
  }

}