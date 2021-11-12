package example.lab3

/** Напишите вашу реализацию в тестовые функции.
  * 
  * https://docs.scala-lang.org/overviews/collections/maps.html
  */
object Maps extends App{

  case class User(name: String, age: Int)

  /* a) В данной Seq[User] сгруппируйте пользователей по имени (`groupBy`) и вычислите средний возраст: `name -> averageAge`
   *    Вы можете реализовать ваше решение в теле тестовой функции. Не изменяйте сигнатуру.
   */

  def testGroupUsers(users: Seq[User]): Map[String, Int] = users.groupBy(_.name).map(x => (x._1 , users.map(_.age).sum / users.length))

  println("a) " + testGroupUsers(Seq(User("Leo", 19), User("NeLeo", 12), User("TopGamerInTheWorld", 21), User("Ded", 99))))
  /* b) Дана `Map[String, User]` состоящая из имен пользователей `User`, сколько имен пользователей, содержащихся в Map, содержат подстроку "Adam"?
   *    Вы можете реализовать ваше решение в теле тестовой функции. Не изменяйте сигнатуру.
   */

  def testNumberFrodos(map: Map[String, User]): Int = map.map(x =>  if (x._2.name.contains("Adam")) (1) else (0)).sum

  println("b) " + testNumberFrodos(Map(("HR", User("Maria", 32)),
    ("QA", User("UserAdam", 14)), ("Back", User("Adam", 92)), ("Front", User("Pole", 84)), ("AI", User("Leo", 19)),
    ("DevOps", User("Adam", 42)))))

  /* c) Удалите всех пользователей возраст которых менее 35 лет.
   *    Вы можете реализовать ваше решение в теле тестовой функции. Не изменяйте сигнатуру.
   */
  def testUnderaged(map: Map[String, User]): Map[String, User] = map.filter(x => x._2.age >= 35)
  println("c) " + testUnderaged(Map(("HR", User("Maria", 32)),
    ("QA", User("UserAdam", 14)), ("Back", User("Adam", 92)), ("Front", User("Pole", 84)), ("AI", User("Leo", 19)),
    ("DevOps", User("Adam", 42)))))
}
