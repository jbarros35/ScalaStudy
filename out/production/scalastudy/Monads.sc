//bind
trait M[A] {
  def flatMap[B](f: A => M[B]) : M[B]
}

// def unit[A](x: A): M[A]

//val wrapBook = unit[String]("my book")


val f1 = (i: Int) => List(i - 1, i, i + 1)
val f2 = (i :Int) => List(i,i-1)

val list = List(5, 6, 7)
println(list.flatMap(f1))
println(list.flatMap(f2))
println(list.map(f1))

// bind part
trait User {
  val child : Option[User]
}

case class UserClass (name : String, child :Option[User]) extends User {
}

object UserService {
  def loadUser(name : String) : Option[User] = {
    if (name.equals("Mike")) {
      Some(UserClass("Mike", Some(UserClass("Mike Son#1", None))))
    } else {
      None
    }
  }
}

// unit
val getChild = (user: User) => user.child

val mike = UserService.loadUser("Mike")
val mikeSon = mike.flatMap(getChild)
val none = UserService.loadUser("Mike")
  .flatMap(getChild)
  .flatMap(getChild)

val printList = (x:Option[Int]) => {
  println(x.getOrElse(0))
  x.getOrElse(0)
}
val list2 = List(Some(1), Some(2), None)
list2.map(printList)

def multiply(i:Int, b:Int):Int = {
  i * b
}

val three:Option[Int] = Option(3)
val four = Option(4)
val var2 = three map (i => four map (i * _))
val var3 = three flatMap (i => four map (multiply(i, _)))

val var4 = for {
  three <- three
  four <- four
} yield (three * four)