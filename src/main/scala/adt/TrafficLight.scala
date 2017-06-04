package adt

sealed trait TrafficLight

final case object Red extends TrafficLight
final case object Green extends TrafficLight
final case object Amber extends TrafficLight

object Test extends App {
  def next(tl: TrafficLight): TrafficLight = tl match {
    case Red =>
      println(s"it's $tl now")
      Green
    case Green =>
      println(s"it's $tl now")
      Amber
    case Amber =>
      println(s"it's $tl now")
      Red
  }

  val n = next(Red)
  val n2 = next(n)
  val n3 = next(n2)
}
