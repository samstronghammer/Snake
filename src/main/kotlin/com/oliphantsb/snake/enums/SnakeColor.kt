package com.oliphantsb.snake.enums

import com.oliphantsb.snake.util.Util
import javax.swing.Icon

enum class SnakeColor {

  YELLOW, GREEN;

  fun getIcon(): Icon {
    return when (this) {
      YELLOW -> Util.snake1_icon
      GREEN -> Util.snake2_icon
    }
  }
}