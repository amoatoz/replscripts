class Wrapper[A] private (value: A) {
  def map[B](f:A => B): Wrapper[B] = new Wrapper(f(value))
  def flatmap[B](f:A => Wrapper[B]): Wrapper[B] = f(value)
  override def toString: String = value.toString
}

object Wrapper {
  def apply[A](value: A): Wrapper[A] = new Wrapper[A](value)
}

Wrapper(1)


