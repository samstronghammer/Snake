package com.oliphantsb.snake.graphics

import com.oliphantsb.snake.enums.ResourceType
import com.oliphantsb.snake.game.Game
import com.oliphantsb.snake.util.FileParser
import com.oliphantsb.snake.util.Strings
import com.oliphantsb.snake.util.Util
import java.awt.Dimension
import java.awt.event.KeyListener
import java.awt.event.WindowEvent
import javax.swing.*

object JFrameFiller {

  private val frame = JFrame()
  private val pane = frame.contentPane
  private var listener: KeyListener? = null

  init {
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.isVisible = true
  }

  fun preGame(score1: Int? = null, score2: Int? = null) {
    clear()
    pane.layout = BoxLayout(pane, BoxLayout.Y_AXIS)
    score1?.let { s1 ->
      if (score2 == null) {
        pane.add(JLabel("${Strings.SCORE}: $s1"))
      } else {
        pane.add(JLabel("${Strings.GREEN} ${Strings.SCORE}: $s1"))
        pane.add(JLabel("${Strings.YELLOW} ${Strings.SCORE}: $score2"))
      }
    }
    val listparts = addList(FileParser.getResourceListing(ResourceType.MAP).toTypedArray())
    val b1 = JButton(Strings.PLAY)
    b1.addActionListener {
      listparts.second.selectedValue?.let {
        startGame(it)
      }
    }
    val b2 = JButton(Strings.HELP)
    b2.addActionListener {
      JOptionPane.showMessageDialog(null, Strings.INSTRUCTIONS, Strings.HELP, JOptionPane.INFORMATION_MESSAGE)
    }
    val b3 = JButton(Strings.QUIT)
    b3.addActionListener {
      frame.dispatchEvent(WindowEvent(frame, WindowEvent.WINDOW_CLOSING))
    }
    pane.add(b1)
    pane.add(b2)
    pane.add(b3)
    frame.pack()
    frame.requestFocus()
  }

  private fun startGame(mapName: String) {
    clear()
    pane.layout = null
    val gameparts = FileParser.parseMap(mapName)
    gameparts.first.initializeIcons(pane)
    pane.preferredSize = Dimension(gameparts.first.cols * Util.ICON_SIZE,
        gameparts.first.rows * Util.ICON_SIZE)
    update()
    Game(gameparts.first, gameparts.second, gameparts.third).run()
  }

  private fun addList(items: Array<String>): Pair<JScrollPane, JList<String>> {
    val jlist = JList(items)
    val scrollPane = JScrollPane(jlist)
    pane.add(scrollPane)
    return Pair(scrollPane, jlist)
  }

  private fun clear() {
    pane.removeAll()
    frame.removeKeyListener(listener)
    pane.preferredSize = null
  }

  fun update() {
    frame.pack()
    frame.repaint()
    frame.requestFocus()
  }

  fun addKeyListener(keyListener: KeyListener) {
    frame.addKeyListener(keyListener)
    listener = keyListener
  }
}