import scala.util.Either

val aRight: Either[String, Int] = Right(22) // success
val amodifiedRight  = aRight.map(_ + 1)

val aWrong: Either[String, Int] = Left("Something is wrong")

aWrong.fold(fa => fa, c => c )

aWrong match {
  case Left(ex) => println(ex)
  case Right(intValue) => intValue
}