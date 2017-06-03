package akkk.d

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

import scala.annotation.tailrec


/**
  * @author lmignot
  */
object Fibonacci extends App {
  val system = ActorSystem("fibonacci")
  val collector = system.actorOf(Props(new Collector(List(4, 9, 14, 20, 36, 42, 56, 64))), "collector")
}

class Collector(nums: List[Int]) extends Actor with ActorLogging {
  var results: List[BigInt] = List.empty
  var size: Int = nums.size

  for (n <- nums) {
    context.actorOf(Props(new Calculator)) ! n
  }

  def receive: Receive = {
    case (num: Int, result: BigInt) =>
      log.info(s"result for $num is $result")

      results :+= result
      size -= 1

      if (size == 0) context.system.terminate()
  }
}

class Calculator extends Actor {
  def receive: Receive = {
    case n: Int => sender ! (n, fibonacci(n))
  }

  private def fibonacci(n: Int) = {
    @tailrec def helper(x: Int, prev: BigInt = 0, next: BigInt = 1): BigInt = x match {
      case 0 => prev
      case 1 => next
      case _ => helper(x - 1, next, next + prev)
    }
    helper(n)
  }
}