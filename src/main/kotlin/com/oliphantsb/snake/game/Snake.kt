package com.oliphantsb.snake.game

import com.oliphantsb.snake.enums.Direction
import com.oliphantsb.snake.enums.SnakeColor
import com.oliphantsb.snake.enums.SquareContents
import com.oliphantsb.snake.util.Util
import java.lang.IllegalStateException
import java.util.*

class Snake(var head: BoardSquare, var direction: Direction, val board: Board, val snakeColor: SnakeColor) {

  private val squares: LinkedList<BoardSquare> = LinkedList()
  private var growth = 0
  private var score = 0

  init {
    squares.add(head)
  }

  fun removeEnd() {
    if (growth == 0) {
      if (squares.size > 0) {
        val square = squares.removeLast()
        square.setContents(SquareContents.EMPTY)
      } else {
        throw IllegalStateException("The end of an empty snake should not be removed.")
      }
    } else {
      growth--
    }
  }

  fun incrementHead(): Boolean {
    val rowCol = direction.applyToRowCol(Pair(head.row, head.col))
    val newSquare = board.get(rowCol.first, rowCol.second)
    if (newSquare.isOpen()) {
      if (newSquare.hasApple()) {
        grow(Util.APPLE_VALUE)
      }
      head = newSquare
      squares.addFirst(head)
      head.setContents(SquareContents.SNAKE, snakeColor)
    }
    return squares.size < 1
  }

  fun turn(d: Direction) {
    if (!direction.isOpposite(d)) {
      direction = d
    }
  }

  private fun grow(amount: Int) {
    growth += amount
    score ++
  }

  fun score(): Int {
    return score
  }

}