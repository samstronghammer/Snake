package main.java.com.oliphantsb.snake.util

import main.java.com.oliphantsb.snake.graphics.JFrameFiller
import javax.swing.JFrame

class Main {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val frame = JFrame()
            JFrameFiller.preGame(frame)
        }
    }


}