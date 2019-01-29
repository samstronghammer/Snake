package com.oliphantsb.snake.enums

import com.oliphantsb.snake.exceptions.EnumException

enum class Direction {

  UP, DOWN, LEFT, RIGHT;

  fun isOpposite(d: Direction): Boolean {
    return when (d) {
      UP -> this == DOWN
      DOWN -> this == UP
      LEFT -> this == RIGHT
      RIGHT -> this == LEFT
    }
  }

  fun applyToRowCol(rowCol: Pair<Int, Int>): Pair<Int, Int> {
    return when (this) {
      UP -> Pair(rowCol.first - 1, rowCol.second)
      DOWN -> Pair(rowCol.first + 1, rowCol.second)
      LEFT -> Pair(rowCol.first, rowCol.second - 1)
      RIGHT -> Pair(rowCol.first, rowCol.second + 1)
    }
  }

  companion object {
    fun fromAscii(c: Char): Direction {
      return when (c) {
        '^' -> UP
        'v' -> DOWN
        '<' -> LEFT
        '>' -> RIGHT
        else -> throw EnumException("'$c' is not a valid direction character. Try one of: '^', 'v', '<', '>'.")
      }
    }
  }
}