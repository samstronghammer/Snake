package main.java.com.oliphantsb.snake.game

import main.java.com.oliphantsb.snake.enums.SquareContents

class BoardSquare(val row: Int, val col: Int) {

    private var contents = SquareContents.EMPTY

    fun isOpen(): Boolean {
        return contents == SquareContents.EMPTY
    }

    fun setContents(newContents: SquareContents) {
        contents = newContents
    }



}