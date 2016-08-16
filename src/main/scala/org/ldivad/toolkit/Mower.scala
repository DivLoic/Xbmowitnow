package org.ldivad.toolkit

import com.typesafe.scalalogging.Logger

/**
  * Created by loicmdivad on 10/08/2016.
  * This stand for the basic Mower, with no functional rules. <br/>
  * You can pass any order, it will even jump out of the garden.
  *
  * @param x : Int Vertical position
  * @param y : Int Horizontal position
  * @param direction : Direction.Value cardinal position (N,S,E & W)
  */
class Mower(x: Int, y: Int, direction: Direction.Value) extends Automatic(x, y, direction) {

  def this(x:Int, y:Int, d:String) = this(x, y, Direction.parse(d))

  /**
    * The main usage of this tool: mowing
    * @param log : Logger
    */
  def mow(log: Logger): Unit = log info s"Now mowing the garden at ($x,$y)."

  /**
    * Call mow on the current cell then <br/>
    * Return a new version of it self at the next cell
    * @param argOrder : Char, code corresponding to an action
    * @param log : Logger
    * @return a new Automatic Tool ready to move (here a Mower)
    */
  override def run(argOrder: Char, log: Logger) = {

    this.mow(log)

    val (xx, yy, dd) = move(argOrder)

    new Mower(xx, yy, dd)
  }

  override def getActivity = Activity.gardening

  override def stop(): Any = s"Stopping mower at: $toTsv"

  /** @return : String csv, with header x;y;direction */
  override def toCsv: String = s"$x,$y,$direction"

  /** @return : String tsv, with header x y direction */
  override def toTsv: String = s"$x $y $direction"

}
