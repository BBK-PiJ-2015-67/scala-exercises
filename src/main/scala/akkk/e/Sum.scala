package akkk.e

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

object Sum extends App {
  val list = List(List(1,4,6), List(67,98,45), List(34,23,22))
  val s = ActorSystem("sum")
  s.actorOf(Props(new Collector(list)))
}

class Collector(ls: List[List[Int]]) extends Actor with ActorLogging {
  var results: List[Int] = List.empty
  var size: Int = ls.size

  ls.foreach(x => context.actorOf(Props(new Calculator)) ! x)

  override def receive: Receive = {
    case (l: List[Int], sum: Int) =>
      size -= 1
      results +:= sum
      log.info(s"the sum of $l is: $sum")

      if (size == 0) context.system.terminate()
  }
}

class Calculator extends Actor {
  override def receive: Receive = {
    case ls: List[Int] => sender ! (ls, sum(ls))
  }

  def sum(ls: List[Int]): Int = ls.sum
}
