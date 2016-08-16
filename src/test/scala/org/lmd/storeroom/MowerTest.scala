package org.lmd.storeroom

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger
import org.ldivad.storeroom.{Direction, Mower}
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FunSuite}
import org.slf4j.LoggerFactory

/**
  * Created by loicmdivad on 12/08/2016.
  */

class MowerTest extends FunSuite with BeforeAndAfter {

  var mower: Mower = _
  var allMowers: List[Mower] = _

  val conf = ConfigFactory.load("default")
  var logger = Logger(LoggerFactory.getLogger(conf.getString("test.logger")))

  before {
    allMowers = List()
    mower = new Mower(0, 0, "N")
  }

  test("Should fail on bad command"){

    intercept[IllegalArgumentException]{
      mower.move('Z')
    }

    intercept[IllegalArgumentException]{
      Direction.parse("K")
    }
  }

  test("Should move the mower n times"){

    import Direction._

    val results = List(
      (0,1,N), (0,1,E), (1,1,E), (2,1,E),
      (2,1,N), (2,2,N), (2,2,E), (3,2,E),
      (3,2,S), (3,2,W), (3,2,N), (3,3,N)
    )

    ("ADAAGADADDDA".toList, results).zipped.foreach((cmd, state) => {
      mower = mower.run(cmd, logger)

      assert(mower.getDir equals state._3)
      assert(mower.getPos equals (state._1, state._2))
    })

  }

  test("Should move the mower out of the garden"){

    import Direction._

    val results = List(
      (0,0,W), (0,0,S), (0,-1,S), (0,-1,W),
      (-1,-1,W), (-2,-1,W), (-2,-1,S), (-2,-2,S),
      (-2,-2,W), (-3,-2,W), (-3,-2,S), (-3,-3,S)
    )

    ("GGADAAGADAGA".toList, results).zipped.foreach((cmd, state) => {
      mower = mower.run(cmd, logger)

      assert(mower.getDir equals state._3)
      assert(mower.getPos equals (state._1, state._2))
    })
  }

}
