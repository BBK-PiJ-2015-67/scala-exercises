package exercises

/**
  * @author lmignot
  */
object Dwarves extends App {
  type Duel = (String, String)

  println(duels(List("Thorin", "Gloin", "Balin")))

//  println(List("Thorin", "Gloin", "Balin").combinations(2).toList)

  def duels(dwarves: List[String]): List[Duel] = {
    dwarves
      .flatMap(x => dwarves.map(y => (x,y)))
      .filter(a => a._1 != a._2)
  }

}
