package example.lab3

import scala.util.{Failure, Success, Try}

/** Реализуйте следующие функции.
  * 
  * List(1, 2) match {
  *   case head :: tail => ???
  *   case Nil          => ???
  *   case l            => ???
  * }
  * 
  * Option(1) match {
  *   case Some(a) => ???
  *   case None    => ???
  * }
  * 
  * Either.cond(true, 1, "right") match {
  *   case Left(i)  => ???
  *   case Right(s) => ???
  * }
  * 
  * Try(impureExpression()) match {
  *   case Success(a)     => ???
  *   case Failure(error) => ???
  * }
  * 
  * Try(impureExpression()).toEither
  * 
  */
object Adts extends App{

  // a) Дан List[Int], верните элемент с индексом n

  // примените функцию из пункта (a) здесь, не изменяйте сигнатуру 
  def testGetNth(list: List[Int], n: Int): Option[Int] = list match {
    case Nil => None
    case head :: tail => if (tail.lengthCompare(n) >= 0) (Some(list(n))) else (None)
  }
  println("a) "+ testGetNth(List(0,1,2,3,4,5,6),3))
  println("a) "+ testGetNth(List(),333))
  println("a) "+ testGetNth(List(1,2),333))
  // b) Напишите функцию, увеличивающую число в два раза.
  


  // примените функцию из пункта (b) здесь, не изменяйте сигнатуру
  def testDouble(n: Option[Int]): Option[Int] = n match {
    case Some(a) => Some(a*2)
    case None => None
  }
  println("b) " + testDouble(Some(5)))
  println("b) " + testDouble(None))
  // c) Напишите функцию, проверяющую является ли число типа Int четным. Если так, верните Right. В противном случае, верните Left("Нечетное число.").



  // примените функцию из пункта (c) здесь, не изменяйте сигнатуру
  def testIsEven(n: Int): Either[String, Int] =  Either.cond(n % 2 == 0, n / 2, "Нечетное число.")
  println("c) "+testIsEven(8))
  println("c) "+testIsEven(7))

  // d) Напишите функцию, реализующую безопасное деление целых чисел. Верните Right с результатом или Left("Вы не можете делить на ноль.").



  // примените функцию из пункта (d) здесь, не изменяйте сигнатуру
  def testSafeDivide(a: Int, b: Int): Either[String, Int] = Either.cond(b != 0, a / b, "Вы не можете делить на ноль.")
  println("d) " + testSafeDivide(8,2))
  println("d) " + testSafeDivide(32,0))
  // e) Обработайте исключения функции с побочным эффектом вернув 0.



  // примените функцию из пункта (e) здесь, не изменяйте сигнатуру
  def testGoodOldJava(impure: String => Int, str: String): Try[Int] = Try(impure(str)) match {
    case Success(a) => Success(a)
    case Failure(error) => Failure(error)
  }
  println("e) " + testGoodOldJava((str: String) => str.toInt, "9999"))
  println("e) "+ testGoodOldJava((str: String) => str(9).toInt, "9999"))
}
