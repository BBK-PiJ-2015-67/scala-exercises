package dps

import scala.collection.mutable

/**
  * @author lmignot
  * memoization is technique
  */
object Memoize extends App {
  def fib(n: Int): Long = if (n <= 2) 1 else fib(n-1) + fib(n-2)

  def memoize[A,B](f: A => B): A => B = {
    val store = new mutable.HashMap[A,B]()
    (a: A) => store get a match {
      case Some(b) => b
      case None =>
        val value = f(a)
        store(a) = value
        value
    }
  }

  println(memoize(fib)(10))
  println(memoize(fib)(45))
}