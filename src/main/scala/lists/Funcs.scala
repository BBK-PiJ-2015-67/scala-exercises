package lists

/**
  * @author lmignot
  */
object Funcs extends App {

  def foldLeft[A,B](ls:List[A], el: B)(f: (B, A) => B): B = ls match {
    case Nil => el
    case hd :: tl => foldLeft(tl, f(el, hd))(f)
  }

  def map[A,B](ls: List[A])(f: A => B): List[B] = ls match {
    case Nil => Nil
    case hd :: tl => f(hd) :: map(tl)(f)
  }

  def flatten[A](ls: List[A]): List[A] = ls match {
    case Nil => Nil
    case (hd: List[A]) :: tl => flatten(hd) ::: flatten(tl)
    case hd :: tl => hd :: flatten(tl)
  }

  def flatMap[A,B](ls: List[A])(f: A => List[B]): List[B] = ???
}
