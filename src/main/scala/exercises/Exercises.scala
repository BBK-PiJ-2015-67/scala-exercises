package exercises

import scala.annotation.tailrec

/**
  * @author lmignot
  */
object Exercises extends App {
  println(manyTimesStringTail("abc", 3))

  def manyTimesStringBoring(s: String, i: Int): String = {
    var output = ""
    for (i <- 1 until i) {
      output = output + s
    }
    output
  }

  def manyTimesString(s: String, i: Int): String = i match {
    case 1 => s
    case _ => s + manyTimesString(s, i - 1)
  }

  def manyTimesStringTail(s: String, i: Int): String = {
    @tailrec
    def helper (acc: String, i: Int): String = i match {
      case 0 => acc
      case _ => helper(s + acc, i - 1)
    }
    helper("", i)
  }

  var between = (temp: Int, b: Int, c: Int) => temp > b && temp < c

  println(between(70,80,90))

  def sumIt(args: Int*) = args.reduce(_ + _)

  println(sumIt(1, 2, 3))


  def factor(x: Int): BigInt = {
    @tailrec
    def helper(nums: List[Int], x: Int): BigInt = nums match {
      case hd :: _ if x % hd == 0 => hd
      case _ :: tl => helper(tl, x)
    }
    helper((2 until x).toList, x)
  }

  println(factor(12))

  type Matrix = Array[Array[Int]]

  println(checkBlock(Array(Array(1,2,3), Array(4,5,6), Array(7,8,9))))
  println(checkBlock(Array(Array(3,2,3), Array(4,1,4), Array(7,7,9))))

  def checkBlock(matrix: Matrix): Boolean =
    matrix.flatten
      .filter(_ <= 9)
      .filter(_ >= 1)
      .distinct
      .length == 9


}
