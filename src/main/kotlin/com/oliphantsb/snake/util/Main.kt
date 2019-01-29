package com.oliphantsb.snake.util

import com.oliphantsb.snake.enums.ResourceType

class Main {

  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
//      val frame = JFrame()
//      JFrameFiller.preGame(frame)
      for (s in FileParser.getResourceListing(ResourceType.MAP)) {
        FileParser.parseMap(s)
        println("name: $s")
      }
      println("Hello")
    }
  }


}