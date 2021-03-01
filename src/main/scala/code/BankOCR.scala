package code

import cats.implicits._

import scala.util.{Failure, Success, Try}

object Main {
  def parseInput(input: String): Try[String] = {
    val rows = input.split("\\n").toList.map { line =>
      line.grouped(3).toList
    }
    val digitGroups = rows match {
      case l1::l2::l3::_ => (l1,l2,l3).zipped.toList
    }
    digitGroups.traverse { group =>
      parseDigit(s"${group._1}\n${group._2}\n${group._3}")
    }
  }

  def parseDigit(input:String): Try[String] =
    input.split("\\n").toList match {
      case "   "::"  |"::"  |"::_ => Success("1")
      case " _ "::" _|"::"|_ "::_ => Success("2")
      case " _ "::" _|"::" _|"::_ => Success("3")
      case "   "::"|_|"::"  |"::_ => Success("4")
      case " _ "::"|_ "::" _|"::_ => Success("5")
      case " _ "::"|_ "::"|_|"::_ => Success("6")
      case " _ "::"  |"::"  |"::_ => Success("7")
      case " _ "::"|_|"::"|_|"::_ => Success("8")
      case " _ "::"|_|"::" _|"::_ => Success("9")
      case _ => Failure(new IllegalArgumentException("oh no!"))
    }
}