package org.ldivad.storeroom

/**
  * Created by loicmdivad on 10/08/2016.
  */

class Mower(x: Int, y: Int, direction: Direction.Value) extends Automatic(x, y, direction) {

  def this(x:Int, y:Int, d:String) = this(x, y, Direction.parse(d))

  def mow(): Unit = println("Mowing now")

  override def run(argOrder:String) = {

    this.mow()

    val (xx, yy, dd) = move(argOrder)

    new Mower(xx, yy, dd)

  }

  override def getActivity = Activity.gardening

}
