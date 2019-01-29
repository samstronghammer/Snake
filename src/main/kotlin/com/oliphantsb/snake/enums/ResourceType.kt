package com.oliphantsb.snake.enums

enum class ResourceType {
  MAP, SPRITE;

  fun getPrefix(): String {
    return when (this) {
      MAP -> "maps/"
      SPRITE -> "sprites/"
    }
  }
}