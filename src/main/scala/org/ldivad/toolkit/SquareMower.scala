package org.ldivad.toolkit

import com.typesafe.scalalogging.Logger

/**
  * Created by loicmdivad on 12/08/2016.
  * Finally, here is the Mower imagined by Mowitnow. <br/>
  * This class is where we store all the functional rules
  * related to the Square Garden and the orthonormal set.<br/>
  *
  * @param x : Int Vertical position
  * @param y : Int Horizontal position
  * @param d : Direction.value Cardinal direction (N,S,E & W)
  * @param X : Int Width of the garden
  * @param Y : Int Height of the garden
  */
class SquareMower(x:Int, y:Int, d:Direction.Value, X:Int, Y:Int) extends Mower(x, y, d){

  def this(x:Int, y:Int, d:String, X:Int, Y:Int) = {
    this(x, y, Direction.parse(d), X, Y)
  }

  def this(m: Mower, X:Int, Y: Int) = {
    this(m.getX, m.getY, m.getDir, X, Y)
    if(!allFunctionalRequirements(m))
      throw new WrongUsageOfToolException("Can not define a mower out of the garden.")
  }

  override def stop(): Any = super.toTsv

  /**
    * Do the same as super, but then apply a stack of validation rules.
    * @param argOrder : Char, code corresponding to an action
    * @param log : Logger
    * @return a new Automatic Tool ready to move (here a SquareMower)
    */
  override def run(argOrder: Char, log: Logger): SquareMower =  {

    val nextMower = super.run(argOrder, log)

    if (allFunctionalRequirements(nextMower)){

      log info s"Compute a path to: ${nextMower.getPos} with success."
      new SquareMower(nextMower, X, Y)

    } else {

      log warn  "Failed to move. It goes out of the garden."
      new SquareMower(x, y, d, X, Y)
    }
  }

  def validate(): Boolean = allFunctionalRequirements(this)

  /**
    * Bring together all requirement with a <strong>&&</strong> operator. <br/>
    * So to pass all requirements should be filled and return true.
    * @param m : Mower
    * @return : Boolean weather the mower is congruent to a SquareMower
    */
  def allFunctionalRequirements(m: Mower): Boolean = {
    upperBound(m) &&
      rightBound(m) &&
      lowerBound(m) &&
      leftBound(m)

    // add any additional rules here ...
  }

  // all the following are rules ...

  def upperBound(m: Mower): Boolean = m.getY <= Y

  def rightBound(m: Mower): Boolean = m.getX <= X

  def lowerBound(m: Mower): Boolean = m.getY >= 0

  def leftBound(m: Mower): Boolean = m.getX >= 0

}
