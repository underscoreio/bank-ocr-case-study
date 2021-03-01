package code

import org.scalatest._

class Part3Spec extends WordSpec with Matchers with Part3TestCases {
  "part 3" should {
    "returns the value when provided a valid test case" in {
      Main.validatedResponse(testCase3d.actual) shouldBe testCase3d.expected
    }
    "returns the value when provided a second valid test case" in {
      Main.validatedResponse(testCase3a.actual) shouldBe testCase3a.expected
    }
    "returns the value + ILL when provided a unparseable test case" in {
      Main.validatedResponse(testCase3b.actual) shouldBe testCase3b.expected
    }
    "returns the value + ILL when provided a second unparseable test case" in {
      Main.validatedResponse(testCase3c.actual) shouldBe testCase3c.expected
    }
    "returns the value when provided a invalid account number test case" in {
      Main.validatedResponse(testCase3e.actual) shouldBe testCase3e.expected
    }
  }
}

trait Part3TestCases {

  val testCase3d: TestCase =
    TestCase.create(
      """
      # _  _  _  _  _  _  _  _  _
      #| || || || || || || || || |
      #|_||_||_||_||_||_||_||_||_|
      """,
      "000000000"
    )

  val testCase3a: TestCase =
    TestCase.create(
      """
      # _  _  _  _  _  _  _  _
      #| || || || || || || ||_   |
      #|_||_||_||_||_||_||_| _|  |
      """,
      "000000051"
    )

  val testCase3b: TestCase =
    TestCase.create(
      """
      #    _  _  _  _  _  _     _
      #|_||_|| || ||_   |  |  | _
      #  | _||_||_||_|  |  |  | _|
      """,
      "49006771? ILL"
    )

  val testCase3c: TestCase =
    TestCase.create(
      """
      #    _  _     _  _  _  _  _
      #  | _| _||_| _ |_   ||_||_|
      #  ||_  _|  | _||_|  ||_| _
      """,
      "1234?678? ILL"
    )

  val testCase3e: TestCase =
    TestCase.create(
      """
      # _  _  _  _  _  _  _  _
      #| || || || || || || || |  |
      #|_||_||_||_||_||_||_||_|  |
      """,
      "000000001 ERR"
    )
}
