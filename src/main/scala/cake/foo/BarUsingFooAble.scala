package cake.foo

class BarUsingFooAble {

  this:FooAble with BazAble => def bar() = s"bar calls foo: ${foo()} and ${baz()}"
}

trait MyFooAble extends FooAble {
  def foo = "foo impl"
}

trait BazAble {
  def baz() = "baz impl"
}

object Main {
  def main(args: Array[String]){
    val barWithFoo = new BarUsingFooAble with MyFooAble with BazAble
    println(barWithFoo.bar())
  }
}