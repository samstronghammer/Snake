package com.oliphantsb.snake.game

import com.oliphantsb.snake.enums.Direction
import com.oliphantsb.snake.graphics.JFrameFiller
import com.oliphantsb.snake.util.Util
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import kotlin.concurrent.fixedRateTimer

class Game(val board: Board, val snake1: Snake, val snake2: Snake?) {

  private var nummillis = 0
  private var millisperframe = 100

  fun run() {
    JFrameFiller.addKeyListener(UserInputHandler())
    startTimer(millisperframe)
  }

  private fun startTimer(period: Int) {
    fixedRateTimer(period = period.toLong()) {
      JFrameFiller.update()
      snake1.removeEnd()
      snake2?.removeEnd()
      val over = snake1.incrementHead() || snake2?.incrementHead() == true
      if (over) {
        this.cancel()
        JFrameFiller.preGame(snake1.score(), snake2?.score())
      } else {
        board.updateApple()
      }

      if (nummillis > Util.SECS_PER_LEVEL * 1000) {
        this.cancel()
        nummillis = 0
        millisperframe = Math.max(millisperframe - 10, Util.MIN_MILLIS_PER_FRAME)
        startTimer(millisperframe)
      }
      nummillis += millisperframe
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