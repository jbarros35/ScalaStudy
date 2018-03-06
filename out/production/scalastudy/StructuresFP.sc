val f1 = Seq(1,2,3,4)
val f2 = f1.foldLeft(0){_+_}
f1.foldLeft(0){(x,y)=>x + y}
