package com.oliphantsb.snake.game

import com.oliphantsb.snake.enums.SnakeColor
import com.oliphantsb.snake.enums.SquareContents
import com.oliphantsb.snake.util.Util
import java.awt.Container
import java.lang.IllegalArgumentException
import javax.swing.Icon
import javax.swing.JLabel
import javax.swing.JPanel

class BoardSquare(val row: Int, val col: Int) {

  private var contents = SquareContents.EMPTY
  private var snake: SnakeColor? = null
  private var icon: JLabel = JLabel()

  init {
    icon.icon = getIcon()
    icon.setBounds(col * Util.ICON_SIZE, row * Util.ICON_SIZE, Util.ICON_SIZE, Util.ICON_SIZE)
  }

  fun isOpen(): Boolean {
    return contents == SquareContents.EMPTY || contents == SquareContents.APPLE
  }

  fun hasApple(): Boolean {
    return contents == SquareContents.APPLE
  }

  fun setContents(newContents: SquareContents, newSnake: SnakeColor? = null) {
    if (newContents == SquareContents.SNAKE) {
      if (newSnake == null) {
        throw IllegalArgumentException("If a snake is entering this square, a color must be provided.")
      } else {
        snake = newSnake
      }
    } else {
      if (newSnake != null) {
        throw IllegalArgumentException("Do not provide a snake color for a square without a snake in it.")
      }
    }
    contents = newContents
    icon.icon = getIcon()
  }

  private fun getIcon(): Icon {
    return when(contents) {
      SquareContents.EMPTY -> Util.floor_icon
      SquareContents.WALL -> Util.wall_icon
      SquareContents.SNAKE -> snake?.getIcon() ?: throw IllegalStateException("Board square must have snake color if it contains a snake.")
      SquareContents.APPLE -> Util.apple_icon
    }
  }

  fun addIcon(pane: Container) {
    pane.add(icon)
  }
}