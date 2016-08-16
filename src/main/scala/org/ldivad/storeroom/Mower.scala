package org.ldivad.storeroom

import com.typesafe.scalalogging.Logger


/**
  * Created by loicmdivad on 10/08/2016.
  */

class Mower(x: Int, y: Int, direction: Direction.Value) extends Automatic(x, y, direction) {

  def this(x:Int, y:Int, d:String) = this(x, y, Direction.parse(d))

  def mow(log: Logger): Unit = log info s"Now mowing the garden at ($x, $y)."

  override def run(argOrder: Char, log: Logger) = {

    this.mow(log)

    val (xx, yy, dd) = move(argOrder)

    new Mower(xx, yy, dd)

  }

  override def getActivity = Activity.gardening

  override def toCsv: String = s"$x;$y;$direction"

  override def toTsv: String = s"$x $y $direction"

  override def stop(): Any = s"stopping mower at: $toTsv"

}
