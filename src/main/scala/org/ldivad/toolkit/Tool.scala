package org.ldivad.toolkit

/**
  * Created by loicmdivad on 10/08/2016. <br/>
  * First tool interface, can be extended by a hammer; a ladder or why not ... a mower
  */
trait Tool {

  def toCsv: String = ???

  def toTsv: String = ???

  def getActivity: Activity.Value = ???

}
