package example.lab2

import scala.annotation.tailrec

/** Реализуйте функции для решения следующих задач.
  * Примечание: Попытайтесь сделать все функции с хвостовой рекурсией, используйте аннотацию для подстверждения.
  * рекурсия будет хвостовой если:
  *   1. рекурсия реализуется в одном направлении
  *   2. вызов рекурсивной функции будет последней операцией перед возвратом
  */
object RecursiveFunc extends App{

  def length[A](as: List[A]): Int = {
    @tailrec
    def loop(rem: List[A], agg: Int): Int = rem match {
      case Nil         => agg
      case _           => loop(rem.tail, agg + 1)
    }

    loop(as, 0)
  }

  /* a) Напишите функцию которая записывает в обратном порядке список:
   *        def reverse[A](list: List[A]): List[A]
   */
  def reverse[A](list: List[A]): List[A] = {
    @tailrec
    def loop(lst: List[A], res: List[A]): List[A] = lst match {
      case Nil          => res
      case head :: tail => loop(tail, head +: res)
    }
    loop(list, Nil)
  }


  // используйте функцию из пункта (a) здесь, не изменяйте сигнатуру
  def testReverse[A](list: List[A]): List[A] = reverse(list)
  println("Reverse: " + testReverse(List(1,2,3,4,5)));
  /* b) Напишите функцию, которая применяет функцию к каждому значению списка:
   *        def map[A, B](list: List[A])(f: A => B): List[B]
   */
  def map[A, B](list: List[A])(f: A => B): List[B] = {
    @tailrec
    def loop(rem: List[A], result: List[B])(f: A => B): List[B] = rem match {
      case head :: tail => loop(tail, result :+ f(head))(f)
      case Nil          => result
    }
    loop(list, Nil)(f)
  }


  // используйте функцию из пункта  (b) здесь, не изменяйте сигнатуру
  def testMap[A, B](list: List[A], f: A => B): List[B] = map(list)(f)
  println("testMap: " + testMap(List(1, 2, 3, 4, 5), (f:Int) => f * f));
  /* c) Напишите функцию, которая присоединяет один список к другому:
   *        def append[A](l: List[A], r: List[A]): List[A]
   */

  def append[A](list: List[A], ap: List[A]): List[A] = {
    @tailrec
    def loop(rem: List[A], result: List[A]): List[A] = rem match {
      case head :: tail => loop(tail, result :+ head)
      case Nil          => result
    }
    loop(ap, list)
  }

  // используйте функцию из пункта  (c) здесь, не изменяйте сигнатуру
  def testAppend[A](l: List[A], r: List[A]): List[A] = append(l, r)
  println(testAppend(List(1, 2, 3), List(4, 5, 6)))
  /* d) Напишите функцию, которая применяет функцию к каждому значению списка:
   *        def flatMap[A, B](list: List[A])(f: A => List[B]): List[B]
   * 
   *    она получает функцию, которая создает новый List[B] для каждого элемента типа A в 
   *    списке. Поэтому вы создаете List[List[B]]. 
   */
  def flatmap[A, B](list: List[A])(f: A => List[B]): List[B] = {
    @tailrec
    def loop1(list: List[A], temp: List[List[B]]): List[List[B]] = list match {
      case Nil => temp
      case _ => loop1(list.tail, temp :+ f(list.head))
    }
    @tailrec
    def loop2(list: List[List[B]], temp: List[B]): List[B] = list match {
      case Nil => temp
      case _ => loop2(list.tail, append(temp, list.head))
    }
    loop2(loop1(list, List.empty[List[B]]), List.empty[B])
  }



  // используйте функцию из пункта  (d) здесь, не изменяйте сигнатуру
  def testFlatMap[A, B](list: List[A], f: A => List[B]): List[B] = flatmap(list)(f)
  println("testFlatMap: " + testFlatMap(List(1,2,3,4,5), (f:Int) => (f.toString + "O").toList))
  println("originalFlatMap: " + List(1,2,3,4,5).flatMap((f:Int) => (f.toString + "O").toList))
  /* e) Вопрос: Возможно ли написать функцию с хвостовой рекурсией для `Tree`s? Если нет, почему? */
  /* Ответ: Нельзя из-за структуры дерева*/
}
