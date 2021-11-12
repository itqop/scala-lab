package example.lab3
import example.lab3.Maps.User

import scala.annotation.tailrec

/** Напишите свои решения в тестовых функциях.
  * 
  * Seq(1, 2) match {
  *   case head +: tail => ???
  *   case Nil          => ???
  *   case s            => ???
  * }
  * 
  * https://www.scala-lang.org/api/2.12.0/scala/collection/Seq.html
  */
// Примечание: напишите функции с хвостовой рекурсией

object Sequence extends App{

  /* a) Найдите последний элемент Seq.
   *    
   */
  def testLastElement[A](seq: Seq[A]): Option[A] = {
    @tailrec
    def loop(seq: Seq[A]): Option[A] =
      seq.length match {
        case 1 => Some(seq.head)
        case _ => loop(seq.drop(1))
      }
    loop(seq)
}
  println("a) " + testLastElement(Seq(User("Leo", 19), User("NeLeo", 12), User("TopGamerInTheWorld", 21), User("Ded", 99))))
  /* b) Объедините две Seqs (то есть Seq(1, 2) и Seq(3, 4) образуют Seq((1, 3), (2, 4))) - если Seq длиннее игнорируйте оставшиеся элементы.
   *    
   */
  def testZip[A](a: Seq[A], b: Seq[A]): Seq[(A, A)] = {
    @tailrec
    def loop(res: Seq[(A, A)], a: Seq[A], b: Seq[A]): Seq[(A, A)] =
    {
      if (a.nonEmpty && b.nonEmpty)
      {
        loop(res :+ (a.head, b.head), a.drop(1), b.drop(1))
      }
      else res
    }
    loop(Seq.empty, a, b)
  }
  println("b) " + testZip(Seq( User("TopGamerInTheWorld", 21), User("Ded", 99)), Seq(User("Leo", 19), User("NeLeo", 12)) ))
  /* c) Проверьте, выполняется ли условие для всех элементов в Seq.
   *    
   */
  def testForAll[A](seq: Seq[A])(cond: A => Boolean): Boolean = seq.forall(cond)

  /* d) Проверьте, является ли Seq палиндромом
   *    
   */
  def testPalindrom[A](seq: Seq[A]): Boolean = {
    @tailrec
    def loop(seq: Seq[A]): Boolean = {
      seq match {
        case Nil => true
        case head :: tail => if (head == tail.last) (loop(seq.drop(1).dropRight(1))) else false
        case _ => false
      }}

      loop(seq)
  }
  println("d) " + testPalindrom(Seq(1,2,3,4,5)))
  println("d) " + testPalindrom(Seq(1,2,3,3,2,1)))
  /* e) Реализуйте flatMap используя foldLeft.
   *    
   */

  def testFlatMap[A, B](seq: Seq[A])(f: A => Seq[B]): Seq[B] =  {
    def flatMamp(out: Seq[B], in: A): Seq[B] = {
    @tailrec
    def loop(output: Seq[B], input: Seq[B]): Seq[B] = input match {
      case Nil => output
      case head::tail => loop(output :+ head, tail)
    }
    loop(out, f(in))
  }
  seq.foldLeft(Seq.empty[B])(flatMamp)
}

  println("e) " + testFlatMap(Seq(1,2,3,4,5))(i => Seq(i, 2*i)))
  println("test e) "+ Seq(1,2,3,4,5).flatMap(i => Seq(i, 2*i)))

}
