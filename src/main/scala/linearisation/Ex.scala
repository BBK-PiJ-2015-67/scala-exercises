package linearisation

/**
  * @author lmignot
  */
object Ex extends App {
  class A {
    def func() = {
      println("calling A")
      "A"
    }
  }

  trait B extends A {
    override def func() = {
      println("calling B")
      super.func() + "B"
    }
  }

  trait C extends B {
    override def func() = {
      println("calling C")
      super.func() + "C"
    }
  }

  trait D extends B {
    override def func() = {
      println("calling D")
      super.func() + "D"
    }
  }

  trait E extends D {
    override def func(): String = {
      println("calling E")
      super.func() + "E"
    }
  }

  trait F extends A {
    override def func(): String = {
      println("calling F")
      super.func() + "F"
    }
  }

  def foobar (x: A) {
    println(x.func())
  }

  foobar(new A with F with C with B)

  // go from right to left
  // all have "func()" so they are acceptable
  // B gives us A
  // C gives us B (but already there so just C)
  // D gives us B (but already there so just D)

  // After Linearisation: DCBA (where to look for func())
  // Find func() in D, run and then stop
  // Output: BDC

}
