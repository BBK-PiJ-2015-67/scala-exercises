package akkk.c

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

import scala.annotation.tailrec

/**
  * @author lmignot
  */
object Factorial extends App {
  val factorials = List(20, 18, 32, 28, 22, 42, 55, 48)

  val system = ActorSystem("factorial")
  val collector = system.actorOf(Props(new FactorialCollector(factorials)), "collector")
}

class FactorialCollector(nums: List[Int]) extends Actor with ActorLogging {
  var results: List[BigInt] = List.empty
  var size: Int = nums.size

  for (n <- nums) {
    context.actorOf(Props(new FactorialCalculator)) ! n
  }

  def receive: Receive = {
    case (n: Int, factorial: BigInt) => {
      log.info(s"factorial for $n is $factorial")
      results :+= factorial
      size -= 1

      if (size == 0) context.system.terminate()
    }
  }
}

class FactorialCalculator extends Actor {
  def receive: Receive = {
    case num: Int => sender ! (num, factor(num))
  }

  private def factor (n: Int): BigInt = factorTail(n, 1)

  @tailrec
  private def factorTail (num: Int, acc: BigInt): BigInt = {
    (num, acc) match {
      case (0, a) => a
      case (n, a) => factorTail(n - 1, n * a)
    }
  }
}