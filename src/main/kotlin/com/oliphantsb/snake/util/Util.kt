package com.oliphantsb.snake.util

import com.oliphantsb.snake.enums.ResourceType
import java.awt.Image
import javax.swing.Icon
import javax.swing.ImageIcon

object Util {

  val APPLE_VALUE = 3
  val ICON_SIZE = 16
  val SCALE = 1.0
  val MIN_MILLIS_PER_FRAME = 20
  val SECS_PER_LEVEL = 15


  val wall_icon = getIcon("wallicon.png")
  val floor_icon = getIcon("flooricon.png")
  val snake1_icon = getIcon("yellowsnakeicon.png")
  val snake2_icon = getIcon("greensnakeicon.png")
  val apple_icon = getIcon("appleicon.png")


  fun getResourceLocation(filename: String, type: ResourceType): String {
    return type.getPrefix() + filename
  }

  private fun getIcon(fileName: String): Icon {
    ImageIcon()
    return ImageIcon(ImageIcon(Unit::class.java.classLoader.getResource(getResourceLocation(fileName, ResourceType.SPRITE))!!)
        .image.getScaledInstance(Util.ICON_SIZE, Util.ICON_SIZE, Image.SCALE_DEFAULT))
  }

  fun posmod(a: Int, b: Int): Int {
    return ((a % b) + b) % b
  }

}