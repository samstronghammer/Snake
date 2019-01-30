package com.oliphantsb.snake.game

import com.oliphantsb.snake.enums.SquareContents
import com.oliphantsb.snake.util.Util
import java.awt.Container

class Board(val rows: Int, val cols: Int) {

  private val board: List<List<BoardSquare>>

  init {
    val list = mutableListOf<List<BoardSquare>>()
    for (r in 0..rows) {
      val sublist = mutableListOf<BoardSquare>()
      list.add(sublist)
      for (c in 0..cols) {
        val newSquare = BoardSquare(r, c)
        sublist.add(newSquare)
      }
    }
    board = list
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

}