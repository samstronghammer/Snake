package com.oliphantsb.snake.util

import com.oliphantsb.snake.enums.ResourceType
import java.awt.Image
import javax.swing.Icon
import javax.swing.ImageIcon

object Util {

  val APPLE_VALUE = 3
  val ICON_SIZE = 8
  val SCALE = 1.0


  val wall_icon = getIcon("wallicon.png")
  val floor_icon = getIcon("flooricon.png")
  val snake1_icon = getIcon("snake1.png")
  val snake2_icon = getIcon("snake2.png")


  fun getResourceLocation(filename: String, type: ResourceType): String {
    return type.getPrefix() + filename
  }

  fun getIcon(fileName: String): Icon {
    ImageIcon()
    return ImageIcon(ImageIcon(Unit::class.java.classLoader.getResource(getResourceLocation(fileName, ResourceType.SPRITE))!!)
        .image.getScaledInstance(Util.ICON_SIZE, Util.ICON_SIZE, Image.SCALE_DEFAULT))
  }

}