package org.ldivad

import java.io.FileNotFoundException
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger
import org.ldivad.toolkit.SquareMower
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

    var garden: List[(SquareMower, String)] = null

    garden = try {
      parseGardenFile(args)
    } catch {
      case t: Throwable => logger error "Bad-formed garden file."; List()
    }

    garden.foreach( pair => {
      logger info s"Initialise a new mower at ${pair._1.getPos}."
      val  finalMower = gardener(pair._2.toList, pair._1)
      logger info s"Race of the mower over at ${finalMower.getPos}."
      println(finalMower.stop())
    })
  }

  /**
    * This is the function that move the mower after computing the next.<br/>
    * To do so it call run on the current mower.
    * @param order List of Char, each is an order
    * @param m The current mower
    * @return A mower at is final point
    */
  def gardener(order: List[Char], m: SquareMower): SquareMower = order match {
    case cmd :: rest => gardener(rest, m.run(cmd, logger))
    case Nil => m
  }

  /**
    * Parse the config file "garden"
    * @param args : An Array of String from the main method
    * @return : List of tuple -> (a mower , and a line of orders)
    */
  def parseGardenFile(args: Array[String]): List[(SquareMower, String)] = {

    val path = "./src/main/resources/"
    var filename: String = null
    var lines: List[String] = List()

    try {
      filename = path + args(0)
    } catch {
      case e: IndexOutOfBoundsException =>
        logger warn "No arguments found."
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

    val allSteps = for (l <- 2 until lines.length by 2) yield {
      lines(l).trim
    }

    allMowers.toList zip allSteps
  }
}
