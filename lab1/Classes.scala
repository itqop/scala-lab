package lab1



/*
 * 
 a) Создать класс Animal, который имеет следующие поля:
 *      - name: String (название)
 *      - species: String (вид)
 *      - food: String
 * 
 *    Синтаксис: class MyClass(val publicField: Int, privateField: String) {
 *              // остальные поля и методы
 *            }
 *
 * 
 * b) Создайте объект-компаньон для класса Animal и добавьте следующие сущности как поля:
 *      - cat, mammal, meat
 *      - parrot, bird, vegetables
 *      - goldfish, fish, plants
 * 
 *    Синтаксис: object MyClass {
 *              // статические поля и методы
 *            }
 * 
 * c) Добавьте следующие метод в Animals:
 *      def eats(food: String): Boolean
 *    
 *     который проверяет ест ли животное определенную пищу
 * 
 * d) Переопределите ваш класс Animal как трейт и создайте объекты класса-образца для Mammals, Birds и Fishs.
 *    Вам все еще нужно поле `species`?
 * 
 * e) Добавьте следующие функции в объект-компаньон Animal:
 *      def knownAnimal(name: String): Boolean  // true если это имя одного из трех животных из (b)
 *      def apply(name: String): Option[Animal] // возвращает одно из трех животных в соответствии с именем (Some) или ничего (None), см. ниже
 * 
 * f) Создайте трейт Food со следующими классами-образцами:
 *      - Meat
 *      - Vegetables
 *      - Plants
 *   и добавьте это в определение Animal. Так же добавьте объект-компаньон с методом apply():
 *      def apply(food: String): Option[Food]
 */

/*
class Animal(name: String, species: String, food: String){
  def eats(food: String): Boolean = food.equals(this.food)
}

object Animal{
  val Cat = new Animal("cat", "mammal", "meat")
  val Parrot = new Animal("parrot","bird","vegetables")
  val goldFish = new Animal("goldfish","fish","plants")
}
*/

sealed trait Food{
  val name:String
}

case class Meat(name: String)       extends Food
case class Vegetables(name: String) extends Food
case class Plants(name: String)     extends Food

object Food{
  val Chicken: Meat = Meat("chicken")
  val Biff: Meat = Meat("biff")
  val Tomato: Vegetables = Vegetables("tomato")
  val Grass: Plants = Plants("grass")

  def apply(name: String):Option[Food] = name match {
    case Chicken.name => Some(Chicken)
    case Biff.name => Some(Biff)
    case Tomato.name => Some(Tomato)
    case Grass.name => Some(Grass)
    case _ => None
  }
}

sealed trait Animal extends Food{
  val name:String
  val food: Food
  def eats(food: String): Boolean = if (Food.apply(food).isDefined) Food.apply(food).get.equals(this.food) else false
}


case class Mammals(name: String, food: Food)  extends Animal
case class Birds(name: String, food: Food)    extends Animal
case class Fish(name: String, food: Food)     extends Animal

object Animal{


  val Cat: Mammals = Mammals("cat", Food.Biff)
  val Parrot: Birds = Birds("parrot", Food.Tomato)
  val goldFish: Fish = Fish("goldfish", Food.Grass)

  def knownAnimal(name: String): Boolean = List(Cat.name,  Parrot.name, goldFish.name).contains(name)

  def apply(name: String): Option[Animal] = name match{
    case Cat.name => Some(Cat)
    case Parrot.name => Some(Parrot)
    case goldFish.name => Some(goldFish)
    case _ => None
  }


}
/*
sealed trait Option[A] {
 
  def isEmpty: Boolean
}
case class Some[A](a: A) extends Option[A] {
  val isEmpty = false
}
case class None[A]()     extends Option[A] {
  val isEmpty = true
}

 */
object test extends App{
  val cat = Animal.Cat
  val parrot = Animal.Parrot
  val goldFish = Animal.goldFish
  val Crocodile = Mammals("Alligator", Food.Chicken)
  println(cat.eats("biff"))
  println(parrot.eats("chicken"))
  println(Crocodile.eats("grass"))
  println(Crocodile.eats("chicken"))
}
