package xyz.terrific;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerContrastIJTheme;
import xyz.terrific.frame.LauncherFrame;
import xyz.terrific.utils.Logger;

import javax.swing.*;
import java.net.URL;

public class Main {

    public static LauncherFrame frame;
    public static Logger logger;

    public static String icon_url = "https://example.com";

    /**
     * Creates the Window and a new Logger, configures Windows and shows the window
     */
    public static void main(String[] args) {
        logger = new Logger("LAUNCHER");
        logger.success("Starting!");


        try {
            frame = new LauncherFrame();
            frame.setTitle("Launcher");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            frame.setIconImage(new ImageIcon("src/main/resources/images/test.png").getImage());

            frame.image_label.setIcon(new ImageIcon("src/main/resources/images/label_image.png"));
            frame.progressbar.setVisible(false);


            UIManager.setLookAndFeel(new FlatMaterialDarkerContrastIJTheme());
            SwingUtilities.updateComponentTreeUI(frame);
        }
        catch (Exception e) {
            logger.error(String.format("Failed creating Window!   %s", e.getMessage()));
            System.exit(1);
        }


        frame.setVisible(true);

    }
}
