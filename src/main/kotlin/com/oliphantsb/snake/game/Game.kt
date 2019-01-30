package com.oliphantsb.snake.game

import com.oliphantsb.snake.enums.Direction
import com.oliphantsb.snake.graphics.JFrameFiller
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import kotlin.concurrent.fixedRateTimer

class Game(val board: Board, val snake1: Snake, val snake2: Snake?) {

  fun run() {
    JFrameFiller.addKeyListener(UserInputHandler())
    fixedRateTimer(period = 1000) {
      snake1.removeEnd()
      snake2?.removeEnd()
      snake1.incrementHead()
      snake2?.incrementHead()
    }
  }

  private inner class UserInputHandler : KeyListener {

    override fun keyTyped(e: KeyEvent) {}

    override fun keyPressed(e: KeyEvent) {
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