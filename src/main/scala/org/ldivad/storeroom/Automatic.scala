package org.ldivad.storeroom

import com.typesafe.scalalogging.Logger


/**
  * Created by loicmdivad on 10/08/2016.
  */

abstract class Automatic(x:Int, y:Int, direction:Direction.Value) extends Tool {

  val directions = Seq(Direction.N, Direction.E, Direction.S, Direction.W)

  def stop(): Any = ???

  def run(argOrder: Char, log: Logger): Automatic = ???

  def move(order: Char): (Int, Int, Direction.Value) = order match {
    case 'D' => (x, y, right(directions indexOf direction))
    case 'G' => (x, y, left(directions indexOf direction))
    case 'A' => val (a, b) = step; (a, b, direction)
    case _ => throw new IllegalArgumentException(s"Can only turn left (G) or right (D), Find: $order.")
  }

  private def left(i: Int): Direction.Value =
    try directions(i-1) catch { case e: IndexOutOfBoundsException => directions.last }

  private def right(i: Int): Direction.Value =
    try directions(i+1) catch { case e: IndexOutOfBoundsException => directions.head }

  // TODO: make the solution support other origin than (0, 0) ?
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
