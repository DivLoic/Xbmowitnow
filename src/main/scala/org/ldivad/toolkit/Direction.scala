package org.ldivad.toolkit

/**
  * Created by loicmdivad on 10/08/2016.
  * A Simple Enum to avoid manipulate the following Strings: (N,S,E & W)
  */
object Direction extends Enumeration{

  type Direction = Value

  val N, S, E, W = Value

  def parse(s: String) = s match {
    case "N" => Direction.N
    case "S" => Direction.S
    case "E" => Direction.E
    case "W" => Direction.W
    case _ => throw new IllegalArgumentException("Bad direction initialisation (N,S,E & W).")
  }

  val sequence = Seq(N, E, S, W)
}
