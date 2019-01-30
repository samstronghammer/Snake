package com.oliphantsb.snake.game

import com.oliphantsb.snake.enums.SquareContents
import java.awt.Container
import javax.swing.JPanel

class Board(private val rows: Int, private val cols: Int) {

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
    return board[row % rows][col % cols]
  }

  fun applyToSquares(function: (BoardSquare) -> Unit) {
    for (list in board) {
      for (square in list) {
        function(square)
      }
    }
  }

}