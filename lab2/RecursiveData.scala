package example.lab2


/** Напишите свои решения в виде функций. */
object RecursiveData extends App{

  // a) Реализуйте функцию, определяющую является ли пустым `List[Int]`.



  // используйте функцию из пункта (a) здесь, не изменяйте сигнатуру
  def testListIntEmpty(list: List[Int]): Boolean = list match {
    case Nil => true
    case _ => false
  }

  // b) Реализуйте функцию, которая получает head `List[Int]`или возвращает -1 в случае если он пустой.



  // используйте функцию из пункта (a) здесь, не изменяйте сигнатуру
  def testListIntHead(list: List[Int]): Int = list match {
    case Nil => -1
    case _ => list.head
  }
  println("1: " + testListIntEmpty(List()))
  println("2: " + testListIntHead(List(999,3,1,22)))
  // c) Можно ли изменить `List[A]` так чтобы гарантировать что он не является пустым?
  def ChangeEmpty[A](n: A, list: List[A]) : List[A] = list match {
    case Nil          => n :: list
    case _            => list.map((i: A) => i)
  }
  println("3: " + ChangeEmpty(3,List()))
  /* d) Реализуйте универсальное дерево (Tree) которое хранит значения в виде листьев и состоит из:
   *      node - левое и правое дерево (Tree)
   *      leaf - переменная типа A
   */
  sealed trait Tree[A]{
    def pront(): String = {this.toString}
  }

  case class Leaf[A](n: A) extends Tree[A]
  case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A]

  val tree = Node(
                  Node(
                        Leaf(1),
                                  Node(
                                        Leaf(2),
                                        Leaf(3))),
                  Leaf(4))

  println(tree.pront())

}
