package org.lmd.storeroom

import org.ldivad.storeroom.{Direction, Mower}
import org.scalatest.FunSuite

/**
  * Created by loicmdivad on 11/08/2016.
  */

class AutomaticTest extends FunSuite {

  test("Should pass anyway"){
    assert(1 !== 2)
  }

  test("Should turn to the right"){
    val m1 = new Mower(1, 1, Direction.N)
    val m2 = new Mower(1, 1, Direction.W)

    assert(m1.move('D')._3 equals Direction.E)
    assert(m2.move('D')._3 equals Direction.N)
  }

  test("Should turn to the left"){
    val m3 = new Mower(1, 1, "S")
    val m4 = new Mower(1, 1, "N")

    assert(m3.move('G')._3 equals Direction.E)
    assert(m4.move('G')._3 equals Direction.W)
  }

  test("Should step forward, backward & on the side"){
    val m5 = new Mower(4, 1, "N")
    val m6 = new Mower(2, 8, "S")
    val m7 = new Mower(9, 7, "W")

    ///m5.step is not reachable
    assert(m5.move('A') equals (4, 2, Direction.N))
    assert(m6.move('A') equals (2, 7, Direction.S))
    assert(m7.move('A') equals (8, 7, Direction.W))
  }

}
