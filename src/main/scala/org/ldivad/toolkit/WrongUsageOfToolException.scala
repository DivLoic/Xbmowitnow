package org.ldivad.toolkit

/**
  * Created by loicmdivad on 14/08/2016.
  * Feel free to send this if someone use a instance of Tool badly.
  */
class WrongUsageOfToolException(message: String = null, cause: Throwable = null) extends
  Exception(ParseMsg.msg(message, cause), cause)

object ParseMsg {
  def msg(message: String, cause: Throwable) =
    if (message != null) message
    else if (cause != null) cause.toString
    else null
}