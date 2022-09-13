/*
 * Created by JFormDesigner on Sun May 29 00:16:38 CEST 2022
 */
package xyz.terrific.frame

import xyz.terrific.Main.logger
import xyz.terrific.StartGame.start
import java.awt.Dimension
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.io.IOException
import java.util.*
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JProgressBar

/**
 * @author TerrificTable55
 */
class LauncherFrame : JFrame() {
    private fun thisWindowClosing(e: WindowEvent) {
        logger?.info("Stopping!")
    }

    private fun btn_playMouseClicked(e: MouseEvent) {
        try {
            start()
        } catch (ex: IOException) {
            logger?.error("Failed Starting Client:  " + ex.message)
        }
    }

    private fun initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        val bundle = ResourceBundle.getBundle("LauncherFrame")
        btn_play = JButton()
        progressbar = JProgressBar()
        image_label = JLabel()
        some_text = JLabel()

        //======== this ========
        minimumSize = Dimension(950, 540)
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                thisWindowClosing(e)
            }
        })
        val contentPane = contentPane
        contentPane.layout = null

        //---- btn_play ----
        btn_play!!.text = bundle.getString("LauncherFrame.btn_play.text")
        btn_play!!.putClientProperty("author", "TerrificTable55")
        btn_play!!.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                btn_playMouseClicked(e)
            }
        })
        contentPane.add(btn_play)
        btn_play!!.setBounds(355, 440, 235, 50)
        contentPane.add(progressbar)
        progressbar!!.setBounds(220, 415, 509, 15)
        contentPane.add(image_label)
        image_label!!.setBounds(32, 10, 881, 390)

        //---- some_text ----
        some_text!!.text = bundle.getString("LauncherFrame.some_text.text")
        contentPane.add(some_text)
        some_text!!.setBounds(80, 440, 190, 45)
        run {

            // compute preferred size
            val preferredSize = Dimension()
            for (i in 0 until contentPane.componentCount) {
                val bounds = contentPane.getComponent(i).bounds
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width)
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height)
            }
            val insets = contentPane.insets
            preferredSize.width += insets.right
            preferredSize.height += insets.bottom
            contentPane.minimumSize = preferredSize
            contentPane.preferredSize = preferredSize
        }
        pack()
        setLocationRelativeTo(owner)
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    var btn_play: JButton? = null
    var progressbar: JProgressBar? = null
    var image_label: JLabel? = null
    var some_text: JLabel? = null // JFormDesigner - End of variables declaration  //GEN-END:variables

    init {
        initComponents()
    }
}