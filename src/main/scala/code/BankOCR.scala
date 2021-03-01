package code

import cats.implicits._

import scala.util.{Failure, Success, Try}

object Main {

  def parseInput(input: String): String = {
    val rows = input.split("\\n").toList.map { line =>
      line.grouped(3).toList
    }
    val result = rows match {
      case l1 :: l2 :: l3 :: _ => {
        val digitGroups = (l1, l2, l3).zipped.toList
        digitGroups.map { group =>
          parseDigit(s"${group._1}\n${group._2}\n${group._3}")
        }.mkString
      }
      case _ => "?????????"
    }
    if(result.contains("?")) {
      result + " ILL"
    }
    else {
      result
    }
  }

  def parseDigit(input: String): String =
    input.split("\\n").toList match {
      case "   " :: "  |" :: "  |" :: _ => "1"
      case " _ " :: " _|" :: "|_ " :: _ => "2"
      case " _ " :: " _|" :: " _|" :: _ => "3"
      case "   " :: "|_|" :: "  |" :: _ => "4"
      case " _ " :: "|_ " :: " _|" :: _ => "5"
      case " _ " :: "|_ " :: "|_|" :: _ => "6"
      case " _ " :: "  |" :: "  |" :: _ => "7"
      case " _ " :: "|_|" :: "|_|" :: _ => "8"
      case " _ " :: "|_|" :: " _|" :: _ => "9"
      case " _ " :: "| |" :: "|_|" :: _ => "0"
      case _                            => "?"
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