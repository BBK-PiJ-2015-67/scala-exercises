package sorting

import scala.util.Random

/**
  * @author lmignot
  */
object Sort extends App {
  def qs(ls: List[Int]): List[Int] = ls match {
    case Nil => Nil
    case a :: Nil => List(a)
    case a :: tail => qs(tail.filter(_ <= a)) ::: List(a) ::: qs(tail.filter(_ > a))
  }
  val rand = new Random()
  val in = (1 to 970 by 2).toList.map(new Random(_).nextInt() * - 1)

  println(in)
  println(qs(in))
}
