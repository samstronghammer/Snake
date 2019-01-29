package main.java.com.oliphantsb.snake.game

import main.java.com.oliphantsb.snake.enums.SquareContents

class Board(val rows: Int, val cols: Int) {

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
        board[row][col].setContents(SquareContents.WALL)
    }

}