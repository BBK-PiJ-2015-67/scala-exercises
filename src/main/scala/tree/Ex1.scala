package tree

sealed trait Tree
final case class Node(l: Tree, v: Int, r: Tree) extends Tree
final case object Leaf extends Tree

object Tree {

  def insert(n: Int, t: Tree): Tree = t match {
    case Leaf => Node(Leaf, n, Leaf)
    case Node(l, v, r) if n < v => Node(insert(n, l), v, r)
    case Node(l, v, r) => Node(l, v, insert(n, r))
  }

  def size(t: Tree): Int = t match {
    case Leaf => 0
    case Node(l, _, r) => 1 + size(l) + size(r)
  }

  def sizeT(t: Tree): Int = {
    def helper(acc: Int, ls: List[Tree]): Int = ls match {
      case Nil => acc
      case Leaf :: tl => helper(acc, tl)
      case Node(l, _, r) :: tl => helper(acc + 1, l :: r :: tl)
    }
    helper(0, List(t))
  }

  def find(n: Int, t: Tree):Boolean = t match {
    case Leaf => false
    case Node(l, v, _) if n < v => find(n, l)
    case Node(_, v, r) if n > v => find(n, r)
    case Node(_, v, _) => v == n
  }

  def sum(t: Tree): Int = t match {
    case Leaf => 0
    case Node(l, v, r) => v + sum(l) + sum(r)
  }

  def sumT(t: Tree): Int = {
    def helper(acc: Int, ls: List[Tree]): Int = ls match {
      case Nil => acc
      case Leaf :: tl => helper(acc, tl)
      case Node(l, v, r) :: tl => helper(acc + v, l :: r :: tl)
    }
    helper(0, List(t))
  }

  def average(t: Tree): Int = sumT(t) / sizeT(t)

  def inOrder(t: Tree): String = t match {
    case Leaf => ""
    case Node(l, v, r) => inOrder(l) + s" ${v.toString} " + inOrder(r)
  }

  def preOrder(t: Tree): String = t match {
    case Leaf => ""
    case Node(l, v, r) => s" ${v.toString} " + preOrder(l) + preOrder(r)
  }

  def postOrder(t: Tree): String = t match {
    case Leaf => ""
    case Node(l, v, r) => postOrder(l) + postOrder(r) + s" ${v.toString} "
  }
}

object Test extends App {
  import Tree._
  val s = Node(Leaf, 8, Leaf)
  val t = insert(4, insert(5, insert(7, insert(9, insert(2, insert(45, insert(36, s)))))))

  println(t)
  println(find(3, t))
  println(find(45, t))
  println(size(t))
  println(sizeT(t))
  println(sum(t))
  println(sumT(t))

  println(inOrder(t))
  println(preOrder(t))
  println(postOrder(t))
  println(average(t))
}
