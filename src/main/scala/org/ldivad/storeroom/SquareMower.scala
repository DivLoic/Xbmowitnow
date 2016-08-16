package org.ldivad.storeroom

import com.typesafe.scalalogging.Logger


/**
  * Created by loicmdivad on 12/08/2016.
  */

class SquareMower(x:Int, y:Int, d:Direction.Value, X:Int, Y:Int) extends Mower(x, y, d){

  def this(x:Int, y:Int, d:String, X:Int, Y:Int) = {
    this(x, y, Direction.parse(d), X, Y)
  }

  def this(m: Mower, X:Int, Y: Int) = {
    this(m.getX, m.getY, m.getDir, X, Y)
  }

  private val innerMower = new Mower(x, y, d)

  override def run(argOrder: Char, log: Logger): SquareMower =  {

    val nextMower = innerMower.run(argOrder, log)

    if (allFunctionalRequirements(nextMower)){

      log info s"Successfully move to the place ${nextMower.getPos}."
      new SquareMower(nextMower, X, Y)

    } else {

      log warn  "Fail to move out of the garden."
      new SquareMower(innerMower, X, Y)
    }


  }

  def allFunctionalRequirements(m: Mower): Boolean = {
    upperBound(m) &&
      rightBound(m) &&
      lowerBound(m) &&
      leftBound(m)

    // add any additional rules here ...
  }

  def validate(): Boolean = allFunctionalRequirements(innerMower)

  def upperBound(m: Mower): Boolean = m.getY <= Y

  def rightBound(m: Mower): Boolean = m.getX <= X

  def lowerBound(m: Mower): Boolean = m.getY >= 0

  def leftBound(m: Mower): Boolean = m.getX >= 0

  override def stop(): Any = innerMower.toTsv

  override def getActivity = Activity.gardening

  override def move(order: Char) = throw new IncorrectUsageOfTools(
    """
      |Can not move a square mower without pass all the functional tests.
      |Please use SqrMower.run instead.
    """.stripMargin
  )

}
