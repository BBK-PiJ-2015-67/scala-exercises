package lists

/**
  * @author lmignot
  */
object Example extends App {

  def f(xs: List[Int], g: (Int, Int) => Boolean) = {
    def h(x: Int, xs: List[Int]): List[Int] = {
      xs match {
        case List() => List(x)
        case y :: ys => if (!g(x, y)) x :: xs else y :: h(x, ys)
      }
    }
    (xs :\ List[Int]())(h)
  }

  val x = f(List(3, 6, 1, 3, 8, 7, 9, 1), (_ > _))

  def sum(xs: List[Int]): Int = {
    if (xs.nonEmpty) xs.head + sum(xs.tail)
    0
  }

  println(sum(List(4,5,78,3,2,7)))
}
