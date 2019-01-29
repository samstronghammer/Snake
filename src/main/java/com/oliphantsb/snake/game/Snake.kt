package main.java.com.oliphantsb.snake.game

import main.java.com.oliphantsb.snake.enums.Direction
import main.java.com.oliphantsb.snake.enums.SquareContents

class Snake(start: BoardSquare, var direction: Direction) {

    private val squares: MutableList<BoardSquare> = mutableListOf(start)
    private var growth = 0

    fun removeEnd() {
        if (growth == 0) {
            val square = squares.removeAt(squares.lastIndex)
            square.setContents(SquareContents.EMPTY)
        } else {
            growth--
        }
    }

    fun incrementHead(): Boolean {
        //TODO return false if dead
        return false
    }

    fun setDirection() {

    }

    fun grow(amount: Int) {
        growth += amount
    }

}