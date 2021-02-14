class Wrapper[A] private (value: A) {
  def map[B](f:A => B): Wrapper[B] = new Wrapper(f(value))
  def flatmap[B](f:A => Wrapper[B]): Wrapper[B] = f(value)
  override def toString: String = value.toString
}

object Wrapper {
  def apply[A](value: A): Wrapper[A] = new Wrapper[A](value)
}

Wrapper(1)

case class Debuggable(value: Int, message: String) {
  def map(f:Int => Int)  = Debuggable(f(value), message)
  def flatMap(f:Int => Debuggable) = {
    val newDebuggable = f(value)
    Debuggable(newDebuggable.value, message + "\n" + newDebuggable.message)
  }
}

def f(a:Int) = Debuggable(a*2, s"Working with function f ${a*2}")
def g(a:Int) = Debuggable(a*3, s"Working with function g  ${a*3}")
def h(a:Int) = Debuggable(a*4, s"Working with function h ${a*4}")

val finalResult = for {
  fResult <- f(100)
  gResult <- g(fResult)
  hResult <- h(gResult)
} yield hResult

println(s"value: ${finalResult.value}")
println(s"message: ${finalResult.message}")