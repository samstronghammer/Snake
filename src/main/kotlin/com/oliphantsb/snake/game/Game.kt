package com.oliphantsb.snake.game

import com.oliphantsb.snake.enums.Direction
import com.oliphantsb.snake.graphics.JFrameFiller
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import kotlin.concurrent.fixedRateTimer

class Game(val board: Board, val snake1: Snake, val snake2: Snake?) {



  fun run() {
    snake2?.let { println("Snake 2 present") }
    JFrameFiller.addKeyListener(UserInputHandler())
    fixedRateTimer(period = 200) {
      JFrameFiller.update()
      snake1.removeEnd()
      snake2?.removeEnd()
      val over = snake1.incrementHead() || snake2?.incrementHead() == true
      if (over) {
        this.cancel()
        JFrameFiller.endGame(snake1.score(), snake2?.score())
      } else {
        board.updateApple()
      }
    }
  }

  private inner class UserInputHandler : KeyListener {

    override fun keyTyped(e: KeyEvent) {}

    override fun keyPressed(e: KeyEvent) {
      println("Key pressed: ${e.keyChar}")
      snake1.turn(when(e.keyCode) {
        37 -> Direction.LEFT
        38 -> Direction.UP
        39 -> Direction.RIGHT
        40 -> Direction.DOWN
        else -> snake1.direction
      })
      snake2?.turn(when(e.keyCode) {
        65 -> Direction.LEFT
        87 -> Direction.UP
        68 -> Direction.RIGHT
        83 -> Direction.DOWN
        else -> snake2.direction
      })
    }

    override fun keyReleased(e: KeyEvent) {}

  }


}