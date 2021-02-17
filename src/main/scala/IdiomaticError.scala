import cats.kernel.Semigroup

import scala.util.{Either, Try}



object IdiomaticError {


  //Like java
  //1
  val magicCar = try{
    //dang code
    "scala".charAt(20)

  } catch {
    case e: NullPointerException => 'z'
    case e: RuntimeException => 's'
  } finally {
    //clean up resources

  }

  //2
  val aTry = Try(2)

  val sFail: Try[Char] = Try {
    "scala".charAt(20)
  }


  val modifiedtry = aTry.map(_ + 2)

  val chainedComputation = for {
    x <- aTry
    y <- modifiedtry
  } yield  x + y

  //3 LEFT and Right
  val aRight: Either[String, Int] = Right(22) // success
  val amodifiedRight  = aRight.map(_ + 1)

  aRight.fold(fa => fa, c => c )


  //4
  import cats.data.Validated




  val aValidValue: Validated[String, Int] = Validated.valid(42) //Right
  val anInvalidValue: Validated[String, Int] = Validated.invalid("Not valid") //Invalid

  val aTest: Validated[String, Int] = Validated.cond(42 > 39, 23, "SOmething is not right")

   def validatePositive(n: Int): Validated[List[String], Int] = Validated.cond(n>0, n, List("Number must be positive"))

  def validateSmall(n: Int): Validated[List[String], Int] = Validated.cond(n< 100, n, List("Number must be small"))

  def validateEven(n: Int): Validated[List[String], Int] = Validated.cond(n %2 ==0, n, List("Number must be Even"))


  import cats.instances.list._
  implicit val combineIntMax: Semigroup[Int]  = Semigroup.instance[Int](Math.max)

  def validate(n: Int): Validated[List[String], Int] =
    validatePositive(n)
      .combine(validateSmall(n))
      .combine(validateEven(n))















  def main(args: Array[String]): Unit = {



  }

}
