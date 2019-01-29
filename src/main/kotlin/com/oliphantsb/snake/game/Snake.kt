package com.oliphantsb.snake.game

import com.oliphantsb.snake.enums.Direction
import com.oliphantsb.snake.enums.SquareContents
import java.util.*

class Snake(var head: BoardSquare, var direction: Direction, val board: Board) {

  private val squares: LinkedList<BoardSquare> = LinkedList()
  private var growth = 0

  init {
    squares.add(head)
  }
  //TODO return true if dead
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
    val rowCol = direction.applyToRowCol(Pair(squares[0].row, squares[0].col))
    val newSquare = board.get(rowCol.first, rowCol.second)
    if (newSquare.isOpen()) {
      head = newSquare
      squares.addFirst(head)
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