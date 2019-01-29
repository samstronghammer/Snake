package com.oliphantsb.snake.game

import com.oliphantsb.snake.enums.SquareContents

class Board(private val rows: Int, private val cols: Int) {

  private val board: List<List<BoardSquare>>

  init {
    val list = mutableListOf<List<BoardSquare>>()
    for (r in 0..rows) {
      val sublist = mutableListOf<BoardSquare>()
      list.add(sublist)
      for (c in 0..cols) {
        sublist.add(BoardSquare(r, c))
      }
    }
    board = list
  }

  fun addBlockage(row: Int, col: Int) {
    get(row, col).setContents(SquareContents.WALL)
  }

  fun get(row: Int, col: Int): BoardSquare {
    return board[row % rows][col % cols]
  }

}