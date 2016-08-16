package org.ldivad.toolkit

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger
import org.scalatest.FunSuite
import org.ldivad.toolkit.Direction._
import org.slf4j.LoggerFactory

/**
  * Created by loicmdivad on 15/08/2016.
  */
class XebiaTest extends FunSuite{

  val conf = ConfigFactory.load("default")
  var logger = Logger(LoggerFactory.getLogger(conf.getString("test.logger")))

  def func(order: List[Char], m: SquareMower): SquareMower = order match {
    case Nil => m
    case cmd :: rest => func(rest, m.run(cmd, logger))
  }

  test("First mower from xebia assignment"){

    val m = new SquareMower(1, 2, N, 5, 5)

    val finalMower = func("GAGAGAGAA".toList, m)

    assertResult("1 3 N")(finalMower.stop())
  }

  test("Second mower from xebia assignment"){

    val m = new SquareMower(3, 3, E, 5, 5)

    val finalMower = func("AADAADADDA".toList, m)

    assertResult("5 1 E")(finalMower.stop())
  }
}
