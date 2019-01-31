package com.oliphantsb.snake.game

import com.oliphantsb.snake.enums.SquareContents
import com.oliphantsb.snake.util.Util
import java.awt.Container
import kotlin.math.roundToInt

class Board(val rows: Int, val cols: Int) {

  private val board: List<List<BoardSquare>>
  private var apple: BoardSquare

  init {
    val list = mutableListOf<List<BoardSquare>>()
    for (r in 0 until rows) {
      val sublist = mutableListOf<BoardSquare>()
      list.add(sublist)
      for (c in 0 until cols) {
        val newSquare = BoardSquare(r, c)
        sublist.add(newSquare)
      }
    }
    board = list
    apple = getRandomOpenSquare()
    apple.setContents(SquareContents.APPLE)
  }

  fun initializeIcons(pane: Container) {
    applyToSquares { square ->
      square.addIcon(pane)
    }
  }

  fun addBlockage(row: Int, col: Int) {
    get(row, col).setContents(SquareContents.WALL)
  }

  fun get(row: Int, col: Int): BoardSquare {
    return board[Util.posmod(row, rows)][Util.posmod(col, cols)]
  }

  private fun applyToSquares(function: (BoardSquare) -> Unit) {
    for (list in board) {
      for (square in list) {
        function(square)
      }
    }
  }

  fun updateApple() {
    if (!apple.hasApple()) {
      apple = getRandomOpenSquare()
      apple.setContents(SquareContents.APPLE)
    }
  }

  private fun getRandomOpenSquare(): BoardSquare {
    var square: BoardSquare? = null
    while (square?.isOpen() != true) {
      square = get((rows * Math.random()).roundToInt(), (cols * Math.random()).roundToInt())
    }
    return square
  }

}