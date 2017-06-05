package variance

/**
  * Playing around with type variance in Scala
  * This file fails to compile due to violations
  * commented below
  */

case class Covariant[+A] ()
case class Contravariant[-A] ()
case class Invariant[A] ()

trait Ax
trait Bx extends Ax
case class C()
case class D() extends Bx
object Variance extends App {

  val bxx: Covariant[Ax] = Covariant[Ax]()
  val bx: Covariant[Ax] = Covariant[Bx]()
  val d: Covariant[Ax] = Covariant[D]()
  // fails to compile, AnyRef is not covariant of A
  val bxxx: Covariant[Ax] = Covariant[AnyRef]()

  val c: Invariant[Ax] = Invariant[Ax]()
  // fails to compile, Bx is not invariant of A
  val cx: Invariant[Ax] = Invariant[Bx]()
  // fails to compile, Bx is not invariant of A
  val cdx: Invariant[Ax] = Invariant[D]()

  val i: Contravariant[Ax] = Contravariant[Ax]()
  val ij: Contravariant[Ax] = Contravariant[AnyRef]()
  // fails to compile, D is not contravariant of A
  val ijx: Contravariant[Ax] = Contravariant[D]()
  // fails to compile, Bx is not contravariant of A
  val ijxb: Contravariant[Ax] = Contravariant[Bx]()
}
