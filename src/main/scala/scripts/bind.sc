class Wrapper[A] private (value: A) {
  def map[B](f:A => B): Wrapper[B] = new Wrapper(f(value))
  def flatmap[B](f:A => Wrapper[B]): Wrapper[B] = f(value)
  override def toString: String = value.toString
}

object Wrapper {
  def apply[A](value: A): Wrapper[A] = new Wrapper[A](value)
}

Wrapper(1)

case class Debuggable[A](value: A, log: List[String]) {
  def map[B](f:A => B)  = Debuggable(f(value), log)
  def flatMap[B](f:A => Debuggable[B]) = {
    val newDebuggable = f(value)
    Debuggable(newDebuggable.value, log  ::: newDebuggable.log)
  }
}

def f(a:Int) = Debuggable(a*2, List(s"Working with function f ${a*2}"))
def g(a:Int) = Debuggable(a*3, List(s"Working with function g  ${a*3}"))
def h(a:Int) = Debuggable(a*4, List(s"Working with function h ${a*4}"))

val finalResult = for {
  fResult <- f(100)
  gResult <- g(fResult)
  hResult <- h(gResult)
} yield hResult

println(s"value: ${finalResult.value}")
println(s"message: ${finalResult.log}")