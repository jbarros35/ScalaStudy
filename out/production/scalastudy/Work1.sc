var x = 1
var y = List(1,2,3)
val z1 = for(e<-y) yield e * 2
val z2 = y.map(_ * 2)
val z3 = y.filter(_ > 1)

case class Player(name:String, position:String)

val heat = Seq(
  Player("Mario Chamlers", "PG"),
  Player("Dwayne Wade", "SG"),
  Player("LeBron Jame", "SF"),
  Player("Udonis Haslem", "PF"),
  Player("Chris Bosh", "C")
)

val guards = for {
  player <- heat
  if player.position.endsWith("G")
} yield player.name

val forwards = heat.withFilter(_.position endsWith("F")).map(_.name)



val res = for {
  name <- heat
  position <- heat
} yield (name.name, position.position)

res(0)._1
res(0)._2

val res2 = for {
  player <- heat
  if player.name.contains("M") // filter M
} yield player


val pairs = Seq(2, 4, 6, 8, 10)
val odds = Seq(1,3,5,7,9)
/*
val merge = for {
  pair <- pairs
  odd<- odds
} yield (pair , odd)
*/

val pairsDouble = pairs.map(2 * _)
val one = Some(1)
val two = one.map(1 + _)

val optString:Option[String] = Some("1")
def strToInt(str:String):Option[Int] = Some(Integer.parseInt(str))

val opt = optString.getOrElse()
var opt1 = strToInt("0")

