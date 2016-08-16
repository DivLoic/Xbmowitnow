package org.ldivad.toolkit

import com.typesafe.scalalogging.Logger
import Direction.sequence


/**
  * Created by loicmdivad on 10/08/2016. <br/>
  * This abstract class define a contract for all  automatic tools
  *
  * @param x : Int Vertical position
  * @param y : Int Horizontal position
  * @param direction : Direction.value cardinal position (N,S,E & W)
  */
abstract class Automatic(x:Int, y:Int, direction:Direction.Value) extends Tool {

  /**
    * Call when the race of the tool
    * is over and return a final position
    * @return : Any final message
    */
  def stop(): Any = ???

  /**
    * Use the tool and move it depending of the order argOrder
    * @param argOrder : Char, code corresponding to an action
    * @param log : Logger,
    * @return a new Automatic Tool ready to move
    */
  def run(argOrder: Char, log: Logger): Automatic = ???

  /**
    * Compute the next position of the tool
    * @param order : Char 'D', 'G', or 'A' as defined in the subject
    * @return :(Int, Int, Direction.Value) position & direction
    */
  def move(order: Char): (Int, Int, Direction.Value) = order match {
    case 'D' => (x, y, right(sequence indexOf direction))
    case 'G' => (x, y, left(sequence indexOf direction))
    case 'A' => val (a, b) = step; (a, b, direction)
    case _ => throw new IllegalArgumentException(s"Can only turn left (G) or right (D), Find: $order.")
  }

  /**
    * Return the direction on the left to the ith direction
    * @param i : Int ith direction when started from N
    * @return
    */
  private def left(i: Int): Direction.Value =
    try sequence(i-1) catch { case e: IndexOutOfBoundsException => sequence.last }

  /**
    * Return the direction on the right to the ith direction
    * @param i : Int ith direction when started from N
    * @return
    */
  private def right(i: Int): Direction.Value =
    try sequence(i+1) catch { case e: IndexOutOfBoundsException => sequence.head }

  /**
    * Compute the next position with a step forward
    * @return : (Int, Int) (x, y)
    */
  private def step: (Int, Int) = this.direction match {
      case Direction.N => (x, y+1)
      case Direction.E => (x+1, y)
      case Direction.S => (x, y-1)
      case Direction.W => (x-1, y)
  }

  def getX = this.x

  def getY = this.y

  def getDir = this.direction

  def getPos = (this.x, this.y)

}
