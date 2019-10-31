package _Tutorial

import org.scalatest.FunSuite

class _10CubeCalculatorTest extends FunSuite {
  test("CubeCalculator.cube") {
    assert(_10CubeCalculator.cube(3) === 27)
  }
}