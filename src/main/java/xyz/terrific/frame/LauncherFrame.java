/*
 * Created by JFormDesigner on Sun May 29 00:16:38 CEST 2022
 */

package xyz.terrific.frame;

import xyz.terrific.StartGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ResourceBundle;

import static xyz.terrific.Main.logger;

/**
 * @author TerrificTable55
 */
public class LauncherFrame extends JFrame {
    public LauncherFrame() {
        initComponents();
    }

    private void thisWindowClosing(WindowEvent e) {
        logger.info("Stopping!");
    }

    private void btn_playMouseClicked(MouseEvent e) {
        try {
            StartGame.start();
        } catch (IOException ex) {
            logger.error("Failed Starting Client:  " + ex.getMessage());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("LauncherFrame");
        btn_play = new JButton();
        progressbar = new JProgressBar();
        image_label = new JLabel();
        some_text = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(950, 540));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- btn_play ----
        btn_play.setText(bundle.getString("LauncherFrame.btn_play.text"));
        btn_play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_playMouseClicked(e);
            }
        });
        contentPane.add(btn_play);
        btn_play.setBounds(355, 440, 235, 50);
        contentPane.add(progressbar);
        progressbar.setBounds(220, 415, 509, 15);
        contentPane.add(image_label);
        image_label.setBounds(32, 10, 881, 390);

        //---- some_text ----
        some_text.setText(bundle.getString("LauncherFrame.some_text.text"));
        contentPane.add(some_text);
        some_text.setBounds(80, 440, 190, 45);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    public JButton btn_play;
    public JProgressBar progressbar;
    public JLabel image_label;
    public JLabel some_text;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
