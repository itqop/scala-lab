package lab1



/** Напишите отдельные функции, решающие поставленную задачу.
 *
 * Синтаксис:
 * // метод
 * def myFunction(param0: Int, param1: String): Double = // тело
 *
 * // значение
 * val myFunction: (Int, String) => Double (param0, param1) => // тело
 */
object Functions extends App {

  /* a) Напишите функцию, которая рассчитывает площадь окружности
   *    r^2 * Math.PI
   */
  def calculateArea(radius: Double): Double = math.Pi * math.pow(radius, 2.0)


  // примените вашу функцию из пункта (a) здесь, не изменяя сигнатуру
  def testCircle(r: Double): Double = calculateArea(r)


  /* b) Напишите карированную функцию которая рассчитывает площадь прямоугольника a * b.
   */
  val multiply: (Double, Double) => Double = _ * _

  val areaRectangleCur: Double => Double => Double = (a: Double) => (b: Double) => a * b // multiple.curied

  // примените вашу функцию из пукта (b) здесь, не изменяя сигнатуру
  def testRectangleCurried(a: Double, b: Double): Double = areaRectangleCur(a)(b)

  println(testRectangleCurried(3, 5))

  // c) Напишите не карированную функцию для расчета площади прямоугольника.

  val areaRectangle: (Double, Double) => Double = multiply(_, _)

  // примените вашу функцию из пункта (c) здесь, не изменяя сигнатуру
  def testRectangleUc(a: Double, b: Double): Double = areaRectangle(a, b)

  println(testRectangleUc(3, 5))
}
