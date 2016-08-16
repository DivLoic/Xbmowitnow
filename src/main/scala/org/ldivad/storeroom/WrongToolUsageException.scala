package org.ldivad.storeroom

/**
  * Created by loicmdivad on 14/08/2016.
  */
class IncorrectUsageOfTools(message: String = null, cause: Throwable = null) extends
  Exception(IUOT.msg(message, cause), cause)

object IUOT {
  def msg(message: String, cause: Throwable) =
    if (message != null) message
    else if (cause != null) cause.toString
    else null
}