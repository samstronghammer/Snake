package com.oliphantsb.snake.util

import com.oliphantsb.snake.enums.Direction
import com.oliphantsb.snake.enums.ResourceType
import com.oliphantsb.snake.exceptions.EnumException
import com.oliphantsb.snake.exceptions.MapFormatException
import com.oliphantsb.snake.game.Board
import com.oliphantsb.snake.game.Snake
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.InputStream
import java.util.jar.JarFile


object FileParser {

  data class Pair(val row: Int, val col: Int)

  fun parseMap(filename: String): Triple<Board, Snake, Snake?> {
    val lines = BufferedReader(InputStreamReader(FileParser.javaClass.classLoader.getResourceAsStream(Util.getResourceLocation(filename, ResourceType.MAP)))).readLines()
    val numRows = lines.size
    if (numRows == 0) {
      throw MapFormatException("'$filename' has no rows.")
    }
    val numCols = lines[0].length
    val board = Board(numRows, numCols)
    var snake1: Snake? = null
    var snake2: Snake? = null
    lines.forEachIndexed { row, line ->
      if (numCols != line.length){
        throw MapFormatException("Row $numRows in '$filename' is not the right length.")
      }
      line.forEachIndexed { col, char ->
        when(char) {
          '.' -> {}
          'x' -> board.addBlockage(row, col)
          else -> {
            try {
              val d = Direction.fromAscii(char)
              when {
                snake1 == null -> snake1 = Snake(board.get(row, col), d, board)
                snake2 == null -> Snake(board.get(row, col), d, board)
                else -> throw MapFormatException("Too many snakes. Currently only two per map are supported.")
              }
            } catch (e: EnumException) {
              throw MapFormatException("Character '$char' is not valid")
            }
          }
        }
      }
    }
    snake1?.let {
      return Triple(board, it, snake2)
    }
    throw MapFormatException("No snakes were found.")
  }

  fun getResourceListing(type: ResourceType): List<String> {
    val path = type.getPrefix()
    val jarFile = File(javaClass.protectionDomain.codeSource.location.path)
    println(jarFile)
    val result = mutableListOf<String>()
    if (jarFile.isFile) {  // Run with JAR file
      println("Is jar file.")
      val jar = JarFile(jarFile)
      val entries = jar.entries() //gives ALL entries in jar
      while (entries.hasMoreElements()) {
        val name = entries.nextElement().name
        if (name.startsWith(path) && name != path) { //filter according to the path
          println(name)
          result.add(name.removePrefix(path))
        }
      }
      jar.close()
    } else { // Run with IDE
      return getResourceFiles(path)
    }
    return result
  }

  private fun getResourceFiles(path: String): List<String> = getResourceAsStream(path).use{
    println("$it")
    if(it == null) {
      return emptyList()
    } else {
      println("Made it here")
      BufferedReader(InputStreamReader(it)).readLines()
    }
  }

  private fun getResourceAsStream(resource: String): InputStream? =
      Thread.currentThread().contextClassLoader.getResourceAsStream(resource)
          ?: resource::class.java.getResourceAsStream(resource)
}