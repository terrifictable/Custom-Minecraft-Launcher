package xyz.terrific

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerContrastIJTheme
import xyz.terrific.frame.LauncherFrame
import xyz.terrific.utils.Logger
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.UIManager

object Main {
    var frame: LauncherFrame? = null
    var logger: Logger? = null
    var location_resource = "src/main/resources/"

    /**
     * Creates the Window and a new Logger, configures Windows and shows the window
     */
    @JvmStatic
    fun main(args: Array<String>) {
        logger = Logger("LAUNCHER")
        logger!!.success("Starting!")
        try {
            frame = LauncherFrame()
            frame!!.title = "Launcher"
            frame!!.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame!!.isResizable = false
            frame!!.iconImage = ImageIcon(location_resource + "images/test.png").image
            frame!!.image_label?.icon = ImageIcon(location_resource + "images/label_image.png")
            frame!!.progressbar?.isVisible = false
            frame!!.some_text?.isVisible = false
            UIManager.setLookAndFeel(FlatMaterialDarkerContrastIJTheme())
            SwingUtilities.updateComponentTreeUI(frame)
        } catch (e: Exception) {
            logger!!.error(String.format("Failed creating Window!   %s", e.message))
            System.exit(1)
        }
        frame!!.isVisible = true
    }
}
