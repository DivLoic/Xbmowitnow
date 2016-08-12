package org.ldivad.storeroom

/**
  * Created by loicmdivad on 10/08/2016.
  */

abstract class Automatic(x:Int, y:Int, direction:Direction.Value) extends Tool {

  val directions = Seq(Direction.N, Direction.E, Direction.S, Direction.W)

  def stop(): Any = ???

  def run(argOrder: String): Automatic = ???

  def left(i: Int): Direction.Value =
    try directions(i-1) catch { case e: IndexOutOfBoundsException => directions.last }

  def right(i: Int): Direction.Value =
    try directions(i+1) catch { case e: IndexOutOfBoundsException => directions.head }

  def move(order:String): (Int, Int, Direction.Value) = order match {
    case "D" => (x, y, right(directions indexOf direction))
    case "G" => (x, y, left(directions indexOf direction))
    case "A" => val (a, b) = step; (a, b, direction)
    case _ => throw new IllegalArgumentException(s"Can only turn left (G) or right (D), Find: $order.")
  }

  // TODO: add the restriction to the garden
  // TODO: make the solution support other origin than (0, 0) ?
  def step: (Int, Int) = this.direction match {
      case Direction.N => (x, y+1)
      case Direction.E => (x+1, y)
      case Direction.S => (x, y-1)
      case Direction.W => (x-1, y)
    }

}
