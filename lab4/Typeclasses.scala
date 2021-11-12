package example.lab4

object Typeclasses extends App{

  // a) Определите тайп-класс Reversable, который представляет в обратном порядке значения.

  trait Reversable[T]{
    def Reverse(a: T): T
  }
  // b) Реализуйте функцию Reverse для String.
  object Reversable {
    implicit object ReversableString extends Reversable[String] {
      def Reverse(s: String): String = (for(i <- s.length - 1 to 0 by -1) yield s(i)).mkString
    }

  }

  // примените тайп-класс-решение из пункта (a) здесь
  def testReversableString(str: String)(implicit en: Reversable[String]): String = en.Reverse(str)
  println("Reverse: "+testReversableString("abc"))

  // c) Определите тайп-класс Smash таким образом чтобы в нем была функция smash, которая выполняет операцию со значениями одного типа.

  trait Smash[T]{
    def smash(a: T, b: T): T
  }
  // d) Реализуйте  функции Smash для типа Int и Double.
  //    Используйте сложение для типа Int у умножение для типа Double.
  object Smash {
    implicit object SmashDouble extends Smash[Double] {
      def smash(x: Double, y: Double): Double = x * y
    }
    implicit object SmashInt extends Smash[Int] {
      def smash(x: Int, y: Int): Int = x + y
    }
    implicit object SmashString extends Smash[String] {
      def smash(x: String, y: String): String = x + y
    }

  }

  // примените тайп-класс-решение из пункта (d) здесь
  def testSmashInt(a: Int, b: Int)(implicit en: Smash[Int]): Int = en.smash(a,b)

  // примените тайп-класс-решение из пункта (d) здесь
  def testSmashDouble(a: Double, b: Double)(implicit en: Smash[Double]): Double = en.smash(a,b)

  println("SmashInt: "+testSmashInt(3,7))
  println("SmashDouble: "+testSmashDouble(3,7))

  // e) Реализуйте функцию Smash для типа String. Необходимо выполнить конкатенацию строк, которые будут получены в качестве параметра.
  // примените тайп-класс-решение из пункта (d) здесь
  def testSmashString(a: String, b: String)(implicit en: Smash[String]): String = en.smash(a,b)
  println("SmashString: "+testSmashString("biba-","boba"))
}

// Реализуйте тестовые функции с выводом на экран проверки разработанных функций.