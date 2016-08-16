package org.ldivad

import java.io.FileNotFoundException

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger
import org.ldivad.storeroom.SquareMower
import org.slf4j.LoggerFactory

import scala.io.Source


/**
  * Created by loicmdivad on 10/08/2016.
  */

object Main {

  val conf = ConfigFactory.load("default")
  // "default" should be replaced with environment variable

  val logger = Logger(LoggerFactory.getLogger(conf.getString("dev.logger")))

  def main(args: Array[String]): Unit = {

    try {
      val garden = parseGardenFile(args)
    } catch {
      case t: Throwable => logger error "Bad-formed garden file."
    }

  }

  def parseGardenFile(args: Array[String]) = {

    val path = "./src/main/resources/"
    var filename: String = null
    var lines: List[String] = List()

    try {
      filename = path + args(0)
    } catch {
      case e: IndexOutOfBoundsException =>
        logger info "Starting with the default garden file."
        filename =  path + conf.getString("dev.file")
    }

    try {
      lines = Source.fromFile(filename).getLines.toList
    } catch {
      case e: FileNotFoundException =>
        logger error s"The file: $filename is missing from the resource folder."
        logger error "See the README.md to know how to add a source file."
        logger warn  "Retrying with the default file ..."
        lines = Source.fromFile(path + conf.getString("dev.file")).getLines.toList
    }

    val garden = lines.head.trim.split(" ").map(_.toInt)

    val allMowers = for (l <- 1 until lines.length by 2) yield {
      val mower = lines(l).trim.split(" ")
      new SquareMower(mower(0).toInt, mower(1).toInt, mower(2), garden(0), garden(1))
    }

    val allSteps = for (l <- 3 until lines.length by 2) yield {
      lines(l).trim
    }

    allMowers.toList zip allSteps
  }
}
