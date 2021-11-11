package example.lab2

import scala.math.BigInt.int2bigInt


/** Option представляет собой контейнер, который хранит какое-то значение 
  * или не хранит ничего совсем, указывает, вернула ли операция результат или нет. 
  * Это часто используется при поиске значений или когда операции могут потерпеть неудачу, 
  * и вам не важна причина.
 *
 * Комбинаторы называются так потому, что они созданы, чтобы объединять результаты.
  * Результат одной функции часто используется в качестве входных данных для другой.
 *
 * Наиболее распространенным способом, является использование их со стандартными структурами данных.
  * Функциональные комбинаторы `map` и` flatMap` являются контекстно-зависимыми. 
  * map - применяет функцию к каждому элементу из списка, возвращается список с тем же числом элементов.
  * flatMap берет функцию, которая работает с вложенными списками и объединяет результаты.
 *
 *sealed trait Option[A] {
 *
 *def map[B](f: A => B): Option[B]
  *def flatMap[B](f: A => Option[B]): Option[B]
*}
*case class Some[A](a: A) extends Option[A] {
 *
 *def map[B](f: A => B): Option[B] = Some(f(a))
  *def flatMap[B](f: A => Option[B]): Option[B] = f(a)
*}
*case class None[A]()     extends Option[A] {
 *
 *def map[B](f: A => B): Option[B] = None()
  *def flatMap[B](f: A => Option[B]): Option[B] = None()
*}
*/
/** Напишите ваши решения в тестовых функциях.  */
object Compositions extends App{

  // a) Используйте данные функции. Вы можете реализовать свое решение прямо в тестовой функции.
  // Нельзя менять сигнатуры 

  def testCompose[A, B, C, D](f: A => B)
                             (g: B => C)
                             (h: C => D): A => D = h compose g compose f
  println("Compose: " + testCompose ((n:Int) => List.tabulate(n)(_ + 1))(n => n.map((i: Int) => "leo " + i.toString))(n => n.head + " - " + n.takeRight(1).head)((7)))


  // b) Напишите функции с использованием `map` и `flatMap`. Вы можете реализовать свое решение прямо в тестовой функции.
  // Нельзя менять сигнатуры 

  def testMapFlatMap[A, B, C, D](f: A => Option[B])
                                (g: B => Option[C])
                                (h: C => D): Option[A] => Option[D] = _.flatMap(f).flatMap(g).map(h)

  println("MapFlatMap: " +
    testMapFlatMap((n:Int) => List.tabulate(n / 2)(_.pow(2)).find((i:BigInt) => i == n)) // если идеальный квадрат
    (n => if (n > 24) Some(n) else None)
    (n => n.signum )(Some(25)))
  // c) Напишите функцию используя for. Вы можете реализовать свое решение прямо в тестовой функции.
  // Нельзя менять сигнатуры 

  def testForComprehension[A, B, C, D](f: A => Option[B])
                                      (g: B => Option[C])
                                      (h: C => D): Option[A] => Option[D] = for { first <- _
                                                                                  second <- f(first)
                                                                                  third <- g(second) } yield h(third)
  println("ForComprehension: " +
    testForComprehension((n:Int) => List.tabulate(n / 2)(_.pow(2)).find((i:BigInt) => i == n)) // если идеальный квадрат
    (n => if (n > 24) Some(n) else None)
    (n => n.signum )(Some(65)))
}
