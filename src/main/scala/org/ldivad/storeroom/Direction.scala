package org.ldivad.storeroom

/**
  * Created by loicmdivad on 10/08/2016.
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

}
