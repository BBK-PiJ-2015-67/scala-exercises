package exercises

/**
  * @author lmignot
  */
object Dwarves extends App {
  type Duel = (String, String)

  println(duels(List("Thorin", "Gloin", "Balin")))
  println(duelsFC(List("Thorin", "Gloin", "Balin")))

  def duels(dwarves: List[String]): List[Duel] = {
    dwarves
      .flatMap(x => dwarves.map(y => (x,y)))
      .filter(a => a._1 != a._2)
  }

  def duelsFC(dwarves: List[String]): List[Duel] = {
    for {
      x <- dwarves
      z <- for (y <- dwarves) yield (x, y)
      if z._1 != z._2
    } yield z
  }

  def duelsRec(dwarves: List[String]): List[Duel] = ???
}
