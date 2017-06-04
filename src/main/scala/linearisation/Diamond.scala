package linearisation

class A {
  def func: String = "Class A: "
}
trait B extends A {
  override def func: String = super.func + "trait B: "
}
trait C extends B {
  override def func: String = super.func + "trait C: "
}
trait D extends B {
  override def func: String = super.func + "trait D: "
}
trait E extends C with D {
  override def func: String = super.func + "trait E: "
}
trait F extends E with C with D {
  override def func: String = super.func + "trait F "
}

object Diamond extends App {

  val a = new A()
  // A > AnyRef > Any
  // Class A:
  val ab = new A() with B
  // A > AnyRef > Any > B > A > AnyRef > Any
  // B > A > AnyRef > Any
  // Class A: trait B:
  val ac = new A() with C
  // A > AnyRef > Any > C > B > A > AnyRef > Any
  // C > B > A > AnyRef > Any
  // Class A: trait B: trait C:
  val ad = new A() with D
  // A > AnyRef > Any > D > B > A > AnyRef > Any
  // D > B > A > AnyRef > Any
  // Class A: trait B: trait D:
  val ae = new A() with E
  // A > AnyRef > Any E > D > B > A > AnyRef > Any > C > B > A > AnyRef > Any
  // E > D > C > B > A > AnyRef > Any
  // Class A: trait B: trait C: trait D: trait E
  val aebcd = new A() with E with B with C with D
  // EBCD
  // DCBE
  // D > B > A > AnyRef > Any > AnyRef > Any
  // C > B > A > AnyRef > Any > AnyRef > Any
  // B > A > AnyRef > Any
  // E > D > B > A > AnyRef > Any > A > AnyRef > Any > C > B > A > AnyRef > Any > A > AnyRef > Any
  // E > D > C > B > A > AnyRef > Any
  // Class A: trait B: trait C: trait D: trait E

  val aedcb = new A() with E with D with C with B
  // Class A: trait B: trait D: trait C: trait E

  val af = new A() with F
  // F >
  // C > B > A > AnyRef > Any > A > AnyRef > Any >
  // D > B > A > AnyRef > Any > A > AnyRef > Any >
  // E > C > B > A > AnyRef > Any > A > AnyRef > Any > D > B > A > AnyRef > Any > A > AnyRef > Any
  // F > E > C > D > B > A > AnyRef > Any
  // Class A: trait B: trait C: trait D: trait E: trait F

  val afx = new A() with F with C with B with D with E
  // FCBDE
  // EDBCF

  val abf = new A() with B with F
  // BF
  // FB


  foo(a)
  foo(ab)
  foo(ac)
  foo(ad)
  foo(ae)
  foo(aebcd)
  foo(aedcb)
  foo(af)
  foo(afx)

  def foo(cls: A): Unit = {
    println(cls.func)
  }
}
