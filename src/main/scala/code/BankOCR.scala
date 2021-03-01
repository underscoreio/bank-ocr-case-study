package code

import cats.implicits._

import scala.util.{Failure, Success, Try}

object Main {
  def parseInput(input: String): Try[String] = {
    val rows = input.split("\\n").toList.map { line =>
      line.grouped(3).toList
    }
    val digitGroups = rows match {
      case l1 :: l2 :: l3 :: _ => (l1, l2, l3).zipped.toList
    }
    digitGroups.traverse { group =>
      parseDigit(s"${group._1}\n${group._2}\n${group._3}")
    }.map(_.mkString)

  }

  def parseDigit(input: String): Try[String] =
    input.split("\\n").toList match {
      case "   " :: "  |" :: "  |" :: _ => Success("1")
      case " _ " :: " _|" :: "|_ " :: _ => Success("2")
      case " _ " :: " _|" :: " _|" :: _ => Success("3")
      case "   " :: "|_|" :: "  |" :: _ => Success("4")
      case " _ " :: "|_ " :: " _|" :: _ => Success("5")
      case " _ " :: "|_ " :: "|_|" :: _ => Success("6")
      case " _ " :: "  |" :: "  |" :: _ => Success("7")
      case " _ " :: "|_|" :: "|_|" :: _ => Success("8")
      case " _ " :: "|_|" :: " _|" :: _ => Success("9")
      case " _ " :: "| |" :: "|_|" :: _ => Success("0")
      case _                            => Failure(new IllegalArgumentException("Following Strong was Invalid >:( :" + "\n" + input))
    }

  // account number:  3  4  5  8  8  2  8  6  5
  // position names:  d9 d8 d7 d6 d5 d4 d3 d2 d1
  // (d1+2*d2+3*d3 +..+9*d9) mod 11 = 0
  def isValid(input: String): Boolean = {
    val inputAsInt = input.split("").toList.traverse(letter => Try(letter.toInt))
    inputAsInt match {
      case Failure(_)     => false
      case Success(value) => value.reverse.zipWithIndex.map {
        case (number, index) => number * (index + 1)
      }.sum % 11 == 0
    }
  }
}