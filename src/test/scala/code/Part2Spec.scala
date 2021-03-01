package code

import org.scalatest._

class Part2Spec extends WordSpec with Matchers {
  "part 2" should {
    "return 345882865 as a valid input" in {
      val accountNumber = "345882865"
      Main.isValid(accountNumber) shouldBe true
    }
    "return 000000000 as a valid input" in {
      val accountNumber = "000000000"
      Main.isValid(accountNumber) shouldBe true
    }
    "return 000000001 as an invalid input" in {
      val accountNumber = "000000001"
      Main.isValid(accountNumber) shouldBe false
    }
  }
}
