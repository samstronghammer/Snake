package com.oliphantsb.snake.util

import com.oliphantsb.snake.enums.ResourceType

object Util {

  val APPLE_VALUE = 3
  val ICON_SIZE = 8
  val SCALE = 1.0


  fun getResourceLocation(filename: String, type: ResourceType): String {
    return type.getPrefix() + filename
  }


}