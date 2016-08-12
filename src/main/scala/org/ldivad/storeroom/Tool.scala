package org.ldivad.storeroom

/**
  * Created by loicmdivad on 10/08/2016.
  */

trait Tool {

  def toCsv: String = ???

  def toTsv: String = ???

  def toJson: String = ???

  def getActivity: Activity.Value = ???

}
