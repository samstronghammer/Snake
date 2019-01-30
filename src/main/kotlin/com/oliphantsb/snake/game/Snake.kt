package com.oliphantsb.snake.game

import com.oliphantsb.snake.enums.Direction
import com.oliphantsb.snake.enums.SnakeColor
import com.oliphantsb.snake.enums.SquareContents
import java.util.*

class Snake(var head: BoardSquare, var direction: Direction, val board: Board, val snakeColor: SnakeColor) {

  private val squares: LinkedList<BoardSquare> = LinkedList()
  private var growth = 0

  init {
    squares.add(head)
  }

  fun removeEnd(): Boolean {
    if (growth == 0) {
      if (squares.size > 0) {
        val square = squares.removeLast()
        square.setContents(SquareContents.EMPTY)
      } else {
        return true
      }
    } else {
      growth--
    }
    return false
  }

  fun incrementHead() {
    val rowCol = direction.applyToRowCol(Pair(head.row, head.col))
    val newSquare = board.get(rowCol.first, rowCol.second)
    if (newSquare.isOpen()) {
      head = newSquare
      squares.addFirst(head)
      head.setContents(SquareContents.SNAKE, snakeColor)
    }
  }

  fun turn(d: Direction) {
    if (!direction.isOpposite(d)) {
      direction = d
    }
  }

  fun grow(amount: Int) {
    growth += amount
  }

}