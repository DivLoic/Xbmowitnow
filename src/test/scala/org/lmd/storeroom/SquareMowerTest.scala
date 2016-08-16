package org.lmd.storeroom

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger
import org.ldivad.storeroom.{Direction, IncorrectUsageOfTools, SquareMower}
import org.scalatest.{BeforeAndAfter, FunSuite}
import org.slf4j.LoggerFactory

/**
  * Created by loicmdivad on 12/08/2016.
  */

class SquareMowerTest extends FunSuite with BeforeAndAfter {

  var mower: SquareMower = _

  val conf = ConfigFactory.load("default")
  var logger = Logger(LoggerFactory.getLogger(conf.getString("test.logger")))

  before {
    mower =  new SquareMower(0, 0, Direction.W, 10, 10)
  }

  test("Should keep the mower inside"){

    for( cmd <- "ADAAGAADDAAGGAAA"){
      mower = mower.run(cmd, logger)
      assert(mower.validate())
    }
  }

  test("Should correctly walk on the line"){

    val results = for(cmd <- "DAAAAAAAAAAADAAAAAAAAAAADAAAAAAAAA") yield {
      mower = mower.run(cmd, logger)
      mower
    }

    results.foreach(sm => assert(sm.validate()))

    assertResult((0, 7))(results(7).getPos)
    assertResult((2, 10))(results(14).getPos)
    assertResult((10, 8))(results(26).getPos)
  }

  test("Should not move if start out of the garden"){

    var m = new SquareMower(11, 11, Direction.S, 9, 9)
    val results = for(cmd <- "ADGAGAGDAG") yield{
      m = m.run(cmd,  logger)
      m
    }
    assert( results.forall(sm => sm.getPos equals (11,11)) )
  }

  test("Should fail to bypass the validation tests"){
    intercept[IncorrectUsageOfTools]{
      mower.move('A')
    }
    mower.run('A', logger)
  }

}
